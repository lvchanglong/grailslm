package common

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PlatformController {

    PlatformService platformService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond platformService.list(params), model:[platformCount: platformService.count()]
    }

    def show(Long id) {
        respond platformService.get(id)
    }

    def create() {
        respond new Platform(params)
    }

    def save(Platform platform) {
        if (platform == null) {
            notFound()
            return
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                platform.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        if(params.state == "开启") {
            Platform.where {
                state == "开启"
            }.updateAll([state: "关闭"])
        }

        try {
            platformService.save(platform)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: CREATED
    }

    def edit(Long id) {
        respond platformService.get(id)
    }

    def update(Platform platform) {
        if (platform == null) {
            notFound()
            return
        }

        if(params.state == "开启") {
            Platform.where {
                state == "开启"
            }.updateAll([state: "关闭"])
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                platform.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        if(!params.functions) {
            platform.functions.clear()
        }

        try {
            platformService.save(platform)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }

        def activePlatform = Platform.getActive()//获得已开启平台
        if(!activePlatform) {
            activePlatform = Platform.mockActive()//虚拟一个
        }
        servletContext.platform = activePlatform

        render status: OK
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        platformService.delete(id)
        render status: NO_CONTENT
    }

    protected void notFound() {
        render status: NOT_FOUND
    }

    /**
     * 批量导出
    */
    def exportData() {
        def dirpath = servletContext.getRealPath("/") + "temp"
        def filetype = 'xls'
        def filename = new Date().format("yyyyMMddHHmmss") + "." + filetype
        def file = FileHelper.getFile(dirpath, filename)

        def array = new ArrayList()
        Platform.list().each {
            def cell = new HashMap()
            cell.put(0, it.id)
            cell.put(1, it.version)
            cell.put(2, it.dateCreated)
            cell.put(3, it.lastUpdated)
            array.add(cell)
        }
        ExcelHelper.writeExcel(file, array)

        response.contentType = grailsApplication.config.grails.mime.types[filetype]
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"))
        response.outputStream.withStream {
            it.write(file.getBytes())
            it.flush()
            it.close()
        }

        if(file.exists()) {
            file.delete()
        }
    }

    /**
     * 图片预览
     */
    def image(Platform instance) {
        def out = response.getOutputStream()
        if(instance.data) {
            out.write(instance.data.bytes)
        } else {
            def url = new URL(createLink(uri:'/', absolute:true) + assetPath(src:'home/000.png').replaceFirst("/", ""))
            out.write(url.getBytes())
        }
        out.flush()
        out.close()
    }

    /**
     * 表格
     */
    def chart() {

    }

    def c1() {
        def labels = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]

        Calendar calendar = Calendar.getInstance()
        def nYear = calendar.get(Calendar.YEAR)

        def instanceList = Platform.where {
            year(dateCreated) == nYear
        }.list()

        ArrayList datas = new ArrayList()
        HashMap hmMonthGroup = instanceList.groupBy {it->
            it.dateCreated.month + 1
        }

        def zhcn = [1:"一月", 2:"二月", 3:"三月", 4:"四月", 5:"五月", 6:"六月", 7:"七月", 8:"八月", 9:"九月", 10:"十月", 11:"十一月", 12:"十二月"]
        def maps = new HashMap()
        for(int m in 1..12) {
            ArrayList list = hmMonthGroup.get(m)
            if(list) {
                datas.add(list.size())
            } else {
                datas.add(0)
            }
            maps.put(zhcn.get(m), list)
        }

        render new HashMap([labels:labels, datas:datas, maps:maps]) as JSON
    }
}

package common

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MimeFileController {

    MimeFileService mimeFileService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def query = MimeFile.where {
            classification == "公开" ||
            creater {
                id == session.uid
            }
            if(params.pid) {
                parent {
                    id == params.pid
                }
            } else {
                parent == null
            }
        }
        respond query.list(params), model:[mimeFileCount: query.count()]
    }

    def show(Long id) {
        respond mimeFileService.get(id)
    }

    def create() {
        respond new MimeFile(params)
    }

    def save(MimeFile mimeFile) {
        if (mimeFile == null) {
            notFound()
            return
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                mimeFile.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        mimeFile.creater = User.load(session.uid)

        try {
            mimeFileService.save(mimeFile)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: CREATED
    }

    def edit(Long id) {
        respond mimeFileService.get(id)
    }

    def update(MimeFile mimeFile) {
        if (mimeFile == null) {
            notFound()
            return
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                mimeFile.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        try {
            mimeFileService.save(mimeFile)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: OK
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }
        mimeFileService.delete(id)
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
        MimeFile.list().each {
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
    def image(MimeFile instance) {
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
     * 下载
     */
    def download(MimeFile instance) {
        if(!instance.data) {
            render status: BAD_REQUEST, text: "无法下载"
            return
        }
        try {
            def filename = instance.filename
            def filetype = FileHelper.getFileType(filename)
            response.contentType = grailsApplication.config.grails.mime.types[filetype]
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"))
            def out = response.getOutputStream()
            out << instance.data.bytes
            out.flush()
            out.close()
        } catch(Exception e) {

        }
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

        def instanceList = MimeFile.where {
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

    /**
     * 获取目录树结构(生成jstree结构)
     */
    def listDirsForJsTree() {
        def array = new ArrayList()
        array.add(["id":"0", "parent":"#", text:"/", "state":["opened":true]])
        MimeFile.findAllByType("文件夹").each { dir->
            def hm = new HashMap()
            hm.put("id", dir.id.toString())
            hm.put("parent", dir.parent?dir.parent.id.toString():"0")
            hm.put("text", dir.filename)
            hm.put("state", ["opened":true])
            array.add(hm)
        }
        render array as JSON
    }

    /**
     * 移动文件
     * @param id
     * @param pid
     */
    def moveTo(String id, String pid) {
        if(!id || pid == null || pid.isEmpty() || id.equalsIgnoreCase(pid)) {
            render status: BAD_REQUEST, text: "暂不支持"
            return
        }

        def instance = MimeFile.get(id)
        if(pid.equalsIgnoreCase("0")) {
            instance.parent = null
        } else {
            instance.parent = MimeFile.load(pid)
        }

        try {
            mimeFileService.save(instance)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: OK, text: "移动成功"
    }

    /**
     * 文件预览
     */
    def preview(MimeFile instance) {
        if (instance == null) {
            render status: NOT_FOUND, text: "不存在"
            return
        }
        if(instance.isDir() || !instance.data) {
            render status: BAD_REQUEST, text: "不支持"
            return
        }

        def realPath = servletContext.getRealPath("/")
        def keyPath = "temp"
        def dirpath = "${realPath}${keyPath}"

        def serverFileType = FileHelper.getFileType(instance.filename).toLowerCase()
        def bytes = instance.data.bytes
        def serverFileName = "${bytes.encodeAsMD5()}.${serverFileType}" //服务器存储-文件名
        def serverFile = FileHelper.getFile(dirpath, serverFileName)
        if(!serverFile.exists()) { //不存在，则下载
            serverFile.withOutputStream {os->
                def is = new ByteArrayInputStream(bytes)
                os << is
                os.flush()
            }
        }

        if(serverFileType.equalsIgnoreCase("pdf")) {
            render(view: "preview${params.browser?:""}", model: [path: "/static/${keyPath}/${serverFileName}"])
            return
        }
        def targetFile = FileHelper.getFile(dirpath, serverFileName + ".pdf")
        if(FileConverter.convertTo(serverFile, targetFile)) {
            render(view: "preview${params.browser?:""}", model: [path: "/static/${keyPath}/${targetFile.getName()}"])
            return
        }
        redirect(uri:"/static/${keyPath}/${serverFileName}") //处理未支持格式
    }

}

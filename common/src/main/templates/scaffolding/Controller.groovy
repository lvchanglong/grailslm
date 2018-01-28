<%=packageName ? "package ${packageName}" : ''%>

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ${className}Controller {

    ${className}Service ${propertyName}Service

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ${propertyName}Service.list(params), model:[${propertyName}Count: ${propertyName}Service.count()]
    }

    def show(Long id) {
        respond ${propertyName}Service.get(id)
    }

    def create() {
        respond new ${className}(params)
    }

    def save(${className} ${propertyName}) {
        if (${propertyName} == null) {
            notFound()
            return
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                ${propertyName}.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        try {
            ${propertyName}Service.save(${propertyName})
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: CREATED
    }

    def edit(Long id) {
        respond ${propertyName}Service.get(id)
    }

    def update(${className} ${propertyName}) {
        if (${propertyName} == null) {
            notFound()
            return
        }

        try {
            def multipartFile = request.getFile("file")
            if(!multipartFile.empty) {
                ${propertyName}.properties = [filename:multipartFile.getOriginalFilename(), data:[bytes:multipartFile.getBytes()]]
            }
        } catch (MissingMethodException e) {

        }

        try {
            ${propertyName}Service.save(${propertyName})
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
        ${propertyName}Service.delete(id)
        render status: NO_CONTENT
    }

    protected void notFound() {
        render status: NOT_FOUND
    }

    /**
     * 批量导出
    */
    def exportData() {
        def dirpath = servletContext.getRealPath("/") + "temp/"
        def filetype = 'xls'
        def filename = new Date().format("yyyyMMddHHmmss") + "." + filetype
        def file = FileHelper.getFile(dirpath, filename)

        def array = new ArrayList()
        ${className}.list().each {
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
    def image(${className} instance) {
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
    def download(${className} instance) {
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

        def instanceList = ${className}.where {
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

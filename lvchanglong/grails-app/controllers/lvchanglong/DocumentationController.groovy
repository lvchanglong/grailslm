package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class DocumentationController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Documentation.list(params), model:[documentationCount: Documentation.count()]
    }

    def show(Documentation documentation) {
        respond documentation
    }

    def create() {
        respond new Documentation(params)
    }

    @Transactional
    def save(Documentation documentation) {
        if (documentation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            documentation.properties = [dataname: dataname, data: new DocumentationData([bytes: bytes])]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            documentation.properties = [facename: facename, face: new DocumentationFace([bytes: bytes])]
        }

        if (documentation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond documentation.errors, view:'create'
            return
        }

        documentation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'documentation.label', default: 'Documentation'), documentation.id])
                redirect documentation
            }
            '*' { respond documentation, [status: CREATED] }
        }
    }

    def edit(Documentation documentation) {
        respond documentation
    }

    @Transactional
    def update(Documentation documentation) {
        if (documentation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            documentation.properties = [dataname: dataname, data: [bytes: bytes]]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            documentation.properties = [facename: facename, face: [bytes: bytes]]
        }

        if (documentation.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond documentation.errors, view:'edit'
            return
        }

        documentation.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'documentation.label', default: 'Documentation'), documentation.id])
                redirect documentation
            }
            '*'{ respond documentation, [status: OK] }
        }
    }

    @Transactional
    def delete(Documentation documentation) {

        if (documentation == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        documentation.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentation.label', default: 'Documentation'), documentation.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentation.label', default: 'Documentation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * 图片预览
     * @param documentation
     * @return
     */
    def preview(Documentation documentation) {
        def out = response.getOutputStream()
        if(documentation == null || documentation.face == null) {
            def url = new URL(createLink(uri: "/", absolute: true) + assetPath(src: "birds/article1.jpg"))
            out.write(url.getBytes())
        } else {
            out.write(documentation.face.bytes)
        }
        out.flush()
        out.close()
    }

    /**
     * 文档下载
     * @param documentation
     * @return
     */
    def download(Documentation documentation) {
        if(!documentation.data) {
            render status: BAD_REQUEST, text: "无法下载"
            return
        }
        try {
            def fileName = documentation.dataname
            def fileType = FileHelper.getFileType(fileName)
            response.contentType = grailsApplication.config.grails.mime.types[fileType]
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"))
            def out = response.getOutputStream()
            out << documentation.data.bytes
            out.flush()
            out.close()
        } catch(Exception e) {

        }
    }

}

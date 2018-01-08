package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class SoftwareController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Software.list(params), model:[softwareCount: Software.count()]
    }

    def show(Software software) {
        respond software
    }

    def create() {
        respond new Software(params)
    }

    @Transactional
    def save(Software software) {
        if (software == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            software.properties = [dataname: dataname, data: [bytes: bytes]]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            software.properties = [facename: facename, face: [bytes: bytes]]
        }

        if (software.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond software.errors, view:'create'
            return
        }

        software.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'software.label', default: 'Software'), software.id])
                redirect software
            }
            '*' { respond software, [status: CREATED] }
        }
    }

    def edit(Software software) {
        respond software
    }

    @Transactional
    def update(Software software) {
        if (software == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            software.properties = [dataname: dataname, data: [bytes: bytes]]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            software.properties = [facename: facename, face: [bytes: bytes]]
        }

        if (software.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond software.errors, view:'edit'
            return
        }

        software.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'software.label', default: 'Software'), software.id])
                redirect software
            }
            '*'{ respond software, [status: OK] }
        }
    }

    @Transactional
    def delete(Software software) {

        if (software == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        software.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'software.label', default: 'Software'), software.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'software.label', default: 'Software'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * 图片预览
     * @param software
     * @return
     */
    def preview(Software software) {
        def out = response.getOutputStream()
        if(software == null || software.face == null) {
            def url = new URL(createLink(uri: "/", absolute: true) + assetPath(src: "web/software.jpg"))
            out.write(url.getBytes())
        } else {
            out.write(software.face.bytes)
        }
        out.flush()
        out.close()
    }

    /**
     * 软件下载
     * @param software
     * @return
     */
    def download(Software software) {
        if(!software.data) {
            render status: BAD_REQUEST, text: "无法下载"
            return
        }
        try {
            def fileName = software.dataname
            def fileType = FileHelper.getFileType(fileName)
            response.contentType = grailsApplication.config.grails.mime.types[fileType]
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"))
            def out = response.getOutputStream()
            out << software.data.bytes
            out.flush()
            out.close()
        } catch(Exception e) {

        }
    }

}

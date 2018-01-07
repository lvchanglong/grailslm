package lvchanglong

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class ArticleController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleCount: Article.count()]
    }

    def show(Article article) {
        respond article
    }

    def create() {
        respond new Article(params)
    }

    @Transactional
    def save(Article article) {
        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            article.properties = [dataname: dataname, data: new ArticleData([bytes: bytes])]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            article.properties = [facename: facename, face: new ArticleFace([bytes: bytes])]
        }

        if (article.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond article.errors, view:'create'
            return
        }

        article.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*' { respond article, [status: CREATED] }
        }
    }

    def edit(Article article) {
        respond article
    }

    @Transactional
    def update(Article article) {
        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def fData = request.getFile("datafile")
        if(!fData.empty) {
            def dataname = fData.getOriginalFilename() //文件名
            byte[] bytes = fData.getBytes() //二进制数据
            article.properties = [dataname: dataname, data: [bytes: bytes]]
        }

        def fFace = request.getFile("facefile")
        if(!fFace.empty) {
            def facename = fFace.getOriginalFilename() //文件名
            byte[] bytes = fFace.getBytes() //二进制数据
            article.properties = [facename: facename, face: [bytes: bytes]]
        }

        if (article.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond article.errors, view:'edit'
            return
        }

        article.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect article
            }
            '*'{ respond article, [status: OK] }
        }
    }

    @Transactional
    def delete(Article article) {

        if (article == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        article.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'article.label', default: 'Article'), article.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'article.label', default: 'Article'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    /**
     * 图片预览
     * @param article
     * @return
     */
    def preview(Article article) {
        def out = response.getOutputStream()
        if(article == null || article.face == null) {
            def url = new URL(createLink(uri: "/", absolute: true) + assetPath(src: "birds/article1.jpg"))
            out.write(url.getBytes())
        } else {
            out.write(article.face.bytes)
        }
        out.flush()
        out.close()
    }

    /**
     * 附件下载
     * @param article
     * @return
     */
    def download(Article article) {
        if(!article.data) {
            render status: BAD_REQUEST, text: "无法下载"
            return
        }
        try {
            def fileName = article.dataname
            def fileType = FileHelper.getFileType(fileName)
            response.contentType = grailsApplication.config.grails.mime.types[fileType]
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"))
            def out = response.getOutputStream()
            out << article.data.bytes
            out.flush()
            out.close()
        } catch(Exception e) {

        }
    }

}

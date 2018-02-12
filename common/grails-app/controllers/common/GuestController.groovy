package common

import extension.ExcelHtml
import extension.ExcelHtmlService
import grails.validation.ValidationException
import org.jsoup.Jsoup
import org.jsoup.select.Elements

import static org.springframework.http.HttpStatus.*

class GuestController {

    def userService
    def loginLogService
    def searchLogService
    ExcelHtmlService excelHtmlService

    def index(String q) {
        def query = MimeFile.where {
            classification == "公开" && type == "文件" && filename ==~ "%${q?q.trim():""}%"
        }
        def mimeFileCount = query.count()
        try {
            if(q) {
                searchLogService.save(new SearchLog([platform:"网站", type:"文件检索", q:q, cnt:mimeFileCount, ip:CommonHelper.getRealIp(request)]))
            }
        } catch (ValidationException e) {
            e.printStackTrace()
        }
        respond query.list(params), model:[mimeFileCount: mimeFileCount]
    }

    /**
     * 用户登录
     */
    def login(String username, String password) {
        if (username && password) {
            def user = User.findByUsernameAndPassword(username, password.encodeAsMD5())
            if (user) {
                if(!user.getStateToBoolean()) {
                    render status: UNAUTHORIZED, text: '已冻结'
                    return
                }
                session.uid = user.id
                session.setMaxInactiveInterval(43200) //失效时间12小时

                if(!user.isSuperman()) {
                    try {
                        loginLogService.save(new LoginLog([username:user.username, nickname:user.nickname, rolename:user.role?.name, ip:CommonHelper.getRealIp(request)]))
                    } catch (ValidationException e) {
                        e.printStackTrace()
                    }
                }
                render status: OK, text: '操作成功，初始化...'
                return
            } else {
                render status: UNAUTHORIZED, text: '认证失败'
                return
            }
        }
        render status: BAD_REQUEST, text: '参数异常'
    }

    /**
     * 用户注册
     */
    def register(User user) {
        try {
            user.role = Role.getVip()
            userService.save(user)
        } catch (ValidationException e) {
            render status: INTERNAL_SERVER_ERROR
            return
        }
        render status: CREATED, text: "注册成功"
    }

//-------------------------------------------------------------------------------------------------------------------------------------------

    def demo(String con) {
        def query = ExcelHtml.where {
            content ==~ "%${con?con.split("#").join("%").trim():""}%"
        }
        respond query.list(params), model:[excelHtmlCount: query.count()]
    }

    def test() {
        def ROOT = "D:\\ZPS-34个表格-131个表单"
        FileHelper.getDir("${ROOT}_src").listFiles().each {file->
            def fileName = file.getName() //文件名
            def fileType = FileHelper.getFileType(fileName)
            if(fileName.endsWith("xls") || fileName.endsWith("xlsx")) { //保留-excel文件
                if(!fileName.startsWith("~\$")) { //过滤-无用文件
                    def html = FileHelper.getFile("${ROOT}_tar", "${fileName}.html")
                    if(FileConverter.convertTo(file, html)) {
                        def dir = new File("${ROOT}_tar", "${fileName}_files")
                        if(dir.exists()) {
                            def number = fileName.find(/.*?(?=\.${fileType}$)/) //编号

                            dir.listFiles().each { f ->
                                if(f.getName().startsWith("sheet")) {
                                    def htmlText = f.getText("UTF-8")
                                    try {
                                        def document = Jsoup.parse(htmlText)
                                        /**
                                         * 清理td内标签
                                         */
                                        document.getElementsByTag("td").removeAttr("height").removeAttr("style").each {td->
                                            td.html(td.text())
                                        }

                                        def trList = document.getElementsByTag("tr").removeAttr("height").removeAttr("style")
                                        def max = trList[0].attr("style", "display:none;").child(0).text().toInteger() //从信息列获取列头行数
                                        def tableName = trList[1].child(0).text() //表名
                                        println "${max}#${number}#${tableName}"

                                        /**
                                         * 列头由td变身为th
                                         */
                                        for(i in 0..max) {
                                            trList[i].addClass("th-father").children().tagName("th")
                                        }

                                        Elements table = document.getElementsByTag("table").removeAttr("height").removeAttr("style").addClass("excel-html")
                                        excelHtmlService.save(new ExcelHtml([number:number, tableName:tableName, content:table.outerHtml()]))
                                    } catch (Exception e) {
                                        println e
                                    } //end try catch
                                }
                            }
                        } else {
                            def number = fileName.find(/.*?(?=\.${fileType}$)/) //编号
                            def htmlText = html.getText("UTF-8")

                            try {
                                def document = Jsoup.parse(htmlText)
                                /**
                                 * 清理td内标签
                                 */
                                document.getElementsByTag("td").removeAttr("height").removeAttr("style").each {td->
                                    td.html(td.text())
                                }

                                def trList = document.getElementsByTag("tr").removeAttr("height").removeAttr("style")
                                def max = trList[0].attr("style", "display:none;").child(0).text().toInteger() //从信息列获取列头行数
                                def tableName = trList[1].child(0).text() //表名
                                println "${max}#${number}#${tableName}"

                                /**
                                 * 列头由td变身为th
                                 */
                                for(i in 0..max) {
                                    trList[i].addClass("th-father").children().tagName("th")
                                }

                                Elements table = document.getElementsByTag("table").removeAttr("height").removeAttr("style").addClass("excel-html")
                                excelHtmlService.save(new ExcelHtml([number:number, tableName:tableName, content:table.outerHtml()]))
                            } catch (Exception e) {
                                println e
                            } //end try catch
                        }
                    }
                }
            }
        }
        render true
    }

}

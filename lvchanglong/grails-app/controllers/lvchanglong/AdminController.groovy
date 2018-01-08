package lvchanglong

/**
 * 管理员
 */
class AdminController {

    def index() {
        render(view: "/index")
    }

    def test() {
        [
                "06-安装及配置MySQL", "07-安装及运行MySQL Front", "08-以指定环境运行项目", "09-连接MySQL数据库", "10-静态页面的实现及访问_样例", "11-动态页面的实现及访问样例", "12-ajax请求响应与常见问题解决方案",
                "13-form表单的使用及响应", "14-ajaxForm的使用及响应", "15-html基础", "16-grails标签基础", "17-css基础", "18-前后台模板分离", "19-导入bootstrap模板实例",
                "20-网站首页调整", "21-创建用户domain模型", "22-根据domain模型生成控制器及相关视图", "23-resource的使用", "24-withFormat详解", "25-respond的使用", "26-文件上传与文件列表",
                "27-模态框的使用", "28-弹出框的使用", "29-用户登录", "30-退出登录", "31-用户注册", "32-用户信息修改", "33-幻灯片模型&相应控制器以及相关视图的生成",
                "34-幻灯片的创建", "35-幻灯片的显示", "36-幻灯片的修改", "37-幻灯片列表展示", "38-首页动态幻灯片展示", "39-文章模型&相应控制器以及相关视图的实现", "40-首页动态文章展示",
                "41-首页文章检索与分页展示", "42-视频播放", "43-在文章中嵌入视频", "44-杂七杂八【完结】"
        ].each {
            def article = new Article()
            article.title = it
            article.content = "链接：https://pan.baidu.com/s/1hstlhQk\n" +
                    "密码：8adt\n" +
                    "<a href=\"https://v.qq.com/x/page/z0526x9bamo.html\" target=\"_blank\">在线播放</a>"
            article.zindex = ("10" + it.split("-")[0]).toLong()
            article.grouping = "Grails实战教程"
            article.save()
        }
        render true
    }

}

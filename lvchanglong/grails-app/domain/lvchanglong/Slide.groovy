package lvchanglong

/**
 * 幻灯片
 */
class Slide {

    SlideData data //数据
    String dataname //文件名

    String title //标题
    String content //内容
    Long zindex //排列顺序

    static mapping = {
        table "slide"
        id column: "id"
        version column: "version"

        data column: "data"
        dataname column: "dataname"
        title column: "title"
        content column: "content", sqlType: "TEXT"
        zindex column: "zindex"

        sort zindex: "desc" //降序desc，升序asc
    }

    static constraints = {
        data(nullable: false)
        dataname(nullable: false, blank: false) //不能为null，不能为""
        title(nullable: false, blank: false)
        content(nullable: true, blank: true)
        zindex(nullable: true)
    }

    def beforeInsert() {
        if(!zindex) {
            zindex = System.currentTimeMillis()
        }
    }

}

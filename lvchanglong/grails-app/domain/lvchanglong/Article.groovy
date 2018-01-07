package lvchanglong

/**
 * 文章
 */
class Article {

    ArticleFace face //文章封面-数据
    String facename //文章封面-文件名
    ArticleData data //文章附件-数据
    String dataname //文章附件-文件名

    String title //标题
    String content //内容

    Long zindex //排列顺序
    String grouping = "grails"//分组

    Date dateCreated //创建时间（特殊写法）
    Date lastUpdated //更新时间（特殊写法）

    static mapping = {
        table "article"
        id column: "id"
        version column: "version"

        face column: "face"
        facename column: "facename"
        data column: "data"
        dataname column: "dataname"

        title column: "title"
        content column: "content", sqlType: "TEXT"
        zindex column: "zindex"
        grouping column: "grouping"

        dateCreated column: "date_created"
        lastUpdated column: "last_updated"

        sort id: "desc" //降序desc，升序asc
    }

    static constraints = {
        title(nullable: false, blank: false) //不能为null，不能为""
        content(nullable: true, blank: true)
        zindex(nullable: true)
        grouping(nullable: true, blank: true)

        data(nullable: true)
        dataname(nullable: true, blank: true)
        face(nullable: true)
        facename(nullable: true, blank: true)
    }

    def beforeInsert() {
        if(!zindex) {
            zindex = System.currentTimeMillis()
        }
    }

}

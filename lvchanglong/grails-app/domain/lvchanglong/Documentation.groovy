package lvchanglong

/**
 * 文档
 */
class Documentation {

    DocumentationFace face //文档封面-数据
    String facename //文档封面-文件名
    DocumentationData data //文档-数据
    String dataname //文档-文件名

    String title //标题
    String content //内容
    Long zindex //排列顺序
    String grouping = "grails" //分组

    Date dateCreated //创建时间（特殊写法）
    Date lastUpdated //更新时间（特殊写法）

    static mapping = {
        table "documentation"
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

        sort zindex: "desc" //降序desc，升序asc
    }

    static constraints = {
        face(nullable: true)
        facename(nullable: true, blank: true)
        data(nullable: false)
        dataname(nullable: false, blank: false) //不能为null，不能为""

        title(nullable: false, blank: false)
        content(nullable: true, blank: true)
        zindex(nullable: true)
        grouping(nullable: true, blank: true)
    }

    def beforeInsert() {
        if(!zindex) {
            zindex = System.currentTimeMillis()
        }
    }

}

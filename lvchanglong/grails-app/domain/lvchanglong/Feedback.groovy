package lvchanglong

/**
 * 反馈
 */
class Feedback {
    String title //标题
    String content //内容

    Date dateCreated //创建时间（特殊写法）
    Date lastUpdated //更新时间（特殊写法）

    static mapping = {
        table "feedback"
        id column: "id"
        version column: "version"

        title column: "title"
        content column: "content", sqlType: "TEXT"

        dateCreated column: "date_created"
        lastUpdated column: "last_updated"

        sort id: "desc" //降序desc，升序asc
    }

    static constraints = {
        title(nullable: false, blank: false) //不能为null，不能为""
        content(nullable: false, blank: false, validator: { val, obj ->
            !val.contains("href")
        })
    }

}

package common

/**
 * 反馈管理
 */
class Feedback {

    String username //账号
    String content	//内容
    String type = "网站"//类型（网站|客户端）
    String ip //ip
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_feedback"
        id column: "id"
        version column: "version"

        username column:"username"
        content column:"content", sqlType:"Text"
        type column:"type"
        ip column:"ip"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        sort id:"desc"
    }

    static constraints = {
        username(blank:false, nullable:false)
        content(blank:false, nullable:false)
        type(blank:false, nullable:false, inList: ["网站", "客户端"])
        ip(blank:true, nullable:true)
    }

    String toString() {
        return this.content
    }

}

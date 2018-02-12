package common

/**
 * 检索日志
 */
class SearchLog {

    String platform = "网站" //平台
    String type = "未知"//检索类型
    String q //检索内容
    String cnt //结果数
    String ip //ip
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_searchlog"
        id column: "id"
        version column: "version"

        platform column:"platform"
        type column:"type"
        q column:"q"
        cnt column:"cnt"
        ip column:"ip"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        sort id:"desc"
    }

    static constraints = {
        platform(blank:false, nullable:false, inList: ["网站", "客户端"])
        type(blank:true, nullable:true)
        q(blank:true, nullable:true)
        cnt(blank:true, nullable:true)
        ip(blank:true, nullable:true)
    }

    String toString() {
        return this.q
    }

}

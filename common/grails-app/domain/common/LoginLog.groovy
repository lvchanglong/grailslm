package common

/**
 * 登录日志
 */
class LoginLog {

    String username//账号
    String nickname//昵称
    String rolename//角色
    String ip //ip
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_loginlog"
        id column: "id"
        version column: "version"

        username column:"username"
        nickname column:"nickname"
        rolename column:"rolename"
        ip column:"ip"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        sort id:"desc"
    }

    static constraints = {
        username(blank:false, nullable:false)
        nickname(blank:true, nullable:true)
        rolename(blank:true, nullable:true)
        ip(blank:true, nullable:true)
    }

    String toString() {
        return this.username
    }

}

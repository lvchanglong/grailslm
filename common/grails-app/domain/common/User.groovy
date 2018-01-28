package common

/**
 * 用户管理
 */
class User {

    Role role //角色
    
    String username //账号
    String password //密码
    String nickname = "匿名"//昵称
    String state = "开启" //状态（开启|关闭）
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    ArrayList functions //不保存到数据库中
    static transients = ["functions"] //功能集合

    static mapping = {
        table "base_user"
        id column: "id"
        version column: "version"

        username column:"username"
        password column:"password"
        nickname column:"nickname"
        state column:"state"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        role column:"role_id"

        sort id:"desc"
    }

    static constraints = {
        username(blank:false, nullable:false, unique:true)
        password(blank:false, nullable:false)
        nickname(blank:false, nullable:false)
        state(blank:false, nullable:false, inList: ["开启", "关闭"])
        role(nullable:true)
    }

    String toString() {
        this.username
    }

    /**
     * 用户状态(true开启，false关闭)
     */
    def getStateToBoolean() {
        return this.state.equalsIgnoreCase("开启")
    }

    /**
     * 开发人员
     */
    def isSuperman() {
        return this.username.equalsIgnoreCase("superman")
    }

    /**
     * 是否管理员
     */
    def isAdmin() {
        if(this.isSuperman()) {
            return true
        }
        if(this.role) {
            return this.role.isAdmin()
        }
        return false
    }

    /**
     * 禁止删除角色
     */
    def banDelete() {
        return this.username in ["admin", "superman"]
    }

    /**
     * 禁止修改角色
     */
    def banEdit() {
        return this.username in ["admin", "superman"]
    }

    /**
     * 列出当前用户所能拥有的全部功能
     */
    def listFunctions() {
        if(this.isSuperman()) {
            return Function.list()
        }
        if(this.role) {
            return this.role.listFunctions()
        }
        return null
    }

    /**
     * 列出当前用户所能拥有的全部功能（分组）
     */
    def listFunctionsToGroup() {
        return FunGroup.toGroup(this.listFunctions())
    }

    /**
     * 是否只读？true或false
     */
    boolean onlyView(def params) {
        if(this.isAdmin()) {
            return false
        }
        if(this.role) {
            return this.role.onlyView(params)
        }
        return false
    }

    def beforeInsert() {
        def md5 = this.password.encodeAsMD5()
        this.password = md5 //密码加密
    }

    def afterLoad() {
        this.functions = this.listFunctions() //载入权限
    }

}

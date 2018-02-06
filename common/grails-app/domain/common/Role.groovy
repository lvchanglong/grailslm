package common

/**
 * 角色管理（关键字：功能二次分配）
 */
class Role {

    SortedSet functions //解决“多对多”关系中，默认排序报错问题
    static hasMany = [functions:Function] //多对多关系

    String name //名称
    String permission = "" //权限限定
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_role"
        id column: "id"
        version column: "version"

        name column:"name"
        permission column:"permission", sqlType:"Text"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        functions column: 'role_id', joinTable: 'base_role_function_association'

        sort id:"desc"
    }

    static constraints = {
        name(blank:false, nullable:false, unique:true)
        permission(blank:true, nullable:true)
    }

    String toString() {
        return this.name
    }

    /**
     * 获得管理员角色
     */
    static def getAdmin() {
        return Role.findByName("管理员")
    }

    /**
     * 获得普通用户角色
     */
    static def getVip() {
        return Role.findByName("普通用户")
    }

    /**
     * 是否管理员
     */
    def isAdmin() {
        return this.name.equalsIgnoreCase("管理员")
    }

    /**
     * 禁止删除角色
     */
    def banDelete() {
        return this.name in ["管理员", "普通用户"]
    }

    /**
     * 禁止修改角色
     */
    def banEdit() {
        return this.name in ["管理员"]
    }

    /**
     * 获取平台下当前角色已被分配的所有功能
     */
    def listFunctions() {
        def platform = Platform.getActive() //获取当前开启中的平台
        if(!platform) {
            return Function.list() //无开启中的平台，进入测试模式
        }
        if(this.isAdmin()) {
            return platform.functions //平台下可用功能
        }
        return Function.where {
            platforms {
                id == platform.id
            } && roles {
                id == this.id
            }
        }.list()
    }

    /**
     * 是否只读？true或false
     */
    boolean onlyView(def params) {
        if(this.permission && params.controller) {
            return "只读".equalsIgnoreCase(this.permission.find("(?<=${params.controller}:).*?(?=;)")?.trim())
        }
        return false
    }

}

package common

/**
 * 功能管理（关键字：功能开闭）
 */
class Function implements Comparable {

    static belongsTo = [Platform, Role] //新建平台时选功能，新建角色时选功能
    static hasMany = [platforms: Platform, roles: Role]
    FunGroup funGroup //所属分组

    String name //名称
    String controllerName //控制器名称
    String actionName = "index"//方法名称
    Integer zIndex = 0//迭代次序
    String state = "开启" //状态（开启|关闭）
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_function"
        id column: "id"
        version column: "version"

        name column:"name"
        controllerName column:"controller_name"
        actionName column:"action_name"
        zIndex column: "z_index"
        state column:"state"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        platforms column: 'function_id', joinTable: 'base_platform_function_association'
        roles column: 'function_id', joinTable: 'base_role_function_association'
        funGroup column:"function_group_id"

        sort zIndex: "asc"
    }

    static constraints = {
        name(blank:false, nullable:false, unique: true)
        controllerName(blank:false, nullable:false)
        actionName(blank:false, nullable:false)
        zIndex(nullable:false)
        state(blank:false, nullable:false, inList: ["开启", "关闭"])
        funGroup(nullable:true)
    }

    String toString() {
        return this.name + (this.getStateToBoolean()?"":"（关闭）")
    }

    @Override
    int compareTo(Object o) {
        return this.zIndex.compareTo(o.zIndex)
    }

    /**
     * 功能状态(true开启，false关闭)
     */
    def getStateToBoolean() {
        return this.state.equalsIgnoreCase("开启")
    }

    /**
     * 功能列表（分组）-“superman”
     */
    static def listToGroup() {
        def groups = Function.list().groupBy {elem->
            elem.funGroup?.name
        }
        def linkedHashMap = new LinkedHashMap()
        FunGroup.list().each {group->
            def key = group.name //分組名称
            def value = groups.get(key)
            if(value) {
                linkedHashMap.put(key, value)
            }
        }
        def value = groups.get(null)
        if(value) {
            linkedHashMap.put("其他", value)
        }
        return linkedHashMap
    }

}

package common

/**
 * 平台管理（关键字：功能分配）
 */
class Platform {

    SortedSet functions //解决多对多关系中，一方数据为空时，默认排序报错问题
    static hasMany = [functions: Function] //多对多关系（一个平台对应多个功能，一个功能对应多个平台）

    String name //名称
    String state = "关闭" //状态（开启|关闭），同一时间最多只能开启1个平台
    String layout = "main" //布局模板
    String css = "application.css" //页面样式
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_platform"
        id column:"id"
        version column: "version"

        name column:"name"
        state column:"state"
        layout column:"layout"
        css column:"css"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        functions column: 'platform_id', joinTable: 'base_platform_function_association'

        sort id:"desc"
    }

    static constraints = {
        name(blank:false, nullable:false, unique: true)
        state(blank:false, nullable:false, inList: ["关闭", "开启"])
        layout(blank:false, nullable:false, inList: ["main"])
        css(blank:false, nullable:false, inList: ["application.css"])
    }

    String toString() {
        return this.name
    }

    /**
     * 平台状态(true开启，false关闭)
     */
    def getStateToBoolean() {
        return this.state.equalsIgnoreCase("开启")
    }

    /**
     * 获取一个开启中的平台(最多有且只有1个开启中的平台)
     */
    static Platform getActive() {
        return Platform.where {
            state == "开启"
        }.find()
    }

    /**
     * 虚拟一个开启中的平台（无开启中的平台，进入功能测试模式）
     */
    static Platform mockActive() {
        return new Platform([name:"系统测试", state:"开启"])
    }

    /**
     * 获取开启中平台的所有功能(存在两种情况：运营模式、测试模式)-“管理员”
     */
    static def listActivePlatformFunctions() {
        def platform = Platform.getActive()
        if(platform) {
            return platform.functions //运营模式
        }
        return Function.list() //测试模式
    }

    /**
     * 获取开启中平台的所有功能（分组）-“管理员”
     */
    static def listActivePlatformFunctionsToGroup() {
        def groups = this.listActivePlatformFunctions().groupBy {elem->
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

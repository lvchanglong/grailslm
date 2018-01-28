package common

/**
 * 功能分组管理（关键字：功能分组）
 */
class FunGroup {

    SortedSet functions //解决“单向一对多”关系中，默认排序报错问题
    static hasMany = [functions: Function]

    String name //名称
    Integer zIndex = 0//迭代次序
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_functiongroup"
        id column: "id"
        version column: "version"

        name column:"name"
        zIndex column: "z_index"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        functions joinTable: [name: 'base_functiongroup_function_association',
                              key: 'functiongroup_id',
                              column: 'function_id']

        sort zIndex: "asc"
    }

    static constraints = {
        name(blank:false, nullable:false, unique: true)
        zIndex(nullable:false)
    }

    String toString() {
        return this.name
    }

    /**
     * 功能分组
     * @param funs
     */
    static def toGroup(def funs) {
        def linkedHashMap = new LinkedHashMap()
        def elseFunctions = new ArrayList(funs) //未分组功能
        FunGroup.list().each {group->
            def key = group.name //分組名称
            def value = group.functions.intersect(funs) //交集
            if(value) {
                linkedHashMap.put(key, value)
                elseFunctions.removeAll(value)
            }
        }
        if(elseFunctions) {
            linkedHashMap.put("其他", elseFunctions)
        }
        return linkedHashMap
    }

}

package common

/**
 * 功能分组管理（关键字：功能分组）
 */
class FunGroup {

    static hasMany = [functions: Function]

    String name //名称
    Integer zIndex = 0//迭代次序
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_function_group"
        id column: "id"
        version column: "version"

        name column:"name"
        zIndex column: "z_index"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        functions sort: "zIndex", order: "asc"
        sort zIndex: "asc"
    }

    static constraints = {
        name(blank:false, nullable:false, unique: true)
        zIndex(nullable:false)
    }

    String toString() {
        return this.name
    }

}

package lvchanglong

/**
 * 文档封面-数据
 */
class DocumentationFace {

    static belongsTo = [parent: Documentation]

    byte[] bytes //二进制数据

    static mapping = {
        table "documentation_face"
        id column: "id"
        version column: "version"

        bytes column: "bytes"
    }

    static constraints = {
        parent(nullable: true)
        bytes(nullable: false, maxSize: 1024 * 1024 * 200) //maxSize设置，不可省略（200M，2 0000 0000）
    }

}

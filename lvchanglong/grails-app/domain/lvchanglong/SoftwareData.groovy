package lvchanglong

/**
 * 软件-数据
 */
class SoftwareData {

    static belongsTo = [parent: Software]

    byte[] bytes //二进制数据

    static mapping = {
        table "software_data"
        id column: "id"
        version column: "version"

        bytes column: "bytes"
    }

    static constraints = {
        parent(nullable: true)
        bytes(nullable: false, maxSize: 1024 * 1024 * 700) //maxSize设置，不可省略（700M，7 0000 0000）
    }

}

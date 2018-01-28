package common

/**
 * 图片数据
 */
class SlideData {

    static belongsTo = [parent: Slide]

    byte[] bytes //二进制

    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_slidedata"
        id column: "id"
        version column: "version"

        parent column:"parent_id"
        bytes column:"bytes", sqlType:"LongBlob"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        sort id:"desc"
    }

    static constraints = {
        parent(nullable:false)
        bytes(nullable:false, maxSize:1024 * 1024 * 30) //30M
    }

}

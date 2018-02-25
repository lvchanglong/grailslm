package common

/**
 * 文件管理(文件|文件夹)
 */
class MimeFile {

    static hasMany = [children:MimeFile]
    static belongsTo = [parent:MimeFile]

    User creater//创建人

    MimeFileData data //文件数据
    String filename //文件名称
    String remark //备注

    String type = "文件"//类型（文件|文件夹）
    String classification = "公开" //密级（公开|私有），公开时能够被他人看到~
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "base_mimefile"
        id column: "id"
        version column: "version"

        filename column:"filename"
        remark column:"remark", sqlType:"Text"
        type column:"type"
        classification column:"classification"
        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        parent column:"parent_id"
        creater column:"creater_id"
        data column:"data_id"
    }

    static constraints = {
        filename(blank:false, nullable:false)
        remark(blank:true, nullable:true)
        type(blank:false, nullable:false, inList:["文件", "文件夹"])
        classification(blank:false, nullable:false, inList:["公开", "私有"])
        parent(nullable:true)
        creater(nullable:false)
        data(nullable:true)
    }

    String toString() {
        return this.filename
    }

    /**
     * 是否是文件夹
     */
    def isDir() {
        return this.type.equalsIgnoreCase("文件夹")
    }

    /**
     * 是否开放访问？
     */
    def isPublic(def uid) {
        if(this.classification.equalsIgnoreCase("公开")) {
            return true
        }
        if(this.createrId.equals(uid)) { //创建者
            return true
        }
        if(User.get(uid).isAdmin()) {
            return true
        }
        return false
    }

    /**
     * 当前文件或文件夹所在路径
     */
    def listParents() {
        ArrayList array = new ArrayList()
        array.add(this)
        def inst = this.parent
        while (inst) {
            array.add(inst)
            inst = inst?.parent
        }
        return array.reverse()
    }

    /**
     * 当前文件或文件夹所在路径（字符串）
     */
    def listParentsToString() {
        String absolutePath = "" //绝对路径
        this.listParents().each {location->
            absolutePath += "/${location.filename}"
        }
        return absolutePath
    }

}

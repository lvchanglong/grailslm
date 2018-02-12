package extension

/**
 * 表格HTML
 */
class ExcelHtml {

    String number //代号
    String tableName //表名
    String content	//内容
    Date dateCreated //创建时间
    Date lastUpdated //更新时间

    static mapping = {
        table "extension_excelhtml"
        id column: "id"
        version column: "version"

        content column:"number"
        content column:"tableName"
        content column:"content", sqlType:"LongText"

        dateCreated column:"date_created"
        lastUpdated column:"last_updated"

        sort id:"desc"
    }

    static constraints = {
        number(blank:false, nullable:false)
        tableName(blank:false, nullable:false)
        content(blank:false, nullable:false)
    }

}

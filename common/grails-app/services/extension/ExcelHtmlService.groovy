package extension

import grails.gorm.services.Service

@Service(ExcelHtml)
interface ExcelHtmlService {

    ExcelHtml get(Serializable id)

    List<ExcelHtml> list(Map args)

    Long count()

    void delete(Serializable id)

    ExcelHtml save(ExcelHtml excelHtml)

}
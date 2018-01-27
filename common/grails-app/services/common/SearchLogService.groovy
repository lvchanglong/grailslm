package common

import grails.gorm.services.Service

@Service(SearchLog)
interface SearchLogService {

    SearchLog get(Serializable id)

    List<SearchLog> list(Map args)

    Long count()

    void delete(Serializable id)

    SearchLog save(SearchLog searchLog)

}
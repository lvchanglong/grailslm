package common

import grails.gorm.services.Service

@Service(Function)
interface FunctionService {

    Function get(Serializable id)

    List<Function> list(Map args)

    Long count()

    void delete(Serializable id)

    Function save(Function function)

}
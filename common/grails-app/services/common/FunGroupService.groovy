package common

import grails.gorm.services.Service

@Service(FunGroup)
interface FunGroupService {

    FunGroup get(Serializable id)

    List<FunGroup> list(Map args)

    Long count()

    void delete(Serializable id)

    FunGroup save(FunGroup funGroup)

}
package common

import grails.gorm.services.Service

@Service(LoginLog)
interface LoginLogService {

    LoginLog get(Serializable id)

    List<LoginLog> list(Map args)

    Long count()

    void delete(Serializable id)

    LoginLog save(LoginLog loginLog)

}
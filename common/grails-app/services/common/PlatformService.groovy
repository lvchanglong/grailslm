package common

import grails.gorm.services.Service

@Service(Platform)
interface PlatformService {

    Platform get(Serializable id)

    List<Platform> list(Map args)

    Long count()

    void delete(Serializable id)

    Platform save(Platform platform)

}
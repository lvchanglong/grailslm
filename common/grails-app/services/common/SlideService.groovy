package common

import grails.gorm.services.Service

@Service(Slide)
interface SlideService {

    Slide get(Serializable id)

    List<Slide> list(Map args)

    Long count()

    void delete(Serializable id)

    Slide save(Slide slide)

}
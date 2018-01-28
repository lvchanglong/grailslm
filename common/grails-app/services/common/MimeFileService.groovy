package common

import grails.gorm.services.Service

@Service(MimeFile)
interface MimeFileService {

    MimeFile get(Serializable id)

    List<MimeFile> list(Map args)

    Long count()

    void delete(Serializable id)

    MimeFile save(MimeFile mimeFile)

}
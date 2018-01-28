package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MimeFileServiceSpec extends Specification {

    MimeFileService mimeFileService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MimeFile(...).save(flush: true, failOnError: true)
        //new MimeFile(...).save(flush: true, failOnError: true)
        //MimeFile mimeFile = new MimeFile(...).save(flush: true, failOnError: true)
        //new MimeFile(...).save(flush: true, failOnError: true)
        //new MimeFile(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //mimeFile.id
    }

    void "test get"() {
        setupData()

        expect:
        mimeFileService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MimeFile> mimeFileList = mimeFileService.list(max: 2, offset: 2)

        then:
        mimeFileList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        mimeFileService.count() == 5
    }

    void "test delete"() {
        Long mimeFileId = setupData()

        expect:
        mimeFileService.count() == 5

        when:
        mimeFileService.delete(mimeFileId)
        sessionFactory.currentSession.flush()

        then:
        mimeFileService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MimeFile mimeFile = new MimeFile()
        mimeFileService.save(mimeFile)

        then:
        mimeFile.id != null
    }
}

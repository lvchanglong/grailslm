package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PlatformServiceSpec extends Specification {

    PlatformService platformService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Platform(...).save(flush: true, failOnError: true)
        //new Platform(...).save(flush: true, failOnError: true)
        //Platform platform = new Platform(...).save(flush: true, failOnError: true)
        //new Platform(...).save(flush: true, failOnError: true)
        //new Platform(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //platform.id
    }

    void "test get"() {
        setupData()

        expect:
        platformService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Platform> platformList = platformService.list(max: 2, offset: 2)

        then:
        platformList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        platformService.count() == 5
    }

    void "test delete"() {
        Long platformId = setupData()

        expect:
        platformService.count() == 5

        when:
        platformService.delete(platformId)
        sessionFactory.currentSession.flush()

        then:
        platformService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Platform platform = new Platform()
        platformService.save(platform)

        then:
        platform.id != null
    }
}

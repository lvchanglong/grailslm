package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FunGroupServiceSpec extends Specification {

    FunGroupService funGroupService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new FunGroup(...).save(flush: true, failOnError: true)
        //new FunGroup(...).save(flush: true, failOnError: true)
        //FunGroup funGroup = new FunGroup(...).save(flush: true, failOnError: true)
        //new FunGroup(...).save(flush: true, failOnError: true)
        //new FunGroup(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //funGroup.id
    }

    void "test get"() {
        setupData()

        expect:
        funGroupService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<FunGroup> funGroupList = funGroupService.list(max: 2, offset: 2)

        then:
        funGroupList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        funGroupService.count() == 5
    }

    void "test delete"() {
        Long funGroupId = setupData()

        expect:
        funGroupService.count() == 5

        when:
        funGroupService.delete(funGroupId)
        sessionFactory.currentSession.flush()

        then:
        funGroupService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        FunGroup funGroup = new FunGroup()
        funGroupService.save(funGroup)

        then:
        funGroup.id != null
    }
}

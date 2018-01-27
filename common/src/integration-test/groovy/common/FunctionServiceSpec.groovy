package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class FunctionServiceSpec extends Specification {

    FunctionService functionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Function(...).save(flush: true, failOnError: true)
        //new Function(...).save(flush: true, failOnError: true)
        //Function function = new Function(...).save(flush: true, failOnError: true)
        //new Function(...).save(flush: true, failOnError: true)
        //new Function(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //function.id
    }

    void "test get"() {
        setupData()

        expect:
        functionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Function> functionList = functionService.list(max: 2, offset: 2)

        then:
        functionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        functionService.count() == 5
    }

    void "test delete"() {
        Long functionId = setupData()

        expect:
        functionService.count() == 5

        when:
        functionService.delete(functionId)
        sessionFactory.currentSession.flush()

        then:
        functionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Function function = new Function()
        functionService.save(function)

        then:
        function.id != null
    }
}

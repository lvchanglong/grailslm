package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LoginLogServiceSpec extends Specification {

    LoginLogService loginLogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new LoginLog(...).save(flush: true, failOnError: true)
        //new LoginLog(...).save(flush: true, failOnError: true)
        //LoginLog loginLog = new LoginLog(...).save(flush: true, failOnError: true)
        //new LoginLog(...).save(flush: true, failOnError: true)
        //new LoginLog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //loginLog.id
    }

    void "test get"() {
        setupData()

        expect:
        loginLogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<LoginLog> loginLogList = loginLogService.list(max: 2, offset: 2)

        then:
        loginLogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        loginLogService.count() == 5
    }

    void "test delete"() {
        Long loginLogId = setupData()

        expect:
        loginLogService.count() == 5

        when:
        loginLogService.delete(loginLogId)
        sessionFactory.currentSession.flush()

        then:
        loginLogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        LoginLog loginLog = new LoginLog()
        loginLogService.save(loginLog)

        then:
        loginLog.id != null
    }
}

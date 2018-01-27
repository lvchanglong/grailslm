package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SearchLogServiceSpec extends Specification {

    SearchLogService searchLogService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SearchLog(...).save(flush: true, failOnError: true)
        //new SearchLog(...).save(flush: true, failOnError: true)
        //SearchLog searchLog = new SearchLog(...).save(flush: true, failOnError: true)
        //new SearchLog(...).save(flush: true, failOnError: true)
        //new SearchLog(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //searchLog.id
    }

    void "test get"() {
        setupData()

        expect:
        searchLogService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SearchLog> searchLogList = searchLogService.list(max: 2, offset: 2)

        then:
        searchLogList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        searchLogService.count() == 5
    }

    void "test delete"() {
        Long searchLogId = setupData()

        expect:
        searchLogService.count() == 5

        when:
        searchLogService.delete(searchLogId)
        sessionFactory.currentSession.flush()

        then:
        searchLogService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SearchLog searchLog = new SearchLog()
        searchLogService.save(searchLog)

        then:
        searchLog.id != null
    }
}

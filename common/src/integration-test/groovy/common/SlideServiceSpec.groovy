package common

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SlideServiceSpec extends Specification {

    SlideService slideService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Slide(...).save(flush: true, failOnError: true)
        //new Slide(...).save(flush: true, failOnError: true)
        //Slide slide = new Slide(...).save(flush: true, failOnError: true)
        //new Slide(...).save(flush: true, failOnError: true)
        //new Slide(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //slide.id
    }

    void "test get"() {
        setupData()

        expect:
        slideService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Slide> slideList = slideService.list(max: 2, offset: 2)

        then:
        slideList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        slideService.count() == 5
    }

    void "test delete"() {
        Long slideId = setupData()

        expect:
        slideService.count() == 5

        when:
        slideService.delete(slideId)
        sessionFactory.currentSession.flush()

        then:
        slideService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Slide slide = new Slide()
        slideService.save(slide)

        then:
        slide.id != null
    }
}

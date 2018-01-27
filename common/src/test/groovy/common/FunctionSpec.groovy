package common

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class FunctionSpec extends Specification implements DomainUnitTest<Function> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}

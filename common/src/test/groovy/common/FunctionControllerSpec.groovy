package common

import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import grails.validation.ValidationException
import spock.lang.*

class FunctionControllerSpec extends Specification implements ControllerUnitTest<FunctionController>, DomainUnitTest<Function> {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * list(_) >> []
            1 * count() >> 0
        }

        when:"The index action is executed"
        controller.index()

        then:"The model is correct"
        !model.functionList
        model.functionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
        controller.create()

        then:"The model is correctly created"
        model.function!= null
    }

    void "Test the save action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        controller.save(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/function/index'
        flash.message != null
    }

    void "Test the save action correctly persists"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * save(_ as Function)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        populateValidParams(params)
        def function = new Function(params)
        function.id = 1

        controller.save(function)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/function/show/1'
        controller.flash.message != null
    }

    void "Test the save action with an invalid instance"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * save(_ as Function) >> { Function function ->
                throw new ValidationException("Invalid instance", function.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def function = new Function()
        controller.save(function)

        then:"The create view is rendered again with the correct model"
        model.function != null
        view == 'create'
    }

    void "Test the show action with a null id"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.show(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the show action with a valid id"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * get(2) >> new Function()
        }

        when:"A domain instance is passed to the show action"
        controller.show(2)

        then:"A model is populated containing the domain instance"
        model.function instanceof Function
    }

    void "Test the edit action with a null id"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * get(null) >> null
        }

        when:"The show action is executed with a null domain"
        controller.edit(null)

        then:"A 404 error is returned"
        response.status == 404
    }

    void "Test the edit action with a valid id"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * get(2) >> new Function()
        }

        when:"A domain instance is passed to the show action"
        controller.edit(2)

        then:"A model is populated containing the domain instance"
        model.function instanceof Function
    }


    void "Test the update action with a null instance"() {
        when:"Save is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then:"A 404 error is returned"
        response.redirectedUrl == '/function/index'
        flash.message != null
    }

    void "Test the update action correctly persists"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * save(_ as Function)
        }

        when:"The save action is executed with a valid instance"
        response.reset()
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        populateValidParams(params)
        def function = new Function(params)
        function.id = 1

        controller.update(function)

        then:"A redirect is issued to the show action"
        response.redirectedUrl == '/function/show/1'
        controller.flash.message != null
    }

    void "Test the update action with an invalid instance"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * save(_ as Function) >> { Function function ->
                throw new ValidationException("Invalid instance", function.errors)
            }
        }

        when:"The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(new Function())

        then:"The edit view is rendered again with the correct model"
        model.function != null
        view == 'edit'
    }

    void "Test the delete action with a null instance"() {
        when:"The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then:"A 404 is returned"
        response.redirectedUrl == '/function/index'
        flash.message != null
    }

    void "Test the delete action with an instance"() {
        given:
        controller.functionService = Mock(FunctionService) {
            1 * delete(2)
        }

        when:"The domain instance is passed to the delete action"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(2)

        then:"The user is redirected to index"
        response.redirectedUrl == '/function/index'
        flash.message != null
    }
}







<%@ page import="common.Function;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createFunctionModal">
    <span class="glyphicon glyphicon-plus"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createFunctionModal" tabindex="-1" role="dialog" aria-labelledby="createFunctionModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="createFunctionForm" url="[controller:params.controller, action: 'save']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="createFunctionModalTitle">信息添加</h3>
                </div>
                <div class="modal-body">
                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="name" class="col-sm-2">名称
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="name" value="${instance.name}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="controllerName" class="col-sm-2">控制器名称
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="controllerName" value="${instance.controllerName}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="actionName" class="col-sm-2">方法名称
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="actionName" value="${instance.actionName}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="zIndex" class="col-sm-2">迭代次序
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="zIndex" value="${instance.zIndex}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="state" class="col-sm-2">状态
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Function.constrainedProperties.state.inList}" name="state" value="${instance.state}" valueMessagePrefix="function.state" class="form-control"/>
                            </div>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-lg btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
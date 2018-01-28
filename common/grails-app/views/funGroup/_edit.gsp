<%@ page import="common.Function; common.FunGroup;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editFunGroupModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editFunGroupModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editFunGroupModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="editFunGroupForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="editFunGroupModal${instance.id}Title">信息修改</h3>
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
                            <label for="zIndex" class="col-sm-2">迭代次序
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="zIndex" value="${instance.zIndex}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="functions" class="col-sm-2">功能列表
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <g:each in="${Function.list()}" status="i" var="fun">
                                        <div class="col-sm-3 checkbox">
                                            <label style="min-width:130px;">
                                                <g:checkBox name="functions" value="${fun.id}" checked="${instance.functions.contains(fun)}"/>
                                                ${fun}
                                            </label>
                                        </div>
                                    </g:each>
                                </div>
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
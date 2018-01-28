<%@ page import="common.Feedback;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editFeedbackModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editFeedbackModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editFeedbackModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="editFeedbackForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="editFeedbackModal${instance.id}Title">信息修改</h3>
                </div>
                <div class="modal-body">
                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="username" class="col-sm-2">账号
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="username" value="${instance.username}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2">内容
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="content" value="${instance.content}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="type" class="col-sm-2">状态
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Feedback.constrainedProperties.type.inList}" name="type" value="${instance.type}" valueMessagePrefix="feedback.type" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ip" class="col-sm-2">ip
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="ip" value="${instance.ip}" class="form-control"/>
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
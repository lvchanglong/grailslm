<%@ page import="common.Feedback;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editFeedbackModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editFeedbackModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editFeedbackModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="editFeedbackForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editFeedbackModal${instance.id}Title">信息修改</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="username">账号
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="username" value="${instance.username}" required="required"/>
                        </div>
                        <div class="fieldcontain required">
                            <label for="content">内容
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="content" value="${instance.content}" required="required"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="type">类型</label>
                            <g:select from="${Feedback.constrainedProperties.type.inList}" name="type" value="${instance.type}" valueMessagePrefix="feedback.type"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="ip">ip</label>
                            <g:textField name="ip" value="${instance.ip}"/>
                        </div>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-sm btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
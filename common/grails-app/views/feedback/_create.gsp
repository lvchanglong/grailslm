<%@ page import="common.Feedback;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createFeedbackModal">
    <span class="fui-plus-circle"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createFeedbackModal" tabindex="-1" role="dialog" aria-labelledby="createFeedbackModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="createFeedbackForm" url="[controller:params.controller, action: 'save']" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="createFeedbackModalTitle">信息添加</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <fieldset class="form">
                            <div class="fieldcontain required">
                                <label for="username">账号
                                    <span class="required-indicator">*</span>
                                </label>
                                <g:textField name="username" value="" required="required"/>
                            </div>
                            <div class="fieldcontain required">
                                <label for="content">内容
                                    <span class="required-indicator">*</span>
                                </label>
                                <g:textField name="content" value="" required="required"/>
                            </div>
                            <div class="fieldcontain">
                                <label for="type">类型</label>
                                <g:select from="${Feedback.constrainedProperties.type.inList}" name="type" value="" valueMessagePrefix="feedback.type"/>
                            </div>
                            <div class="fieldcontain">
                                <label for="ip">ip</label>
                                <g:textField name="ip" value=""/>
                            </div>
                        </fieldset>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-sm btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
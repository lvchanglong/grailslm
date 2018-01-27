<%@ page import="common.SearchLog;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editSearchLogModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editSearchLogModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editSearchLogModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="editSearchLogForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editSearchLogModal${instance.id}Title">信息修改</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <div class="fieldcontain">
                            <label for="platform">平台</label>
                            <g:select from="${SearchLog.constrainedProperties.platform.inList}" name="platform" value="${instance.platform}"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="type">检索类型</label>
                            <g:textField name="type" value="${instance.type}"/>
                        </div>
                        <div class="fieldcontain required">
                            <label for="q">检索内容
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="q" value="${instance.q}" required="required"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="cnt">结果数</label>
                            <g:textField name="cnt" value="${instance.cnt}" required=""/>
                        </div>
                        <div class="fieldcontain">
                            <label for="ip">ip</label>
                            <g:textField name="ip" value="${instance.ip}" required=""/>
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
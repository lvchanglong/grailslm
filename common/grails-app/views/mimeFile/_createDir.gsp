<%@ page import="common.MimeFile;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createMimeDirModal">
    <span class="glyphicon glyphicon-plus"></span>&nbsp;创建文件夹
</a>
<div class="modal fade" id="createMimeDirModal" tabindex="-1" role="dialog" aria-labelledby="createMimeDirModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="createMimeDirForm" url="[controller:params.controller, action: 'save']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="createMimeDirModalTitle">创建文件夹</h3>
                </div>
                <div class="modal-body">
                    <g:hiddenField name="parent" value="${parent?.id}"/>
                    <g:hiddenField name="type" value="文件夹"/>

                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="filename" class="col-sm-2">文件夹名
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="filename" value="${instance.filename}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="classification" class="col-sm-2">密级
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${MimeFile.constrainedProperties.classification.inList}" name="classification" value="${instance.classification}" valueMessagePrefix="mimeFile.classification" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="remark" class="col-sm-2">备注
                            </label>
                            <div class="col-sm-10">
                                <g:textArea name="remark" value="暂无" class="form-control"/>
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
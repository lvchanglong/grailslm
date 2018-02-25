<%@ page import="common.MimeFile;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editMimeFileModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editMimeFileModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editMimeFileModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="editMimeFileForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="editMimeFileModal${instance.id}Title">信息修改</h3>
                </div>
                <div class="modal-body">
                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="filename" class="col-sm-2">名称
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
                                <g:textArea name="remark" value="${instance.remark}" class="form-control"/>
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
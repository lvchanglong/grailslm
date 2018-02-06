<%@ page import="common.Function; common.Platform;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editPlatformModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editPlatformModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editPlatformModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="editPlatformForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="editPlatformModal${instance.id}Title">信息修改</h3>
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
                            <label for="state" class="col-sm-2">状态
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Platform.constrainedProperties.state.inList}" name="state" value="${instance.state}" valueMessagePrefix="platform.state" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="layout" class="col-sm-2">布局模板
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Platform.constrainedProperties.layout.inList}" name="layout" value="${instance.layout}" valueMessagePrefix="platform.layout" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="css" class="col-sm-2">页面样式
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Platform.constrainedProperties.css.inList}" name="css" value="${instance.css}" valueMessagePrefix="platform.css" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="image" class="col-sm-2">预览
                            </label>
                            <div class="col-sm-10">
                                <img id="image" src="${createLink(controller:params.controller, action:'image', params:[id: instance.id])}" style="width:100px;height:100px;" class="black-screen"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="file" class="col-sm-2">上传
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <input type="file" id="file" name="file" value="" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="functions" class="col-sm-2">功能列表
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <g:each in="${Function.list()}" status="i" var="one">
                                        <div class="col-sm-3 checkbox">
                                            <label class="checkbox-label">
                                                <g:checkBox name="functions" value="${one.id}" checked="${instance.functions.contains(one)}"/>
                                                ${one}
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
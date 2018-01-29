<%@ page import="common.Slide;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createSlideModal">
    <span class="glyphicon glyphicon-plus"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createSlideModal" tabindex="-1" role="dialog" aria-labelledby="createSlideModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="createSlideForm" url="[controller:params.controller, action: 'save']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="createSlideModalTitle">信息添加</h3>
                </div>
                <div class="modal-body">
                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="title" class="col-sm-2">标题
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="title" value="${instance.title}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2">url
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="url" value="${instance.url}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2">内容
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="content" value="${instance.content}" class="form-control"/>
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
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-lg btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
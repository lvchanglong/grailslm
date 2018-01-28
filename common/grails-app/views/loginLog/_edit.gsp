<%@ page import="common.LoginLog;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editLoginLogModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editLoginLogModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editLoginLogModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="editLoginLogForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="editLoginLogModal${instance.id}Title">信息修改</h3>
                </div>
                <div class="modal-body">
                    <fieldset class="modal-fieldset">
                        <div class="form-group">
                            <label for="username" class="col-sm-2">账号
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" name="username" value="${instance.username}" required="required" id="username" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nickname" class="col-sm-2">昵称</label>
                            <div class="col-sm-10">
                                <input type="text" name="nickname" value="${instance.nickname}" id="nickname" class="form-control col-sm-10">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="rolename" class="col-sm-2">角色</label>
                            <div class="col-sm-10">
                                <input type="text" name="rolename" value="${instance.rolename}" id="rolename" class="form-control col-sm-10">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ip" class="col-sm-2">ip</label>
                            <div class="col-sm-10">
                                <input type="text" name="ip" value="${instance.ip}" id="ip" class="form-control col-sm-10">
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
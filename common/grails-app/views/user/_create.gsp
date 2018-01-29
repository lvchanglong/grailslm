<%@ page import="common.Role; common.User;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createUserModal">
    <span class="glyphicon glyphicon-plus"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createUserModal" tabindex="-1" role="dialog" aria-labelledby="createUserModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="createUserForm" url="[controller:params.controller, action: 'save']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="createUserModalTitle">信息添加</h3>
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
                            <label for="password" class="col-sm-2">密码
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="password" value="${instance.password}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="nickname" class="col-sm-2">昵称
                                <span class="required-indicator">*</span>
                            </label>
                            <div class="col-sm-10">
                                <g:textField name="nickname" value="${instance.nickname}" required="required" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="state" class="col-sm-2">状态
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${User.constrainedProperties.state.inList}" name="state" value="${instance.state}" valueMessagePrefix="user.state" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="state" class="col-sm-2">角色
                            </label>
                            <div class="col-sm-10">
                                <g:select from="${Role.list()}" optionKey="id" optionValue="name" name="role" value="${instance.roleId}" valueMessagePrefix="user.role" class="form-control"/>
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
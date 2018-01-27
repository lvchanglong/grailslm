<%@ page import="common.LoginLog;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editLoginLogModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editLoginLogModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editLoginLogModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="editLoginLogForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editLoginLogModal${instance.id}Title">信息修改</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="username">账号
                                <span class="required-indicator">*</span>
                            </label>
                            <input type="text" name="username" value="${instance.username}" required="required" id="username">
                        </div>
                        <div class="fieldcontain">
                            <label for="nickname">昵称</label>
                            <input type="text" name="nickname" value="${instance.nickname}" id="nickname">
                        </div>
                        <div class="fieldcontain">
                            <label for="rolename">角色</label>
                            <input type="text" name="rolename" value="${instance.rolename}" id="rolename">
                        </div>
                        <div class="fieldcontain">
                            <label for="ip">ip</label>
                            <input type="text" name="ip" value="${instance.ip}" id="ip">
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
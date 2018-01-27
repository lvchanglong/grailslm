<%@ page import="common.LoginLog;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createLoginLogModal">
    <span class="fui-plus-circle"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createLoginLogModal" tabindex="-1" role="dialog" aria-labelledby="createLoginLogModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="createLoginLogForm" url="[controller:params.controller, action: 'save']" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="createLoginLogModalTitle">信息添加</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <fieldset class="form">
                            <div class="fieldcontain required">
                                <label for="username">账号
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="username" value="" required="required" id="username">
                            </div>
                            <div class="fieldcontain">
                                <label for="nickname">昵称</label>
                                <input type="text" name="nickname" value="" id="nickname">
                            </div>
                            <div class="fieldcontain">
                                <label for="rolename">角色</label>
                                <input type="text" name="rolename" value="" id="rolename">
                            </div>
                            <div class="fieldcontain">
                                <label for="ip">ip</label>
                                <input type="text" name="ip" value="" id="ip">
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
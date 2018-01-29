<%@ page import="common.Platform; common.Role;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createRoleModal">
    <span class="glyphicon glyphicon-plus"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createRoleModal" tabindex="-1" role="dialog" aria-labelledby="createRoleModalTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <g:form name="createRoleForm" url="[controller:params.controller, action: 'save']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="createRoleModalTitle">信息添加</h3>
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
                            <label for="permission" class="col-sm-2">权限限定
                            </label>
                            <div class="col-sm-10">
                                <g:textArea name="permission" value="${instance.permission}" class="form-control" placeholder=""/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="functions" class="col-sm-2">功能列表
                            </label>
                            <div class="col-sm-10">
                                <div class="row">
                                    <g:each in="${Platform.listActivePlatformFunctions()}" status="i" var="one">
                                        <div class="col-sm-3 checkbox">
                                            <label class="checkbox-label">
                                                <g:checkBox name="functions" value="${one.id}" checked="false"/>
                                                ${one}
                                            </label>
                                            <a href="javascript:void(0)" id="insertIntoPermissionsLink${instance.id}_${i}" title="设为只读" onclick="insertIntoPermissions${instance.id}_${i}('${one.controllerName}:只读;')" style="color:grey;">
                                                <span class="glyphicon glyphicon-ban-circle"></span>
                                            </a>
                                            <a href="javascript:void(0)" id="insertIntoPermissionsLink${instance.id}_${i}" title="取消只读" onclick="removeFromPermissions${instance.id}_${i}('${one.controllerName}:只读;')" style="color:grey;">
                                                <span class="glyphicon glyphicon-erase"></span>
                                            </a>
                                            <script>
                                                function insertIntoPermissions${instance.id}_${i}(k) {
                                                    var $textArea = jQuery("#permission");
                                                    var value = $textArea.val();
                                                    value += k;
                                                    $textArea.val(value);
                                                }
                                                function removeFromPermissions${instance.id}_${i}(k) {
                                                    var $textArea = jQuery("#permission");
                                                    var value = $textArea.val();
                                                    var reg = new RegExp(k, "g");
                                                    value = value.replace(reg, "");
                                                    if(value != undefined) {
                                                        $textArea.val(value);
                                                    }
                                                }
                                            </script>
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
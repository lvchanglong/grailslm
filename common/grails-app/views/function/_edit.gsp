<%@ page import="common.Function;" %>

<a href="#fakelink" data-toggle="modal" data-target="#editFunctionModal${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="editFunctionModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="editFunctionModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="editFunctionForm${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="editFunctionModal${instance.id}Title">信息修改</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="name">名称
                                <span class="required-indicator">*</span>
                            </label>
                            <input type="text" name="name" value="${instance.name}" required="" id="name">
                        </div>
                        <div class="fieldcontain required">
                            <label for="controllerName">控制器名称
                                <span class="required-indicator">*</span>
                            </label>
                            <input type="text" name="controllerName" value="${instance.controllerName}" id="controllerName">
                        </div>
                        <div class="fieldcontain required">
                            <label for="actionName">方法名称
                                <span class="required-indicator">*</span>
                            </label>
                            <input type="text" name="actionName" value="${instance.actionName}" id="actionName">
                        </div>
                        <div class="fieldcontain required">
                            <label for="zIndex">迭代次序
                                <span class="required-indicator">*</span>
                            </label>
                            <input type="text" name="zIndex" value="${instance.zIndex}" id="zIndex">
                        </div>
                        <div class="fieldcontain">
                            <label for="state">状态</label>
                            <g:select from="${Function.listStates()}" name="state" valueMessagePrefix="function.state"  value="${instance.state}"/>
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
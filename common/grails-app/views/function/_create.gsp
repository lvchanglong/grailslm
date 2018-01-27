<%@ page import="common.Function;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createFunctionModal">
    <span class="fui-plus-circle"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createFunctionModal" tabindex="-1" role="dialog" aria-labelledby="createFunctionModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="createFunctionForm" url="[controller:params.controller, action: 'save']" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="createFunctionModalTitle">信息添加</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <fieldset class="form">
                            <div class="fieldcontain required">
                                <label for="name">名称
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="name" value="" required="" id="name">
                            </div>
                            <div class="fieldcontain required">
                                <label for="controllerName">控制器名称
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="controllerName" value="" id="controllerName">
                            </div>
                            <div class="fieldcontain required">
                                <label for="actionName">方法名称
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="actionName" value="" id="actionName">
                            </div>
                            <div class="fieldcontain required">
                                <label for="zIndex">迭代次序
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="zIndex" value="" id="zIndex">
                            </div>
                            <div class="fieldcontain">
                                <label for="state">状态</label>
                                <g:select from="${Function.listStates()}" name="state" valueMessagePrefix="function.state"/>
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
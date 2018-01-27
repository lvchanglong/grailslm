<%@ page import="common.SearchLog;" %>

<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createSearchLogModal">
    <span class="fui-plus-circle"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createSearchLogModal" tabindex="-1" role="dialog" aria-labelledby="createSearchLogModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="createSearchLogForm" url="[controller:params.controller, action: 'save']" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="createSearchLogModalTitle">信息添加</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <fieldset class="form">
                            <div class="fieldcontain">
                                <label for="platform">平台</label>
                                <g:select from="${SearchLog.constrainedProperties.platform.inList}" name="platform" value=""/>
                            </div>
                            <div class="fieldcontain">
                                <label for="type">检索类型</label>
                                <g:textField name="type" value=""/>
                            </div>
                            <div class="fieldcontain required">
                                <label for="q">检索内容
                                    <span class="required-indicator">*</span>
                                </label>
                                <g:textField name="q" value="" required="required"/>
                            </div>
                            <div class="fieldcontain">
                                <label for="cnt">结果数</label>
                                <g:textField name="cnt" value="" required=""/>
                            </div>
                            <div class="fieldcontain">
                                <label for="ip">ip</label>
                                <g:textField name="ip" value="" required=""/>
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
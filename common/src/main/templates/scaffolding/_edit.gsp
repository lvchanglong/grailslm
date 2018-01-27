${'<%'}@ page import="common.${className};" %>

<a href="#fakelink" data-toggle="modal" data-target="#edit${className}Modal\${instance.id}">
    <span class="glyphicon glyphicon-edit" data-toggle="tooltip" title="修改"></span>
</a>

<div class="modal fade" id="edit${className}Modal\${instance.id}" tabindex="-1" role="dialog" aria-labelledby="edit${className}Modal\${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="edit${className}Form\${instance.id}" url="[controller:params.controller, action: 'update', id:instance.id]" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="edit${className}Modal\${instance.id}Title">信息修改</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <div class="fieldcontain required">
                            <label for="title">Title
                                <span class="required-indicator">*</span>
                            </label>
                            <g:textField name="title" value="\${instance.title}" required="required"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="content">Content</label>
                            <g:textField name="content" value="\${instance.content}"/>
                        </div>
                        <div class="fieldcontain">
                            <label for="state">State</label>
                            <g:select from="\${${className}.constrainedProperties.state.inList}" name="state" value="\${instance.state}" valueMessagePrefix="${propertyName}.state"/>
                        </div>
                        <g:if test="\${instance.data}">
                            <div class="fieldcontain">
                                <label for="image">Image</label>
                                <img id="image" src="\${createLink(controller:params.controller, action:'image', params:[id: instance.id])}" style="width:100px;height:100px;"/>
                            </div>
                        </g:if>
                        <div class="fieldcontain">
                            <label for="file">Data</label>
                            <input type="file" id="file" name="file" value="" style="padding: 10px 0;display:inline-block;"/>
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
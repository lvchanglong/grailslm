<a href="#fakelink" data-toggle="modal" data-target="#delete${className}Modal\${instance.id}">
    <span class="glyphicon glyphicon-remove" data-toggle="tooltip" title="删除"></span>
</a>
<div class="modal fade" id="delete${className}Modal\${instance.id}" tabindex="-1" role="dialog" aria-labelledby="delete${className}Modal\${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" id="delete${className}Modal\${instance.id}Title">是否删除？</h3>
            </div>
            <div class="modal-body">
                “&nbsp;\${instance}&nbsp;”&nbsp;删除后，<strong>不可恢复</strong>，请慎重操作
            </div>
            <div class="modal-footer">
                <g:form name="delete${className}Form\${instance.id}" url="[controller:params.controller, action: 'delete', id:instance.id]" class="ajaxForm">
                    <g:submitButton name="submit" value="确认删除" class="btn btn-lg btn-primary"/>
                </g:form>
            </div>
        </div>
    </div>
</div>
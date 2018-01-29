<a href="#fakelink" data-toggle="modal" data-target="#moveToDirModal${instance.id}">
    <span class="glyphicon glyphicon-move" data-toggle="tooltip" title="移动"></span>
</a>

<div class="modal fade" id="moveToDirModal${instance.id}" tabindex="-1" role="dialog" aria-labelledby="moveToDirModal${instance.id}Title" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="moveToDirForm" url="[controller:params.controller, action: 'moveTo']" class="form-horizontal ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="moveToDirModal${instance.id}Title">移动到...</h4>
                </div>
                <div class="modal-body">
                    <div id="jstree_${instance.id}_demo">
                    </div>
                    <g:hiddenField name="id" value="${instance.id}"/>
                    <g:hiddenField name="pid" id="jstree_${instance.id}_pid"/>
                    <script>
                        $(function() {
                            jQuery.getJSON("${createLink(controller:params.controller, action:"listDirsForJsTree")}", function(data){
                                jQuery("#jstree_${instance.id}_demo").on("changed.jstree", function (e, data) {
                                    jQuery("#jstree_${instance.id}_pid").val(data.selected[0]);
                                }).jstree({
                                    'core': {
                                        'data': data
                                    }
                                });
                            });
                        });
                    </script>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-sm btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
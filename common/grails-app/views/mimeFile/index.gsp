<%@ page import="common.User; common.MimeFile;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="offset" value="${params.offset?:0}"/>
        <g:set var="vip" value="${User.get(session.uid)}"/>
        <g:set var="parent" value="${MimeFile.get(params.pid)}"/>
    </head>
    <body>
        <g:applyLayout name="${application.platform.layout}">
            <h1 class="main-title" style="text-align: center">
                <g:if test="${parent}">
                    <g:link controller="mimeFile" action="index">文件管理</g:link>
                    <g:each in="${parent.listParents()}" status="i" var="location">
                        /<g:link controller="mimeFile" action="index" params="[pid: location.id]">
                            ${location.filename}
                        </g:link>
                    </g:each>
                </g:if>
                <g:else>
                    <g:link controller="mimeFile" action="index">文件管理</g:link>
                </g:else>
            </h1>

            <g:if test="${!vip.onlyView(params)}">
                <g:render template="createFile" model="[instance: new MimeFile(), parent: parent]"/>

                <g:render template="createDir" model="[instance: new MimeFile(), parent: parent]"/>

                <a href="#fakelink" class="btn btn-lg btn-danger" data-toggle="modal" data-target="#batchMoveMimeFileModal">
                    <span class="glyphicon glyphicon-move"></span>&nbsp;移动文件
                </a>
                <div class="modal fade" id="batchMoveMimeFileModal" tabindex="-1" role="dialog" aria-labelledby="batchMoveMimeFileModalTitle" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title" id="batchMoveMimeFileModalTitle">移动到...</h3>
                            </div>
                            <div class="modal-body">
                                <div id="jstree_batch_demo">
                                </div>
                                <g:hiddenField name="pid" id="jstree_batch_pid"/>
                                <script>
                                    $(function() {
                                        jQuery.getJSON("${createLink(controller:params.controller, action:"listDirsForJsTree")}", function(data){
                                            jQuery("#jstree_batch_demo").on("changed.jstree", function (e, data) {
                                                jQuery("#jstree_batch_pid").val(data.selected[0]);
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
                                <a href="javascript:void(0);" class="btn btn-lg btn-danger" id="batchMoveMaster" data-url="${createLink(controller: params.controller, action: 'moveTo')}">
                                    <span class="glyphicon glyphicon-move" data-toggle="tooltip" title="移动"></span>&nbsp;确认移动
                                </a>
                                <script>
                                    jQuery("#batchMoveMaster").click(function () {
                                        var urlBase = jQuery(this).attr("data-url");//url = urlBase + "/" + id
                                        var pid = jQuery("#jstree_batch_pid").val();
                                        var array = jQuery("[name=ids]").serializeArray();
                                        if(0 == array.length) {
                                            Messenger().post({
                                                message: "请选择操作对象",
                                                hideAfter:2,
                                                showCloseButton: true
                                            });
                                            return;
                                        }
                                        jQuery.each(array, function(i, hm) {
                                            jQuery.ajax({
                                                async:false,
                                                type:'POST',
                                                url:urlBase + "/" + hm.value + "?pid=" + pid,
                                                success:function(data,textStatus){
                                                    Messenger().post({
                                                        message: "操作成功",
                                                        hideAfter:2,
                                                        showCloseButton: true
                                                    });
                                                },
                                                error:function(XMLHttpRequest,textStatus,errorThrown){
                                                    Messenger().post({
                                                        message: "操作失败",
                                                        hideAfter:2,
                                                        showCloseButton: true
                                                    });
                                                }
                                            });
                                        });
                                        window.location.reload();
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>

                <a href="#fakelink" class="btn btn-lg btn-danger" data-toggle="modal" data-target="#batchDeleteMimeFileModal">
                    <span class="glyphicon glyphicon-remove"></span>&nbsp;批量删除
                </a>
                <div class="modal fade" id="batchDeleteMimeFileModal" tabindex="-1" role="dialog" aria-labelledby="batchDeleteMimeFileModalTitle" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h3 class="modal-title" id="batchDeleteMimeFileModalTitle">是否删除？</h3>
                            </div>
                            <div class="modal-body">
                                &nbsp;删除后，<strong>不可恢复</strong>，请慎重操作
                            </div>
                            <div class="modal-footer">
                                <a href="javascript:void(0);" class="btn btn-lg btn-danger" id="batchDeleteMaster" data-url="${createLink(controller: params.controller, action: 'delete')}">
                                    <span class="glyphicon glyphicon-remove"></span>&nbsp;确认删除
                                </a>
                                <script>
                                    jQuery("#batchDeleteMaster").click(function () {
                                        var urlBase = jQuery(this).attr("data-url");//url = urlBase + "/" + id
                                        var array = jQuery("[name=ids]").serializeArray();
                                        if(0 == array.length) {
                                            Messenger().post({
                                                message: "请选择操作对象",
                                                hideAfter:2,
                                                showCloseButton: true
                                            });
                                            return;
                                        }
                                        jQuery.each(array, function(i, hm) {
                                            jQuery.ajax({
                                                async:false,
                                                type:'POST',
                                                url:urlBase + "/" + hm.value,
                                                success:function(data,textStatus){
                                                    Messenger().post({
                                                        message: "操作成功",
                                                        hideAfter:2,
                                                        showCloseButton: true
                                                    });
                                                },
                                                error:function(XMLHttpRequest,textStatus,errorThrown){
                                                    Messenger().post({
                                                        message: "操作失败",
                                                        hideAfter:2,
                                                        showCloseButton: true
                                                    });
                                                }
                                            });
                                        });
                                        window.location.reload();
                                    });
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </g:if>

            <table class="table table-striped">
                <tr>
                    <g:if test="${!vip.onlyView(params)}">
                        <th>
                            <g:checkBox name="checkBoxMaster" value="" checked="false"/>
                            <script>
                                jQuery("#checkBoxMaster").click(function () {
                                    if(jQuery(this).is(":checked")) {
                                        jQuery("[name=ids]").prop("checked", true);
                                    } else {
                                        jQuery("[name=ids]").prop("checked", false);
                                    }
                                });
                            </script>
                        </th>
                    </g:if>
                    <th>序号</th>
                    <th>文件名称</th>
                    <th>类型</th>
                    <th>密级</th>
                    <th>备注</th>
                    <g:sortableColumn property="dateCreated" title="创建时间"/>
                    <th>操作</th>
                </tr>
                <g:each in="${mimeFileList}" status="i" var="mimeFile">
                    <tr>
                        <g:if test="${!vip.onlyView(params)}">
                            <td>
                                <g:checkBox name="ids" value="${mimeFile.id}" checked="false"/>
                            </td>
                        </g:if>
                        <td>${offset.toInteger() + i + 1}</td>
                        <td>
                            <g:if test="${mimeFile.isDir()}">
                                <g:link action="index" params="[pid:mimeFile.id]">${mimeFile.filename}</g:link>
                            </g:if>
                            <g:else>
                                <g:link action="preview" id="${mimeFile.id}" target="_blank">${mimeFile.filename}</g:link>
                            </g:else>
                        </td>
                        <td>${mimeFile.type}</td>
                        <td>${mimeFile.classification}</td>
                        <td><pre>${mimeFile.remark}</pre></td>
                        <td><g:formatDate format="yyyy-MM-dd" date="${mimeFile.dateCreated}"/></td>
                        <td>
                            <g:if test="${mimeFile.isDir()}">
                                <span class="glyphicon glyphicon-minus"></span>
                            </g:if>
                            <g:else>
                                <g:link action="preview" id="${mimeFile.id}" params="[browser:'EX']" target="_blank"><span class="glyphicon glyphicon-fullscreen" title="预览（最大化）"></span></g:link>
                            </g:else>
                        &nbsp;
                            <g:if test="${!vip.onlyView(params)}">
                                <g:render template="download" model="[instance:mimeFile]"/>
                                &nbsp;
                                <g:render template="moveTo" model="[instance:mimeFile]"/>
                                &nbsp;
                                <g:render template="edit" model="[instance:mimeFile]"/>
                                &nbsp;
                                <g:render template="delete" model="[instance:mimeFile]"/>
                            </g:if>
                            <g:else>
                                <span class="glyphicon glyphicon-minus"></span>
                            </g:else>
                        </td>
                    </tr>
                </g:each>
            </table>

            <div style="text-align: center;">
                <div class="pagination">
                    <g:paginate total="${mimeFileCount ?: 0}" params="[pid:params.pid]"/>
                </div>
            </div>
        </g:applyLayout>
    </body>
</html>
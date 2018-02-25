<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <style>
            .dashboard-header {
                border:none !important;
            }
            .dashboard-header .container-fluid {
                background-color: #202e57 !important;
            }
        </style>
    </head>
    <body>
        <g:if test="${params.q}">
            <g:applyLayout name="${application.platform.layout}">
                <g:if test="${mimeFileList}">
                    <table class="table table-striped">
                        <tr>
                            <th>文件名称</th>
                            <th>类型</th>
                            <th>密级</th>
                            <th>备注</th>
                            <th>创建时间</th>
                        </tr>
                        <g:each in="${mimeFileList}" status="i" var="mimeFile">
                            <tr>
                                <td>
                                    <g:if test="${mimeFile.isDir()}">
                                        <g:link controller="mimeFile" action="index" params="[pid:mimeFile.id]">${mimeFile.filename}</g:link>
                                    </g:if>
                                    <g:else>
                                        <g:link controller="mimeFile" action="preview" id="${mimeFile.id}" target="_blank">${mimeFile.filename}</g:link>
                                    </g:else>
                                </td>
                                <td>${mimeFile.type}</td>
                                <td>${mimeFile.classification}</td>
                                <td><pre>${mimeFile.remark}</pre></td>
                                <td><g:formatDate format="yyyy-MM-dd" date="${mimeFile.dateCreated}"/></td>
                            </tr>
                        </g:each>
                    </table>
                    <div style="text-align: center;">
                        <div class="pagination">
                            <g:paginate total="${mimeFileCount ?: 0}" params="[q:params.q]"/>
                        </div>
                    </div>
                </g:if>
                <g:else>
                    <span>暂无数据</span>
                </g:else>
            </g:applyLayout>
        </g:if>
        <g:else>
            <g:applyLayout name="/bases/Center">
                <g:render template="slider"/>
            </g:applyLayout>
        </g:else>
    </body>
</html>

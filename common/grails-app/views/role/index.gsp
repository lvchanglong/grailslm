<%@ page import="common.User; common.Role;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="offset" value="${params.offset?:0}"/>
        <g:set var="vip" value="${User.get(session.uid)}"/>
    </head>
    <body>
        <g:applyLayout name="${application.platform.layout}">
            <h1 class="main-title" style="text-align: center">
                角色管理
            </h1>
            <g:if test="${!vip.onlyView(params)}">
                <g:render template="create" model="[instance: new Role()]"/>
            </g:if>

            <table class="table table-striped">
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>权限限定</th>
                    <th>功能集合</th>
                    <g:sortableColumn property="dateCreated" title="创建时间"/>
                    <th>操作</th>
                </tr>
                <g:each in="${roleList}" status="i" var="role">
                    <tr>
                        <td>${offset.toInteger() + i + 1}</td>
                        <td>${role.name}</td>
                        <td>
                            ${role.permission}
                        </td>
                        <td>
                            <g:if test="${role.isAdmin()}">
                                <span class="glyphicon glyphicon-minus"></span>
                            </g:if>
                            <g:else>
                                ${role.functions}
                            </g:else>
                        </td>
                        <td><g:formatDate format="yyyy-MM-dd" date="${role.dateCreated}"/></td>
                        <td>
                            <g:if test="${!vip.onlyView(params)}">
                                <g:if test="${!role.banEdit()}">
                                    <g:render template="edit" model="[instance:role]"/>
                                </g:if>
                                <g:else>
                                    <span class="glyphicon glyphicon-minus"></span>
                                </g:else>
                                &nbsp;
                                <g:if test="${!role.banDelete()}">
                                    <g:render template="delete" model="[instance:role]"/>
                                </g:if>
                                <g:else>
                                    <span class="glyphicon glyphicon-minus"></span>
                                </g:else>
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
                    <g:paginate total="${roleCount ?: 0}" />
                </div>
            </div>
        </g:applyLayout>
    </body>
</html>
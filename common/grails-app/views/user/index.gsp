<%@ page import="common.User; common.User;" %>
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
                用户管理
            </h1>
            <g:if test="${!vip.onlyView(params)}">
                <g:render template="create" model="[instance: new User()]"/>
            </g:if>

            <table class="table table-striped">
                <tr>
                    <th>序号</th>
                    <th>角色</th>
                    <th>账号</th>
                    <th>昵称</th>
                    <th>状态</th>
                    <g:sortableColumn property="dateCreated" title="创建时间"/>
                    <th>操作</th>
                </tr>
                <g:each in="${userList}" status="i" var="user">
                    <tr>
                        <td>${offset.toInteger() + i + 1}</td>
                        <td>${user.role}</td>
                        <td>${user.username}</td>
                        <td>${user.nickname}</td>
                        <td>${user.state}</td>
                        <td><g:formatDate format="yyyy-MM-dd" date="${user.dateCreated}"/></td>
                        <td>
                            <g:if test="${!vip.onlyView(params)}">
                                <g:if test="${!user.banEdit()}">
                                    <g:render template="edit" model="[instance:user]"/>
                                </g:if>
                                <g:else>
                                    <span class="glyphicon glyphicon-minus"></span>
                                </g:else>
                                &nbsp;
                                <g:if test="${!user.banDelete()}">
                                    <g:render template="delete" model="[instance:user]"/>
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
                    <g:paginate total="${userCount ?: 0}" />
                </div>
            </div>
        </g:applyLayout>
    </body>
</html>
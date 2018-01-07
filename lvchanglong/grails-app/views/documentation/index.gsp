<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="grails" />
        <g:set var="entityName" value="${message(code: 'documentation.label', default: 'Documentation')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-documentation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-documentation" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Zindex</th>
                    <th>Grouping</th>
                    <th>Face</th>
                    <th>Data</th>
                    <th>DateCreated</th>
                </tr>
                <g:each in="${documentationList}" status="i" var="documentation">
                    <tr class="${(i % 2 == 0)?"even":"odd"}">
                        <td><g:link controller="documentation" action="show" id="${documentation.id}">${documentation.title}</g:link></td>
                        <td>${documentation.zindex}</td>
                        <td>${documentation.grouping}</td>
                        <td><img src="${createLink(controller: "documentation", action: "preview", id: documentation.id)}" style="max-width:25px;max-height:25px;"/></td>
                        <td><g:link controller="documentation" action="download" id="${documentation.id}">${documentation.dataname}</g:link></td>
                        <td>${documentation.dateCreated}</td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${documentationCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
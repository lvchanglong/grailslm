<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="grails" />
        <g:set var="entityName" value="${message(code: 'software.label', default: 'Software')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-software" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-software" class="content scaffold-list" role="main">
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
                <g:each in="${softwareList}" status="i" var="software">
                    <tr class="${(i % 2 == 0)?"even":"odd"}">
                        <td><g:link controller="software" action="show" id="${software.id}">${software.title}</g:link></td>
                        <td>${software.zindex}</td>
                        <td>${software.grouping}</td>
                        <td><img src="${createLink(controller: "software", action: "preview", id: software.id)}" style="max-width:25px;max-height:25px;"/></td>
                        <td><g:link controller="software" action="download" id="${software.id}">${software.dataname}</g:link></td>
                        <td>${software.dateCreated}</td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${softwareCount ?: 0}" />
            </div>
        </div>
    </body>
</html>
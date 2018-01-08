<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="grails" />
        <g:set var="entityName" value="${message(code: 'software.label', default: 'Software')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-software" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-software" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list software">
                <li class="fieldcontain">
                    <span id="title-label" class="property-label">Title</span>
                    <div class="property-value" aria-labelledby="title-label">${software.title}</div>
                </li>
                <li class="fieldcontain">
                    <span id="content-label" class="property-label">Content</span>
                    <div class="property-value" aria-labelledby="content-label">${software.content}</div>
                </li>
                <div class="fieldcontain">
                    <span id="zindex-label" class="property-label">Zindex</span>
                    <div class="property-value" aria-labelledby="zindex-label">${software.zindex}</div>
                </div>
                <div class="fieldcontain">
                    <span id="grouping-label" class="property-label">Grouping</span>
                    <div class="property-value" aria-labelledby="grouping-label">${software.grouping}</div>
                </div>
                <li class="fieldcontain">
                    <span id="face-label" class="property-label">Face</span>
                    <div class="property-value" aria-labelledby="face-label"><img src="${createLink(controller: "software", action: "preview", id: software.id)}" style="max-width:300px;max-height:300px;"/></div>
                </li>
                <li class="fieldcontain">
                    <span id="data-label" class="property-label">Data</span>
                    <div class="property-value" aria-labelledby="data-label"><g:link controller="software" action="download" id="${software.id}">${software.dataname}</g:link></div>
                </li>
                <li class="fieldcontain">
                    <span id="dateCreated-label" class="property-label">DateCreated</span>
                    <div class="property-value" aria-labelledby="dateCreated-label">${software.dateCreated}</div>
                </li>
                <li class="fieldcontain">
                    <span id="lastUpdated-label" class="property-label">LastUpdated</span>
                    <div class="property-value" aria-labelledby="lastUpdated-label">${software.lastUpdated}</div>
                </li>
            </ol>
            <g:form resource="${this.software}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.software}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>

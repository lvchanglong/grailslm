<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="grails" />
        <g:set var="entityName" value="${message(code: 'documentation.label', default: 'Documentation')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-documentation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-documentation" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list documentation">
                <li class="fieldcontain">
                    <span id="title-label" class="property-label">Title</span>
                    <div class="property-value" aria-labelledby="title-label">${documentation.title}</div>
                </li>
                <li class="fieldcontain">
                    <span id="content-label" class="property-label">Content</span>
                    <div class="property-value" aria-labelledby="content-label">${documentation.content}</div>
                </li>
                <div class="fieldcontain">
                    <label for="zindex">Zindex</label>
                    <g:textField name="zindex" value="${documentation.zindex}" style="min-width:285px;"/>
                </div>
                <div class="fieldcontain">
                    <label for="grouping">Grouping</label>
                    <g:textField name="grouping" value="${documentation.grouping}" style="min-width:285px;"/>
                </div>
                <li class="fieldcontain">
                    <span id="face-label" class="property-label">Face</span>
                    <div class="property-value" aria-labelledby="face-label"><img src="${createLink(controller: "documentation", action: "preview", id: documentation.id)}" style="max-width:300px;max-height:300px;"/></div>
                </li>
                <li class="fieldcontain">
                    <span id="data-label" class="property-label">Data</span>
                    <div class="property-value" aria-labelledby="data-label"><g:link controller="documentation" action="download" id="${documentation.id}">${documentation.dataname}</g:link></div>
                </li>
                <li class="fieldcontain">
                    <span id="dateCreated-label" class="property-label">DateCreated</span>
                    <div class="property-value" aria-labelledby="dateCreated-label">${documentation.dateCreated}</div>
                </li>
                <li class="fieldcontain">
                    <span id="lastUpdated-label" class="property-label">LastUpdated</span>
                    <div class="property-value" aria-labelledby="lastUpdated-label">${documentation.lastUpdated}</div>
                </li>
            </ol>
            <g:form resource="${this.documentation}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.documentation}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>

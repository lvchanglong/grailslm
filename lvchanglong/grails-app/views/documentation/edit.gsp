<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="grails" />
        <g:set var="entityName" value="${message(code: 'documentation.label', default: 'Documentation')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-documentation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/admin')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-documentation" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.documentation}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.documentation}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <g:uploadForm controller="documentation" action="update" id="${this.documentation.id}">
                <g:hiddenField name="version" value="${this.documentation?.version}" />
                <fieldset class="form">
                    <div class="fieldcontain required">
                        <label for="title">Title
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="title" value="${documentation.title}" style="min-width:285px;"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="content">Content</label>
                        <g:textArea name="content" value="${documentation.content}" style="min-width:700px;min-height:300px;"/>
                    </div>
                    <div class="fieldcontain required">
                        <label for="zindex">Zindex
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="zindex" value="${documentation.zindex}" style="min-width:285px;"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="grouping">Grouping</label>
                        <g:textField name="grouping" value="${documentation.grouping}" style="min-width:285px;"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="preview">Preview
                        </label>
                        <img id="preview" src="${createLink(controller: "documentation", action: "preview", id: documentation.id)}" style="max-width:300px;max-height:300px;"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="face">Face
                        </label>
                        <input type="file" name="facefile" id="face" style="display: inline-block"/>
                    </div>
                    <div class="fieldcontain">
                        <label for="data">Data
                        </label>
                        <input type="file" name="datafile" id="data" style="display: inline-block"/>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:uploadForm>
        </div>
    </body>
</html>

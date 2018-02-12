<%@ page import="common.FunGroup; common.Function; common.Platform; common.User" %>

<g:set var="vip" value="${User.get(session.uid)}"/>

<nav class="navbar navbar-inverse navbar-fixed-top dashboard-header">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <g:set var="platform" value="${application.platform}"/>
            <g:if test="${platform?.data}">
                <g:link uri="/" class="navbar-brand-image">
                    <img src="${createLink(controller:'platform', action:"image", params:["id": platform?.id])}"/>
                </g:link>
            </g:if>
            <g:else>
                <g:link uri="/" class="navbar-brand">
                    ${platform?.name}
                </g:link>
            </g:else>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <g:if test="${vip}">
                <g:if test="${vip.isSuperman()}">
                    <g:set var="groups" value="${Function.listToGroup()}"/>
                </g:if>
                <g:elseif test="${vip.isAdmin()}">
                    <g:set var="groups" value="${Platform.listActivePlatformFunctionsToGroup()}"/>
                </g:elseif>
                <g:else>
                    <g:set var="groups" value="${vip.listFunctionsToGroup()}"/>
                </g:else>

                <ul class="nav navbar-nav">
                    <g:each in="${groups}" status="j" var="hm">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">${hm.getKey()} <b class="caret"></b></a>
                            <span class="dropdown-arrow"></span>
                            <ul class="dropdown-menu">
                                <g:each in="${hm.getValue()}" status="i" var="function">
                                    <g:if test="${function.getStateToBoolean() || vip.isSuperman()}">
                                        <li><g:link controller="${function.controllerName}" action="${function.actionName}">${function.name}</g:link></li>
                                    </g:if>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <g:render template="/layouts/demos/regenerate" model="[vip:vip]"/>
                    </li>
                    <li>
                        <g:render template="/layouts/demos/logout"/>
                    </li>
                </ul>
            </g:if>
            <g:else>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <g:render template="/layouts/demos/login"/>
                    </li>
                    <li>
                        <g:render template="/layouts/demos/register"/>
                    </li>
                </ul>
            </g:else>

            <div class="navbar-form navbar-right">
                <g:form controller="guest" action="index">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="文件检索..." name="q" value="${params.q}"/>
                        <span class="input-group-btn">
                            <g:submitButton name="submit" class="btn btn-default" value="Go!"/>
                        </span>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</nav>
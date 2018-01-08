<%@ page import="lvchanglong.User" %>

<style>
    .dashboard-header a {
        font-weight:bold;
    }
</style>

<nav class="navbar navbar-inverse navbar-fixed-top dashboard-header" style="z-index:9999;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link uri="/" class="navbar-brand">Grails联盟</g:link>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <g:set var="vip" value="${lvchanglong.User.get(session.uid)}"/>
                <g:if test="${vip}">
                    <li>
                        <g:render template="/layouts/demos/regenerate" model="[vip:vip]"/>
                    </li>
                    <li>
                        <g:render template="/layouts/demos/logout"/>
                    </li>
                </g:if>
                <g:else>
                    <li>
                        <g:render template="/layouts/demos/login"/>
                    </li>
                    <li>
                        <g:render template="/layouts/demos/register"/>
                    </li>
                </g:else>
            </ul>
            %{--<form class="navbar-form navbar-right">
                <g:form controller="guest" action="index" style="display:inline-block;width:300px;padding-top:8px;">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search..." name="q" value="${params.q}"/>
                        <span class="input-group-btn">
                            <g:submitButton name="submit" class="btn btn-default" value="Go!"/>
                        </span>
                    </div>
                </g:form>
            </form>--}%
        </div>
    </div>
</nav>
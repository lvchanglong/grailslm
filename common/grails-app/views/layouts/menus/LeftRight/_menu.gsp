<%@ page import="common.FunGroup; common.Function; common.Platform; common.User" %>

<g:set var="vip" value="${User.get(session.uid)}"/>

<div id="menuLeft" style="font-size:12px;">
	<g:if test="${vip}">
		<div class="panel panel-default">
			<g:if test="${vip.isSuperman()}">
				<g:each in="${Function.listToGroup()}" status="j" var="hm">
					<div class="panel-heading" role="tab" id="menuLeftHeading${j}">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse" href="#menuLeftCollapse${j}" aria-expanded="true" aria-controls="menuLeftCollapse${j}">
								${hm.getKey()} <b class="caret"></b>
							</a>
						</h4>
					</div>
					<div id="menuLeftCollapse${j}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="menuLeftHeading${j}">
						<ul class="list-group">
							<g:each in="${hm.getValue()}" status="i" var="function">
								<li class="list-group-item"><g:link controller="${function.controllerName}" action="${function.actionName}">${function.name}</g:link></li>
							</g:each>
						</ul>
					</div>
				</g:each>
			</g:if>
			<g:elseif test="${vip.isAdmin()}">
				<g:each in="${Platform.listActivePlatformFunctionsToGroup()}" status="j" var="hm">
					<div class="panel-heading" role="tab" id="menuLeftHeading${j}">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse" href="#menuLeftCollapse${j}" aria-expanded="true" aria-controls="menuLeftCollapse${j}">
								${hm.getKey()} <b class="caret"></b>
							</a>
						</h4>
					</div>
					<div id="menuLeftCollapse${j}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="menuLeftHeading${j}">
						<ul class="list-group">
							<g:each in="${hm.getValue()}" status="i" var="function">
								<g:if test="${function.getStateToBoolean()}">
									<li class="list-group-item"><g:link controller="${function.controllerName}" action="${function.actionName}">${function.name}</g:link></li>
								</g:if>
							</g:each>
						</ul>
					</div>
				</g:each>
			</g:elseif>
			<g:else>
				<g:each in="${vip.listFunctionsToGroup()}" status="j" var="hm">
					<div class="panel-heading" role="tab" id="menuLeftHeading${j}">
						<h4 class="panel-title">
							<a class="collapsed" role="button" data-toggle="collapse" href="#menuLeftCollapse${j}" aria-expanded="true" aria-controls="menuLeftCollapse${j}">
								${hm.getKey()} <b class="caret"></b>
							</a>
						</h4>
					</div>
					<div id="menuLeftCollapse${j}" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="menuLeftHeading${j}">
						<ul class="list-group">
							<g:each in="${hm.getValue()}" status="i" var="function">
								<g:if test="${function.getStateToBoolean()}">
									<li class="list-group-item"><g:link controller="${function.controllerName}" action="${function.actionName}">${function.name}</g:link></li>
								</g:if>
							</g:each>
						</ul>
					</div>
				</g:each>
			</g:else>
		</div>
	</g:if>
</div>
<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <g:render template="dashboard/leftMenu"/>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <g:each in="${groups}" var="group">
                        <div class="group-header">${group.getKey()?.toUpperCase()}</div>
                        <div class="row" style="margin-bottom:15px;">
                            <g:each in="${group.getValue()}" var="software">
                                <div class="col-md-6">

                                    <div class="row" style="margin-bottom:5px;">
                                        <div class="col-md-3">
                                            <img src="${createLink(controller: "software", action: "preview", id: software.id)}" style="display:block;width:100%;max-width:300px;height:100px;margin-top:27px;"/>
                                        </div>
                                        <div class="col-md-9">
                                            <div style="padding:15px 0;">
                                                <g:if test="${software.data}">
                                                    <g:link controller="software" action="download" id="${software.id}" style="display:inline-block;font-weight:bold;margin-bottom:10px;font-size:20px;">
                                                        ${software.title}
                                                    </g:link>
                                                </g:if>
                                                <g:else>
                                                    <span style="display:inline-block;font-weight:bold;margin-bottom:10px;font-size:20px;">${software.title}</span>
                                                </g:else>
                                                <br/>
                                                <pre>${software.content}</pre>
                                                <div style="margin-top:10px;">发布时间：<g:formatDate date="${software.dateCreated}" format="yyyy-MM-dd" /></div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </g:each>
                        </div>
                    </g:each>
                </div>
            </div>
        </div>
    </body>
</html>

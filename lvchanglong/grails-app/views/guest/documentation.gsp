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
                            <g:each in="${group.getValue()}" var="documentation">
                                <div class="col-md-4">

                                    <div class="row" style="margin-bottom:5px;">
                                        <div class="col-md-3">
                                            <img src="${createLink(controller: "documentation", action: "preview", id: documentation.id)}" style="display:block;width:100%;max-width:300px;height:100px;margin-top:27px;"/>
                                        </div>
                                        <div class="col-md-9">
                                            <div style="padding:15px 0;">
                                                <g:if test="${documentation.data}">
                                                    <g:link controller="documentation" action="download" id="${documentation.id}" style="display:inline-block;font-weight:bold;margin-bottom:10px;font-size:20px;">
                                                        ${documentation.title}
                                                    </g:link>
                                                </g:if>
                                                <g:else>
                                                    <span style="display:inline-block;font-weight:bold;margin-bottom:10px;font-size:20px;">${documentation.title}</span>
                                                </g:else>
                                                <br/>
                                                <pre>${documentation.content}</pre>
                                                <div style="margin-top:10px;">发布时间：<g:formatDate date="${documentation.dateCreated}" format="yyyy-MM-dd" /></div>
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

<%@ page import="lvchanglong.User" %>

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
                    <g:form controller="guest" action="feedback" class="ajaxForm">
                        <div class="row">
                            <div class="col-md-12">
                                <h2 style="margin-top: 0;margin-bottom:5px;">
                                    关于本<asset:image src="web/网站.png" alt="网站" style="width:104px;height:51px;"/>，你想<asset:image src="web/说.png" alt="说" style="width:56px;height:51px;"/>什么？
                                </h2>
                            </div>
                            <div class="col-md-12">
                                <g:if test="${session.uid}">
                                    <g:hiddenField name="title" value="${User.get(session.uid).username}"/>
                                </g:if>
                                <g:else>
                                    <g:hiddenField name="title" value="${new Date().format("yyyyMMddHHmmss")}"/>
                                </g:else>
                                <g:textArea name="content" required="" autofocus="" class="form-control borderRadius" style="width:100%;min-height:300px;" placeholder="建议、意见或留言？总之，写点什么吧~"/>
                            </div>
                            <div class="col-md-12">
                                <g:submitButton name="submit" value="发布" class="btn btn-primary btn-lg" style="margin-top:10px;"/>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </body>
</html>

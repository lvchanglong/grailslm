<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title><g:layoutTitle default="${application.platform.name}"/></title>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

        <meta name="Keywords" content="grails联盟,Grails联盟,GRAILS联盟"/>
        <meta name="Description" content="grails,grails教程,grails视频教程,网站开发,深思君"/>

        <asset:stylesheet src="application.css"/>
        <asset:stylesheet src="${application.platform.css}"/>

        <!--[if lt IE 9]>
            <asset:javascript src="ie/Respond-1.4.2/dest/respond.min.js"/>
            <asset:javascript src="ie/html5shiv-3.7.3/dist/html5shiv-printshiv.js"/>
            <asset:javascript src="ie/es5-shim-4.5.10/es5-shim.js"/>
            <asset:javascript src="ie/es5-shim-4.5.10/es5-sham.js"/>
            <asset:javascript src="ie/json3.min.js"/>
            <asset:javascript src="ie/es6-shim-0.35.3/es6-shim.js"/>
            <asset:javascript src="ie/es6-shim-0.35.3/es6-sham.js"/>
            <!--
            <asset:javascript src="ie/es7-shim-6.0.0/es7-shim.js"/>
            <asset:javascript src="ie/backings/console-polyfill/index.js"/>
            -->
        <![endif]-->

        <asset:javascript src="application.js"/>

        <g:layoutHead/>
    </head>
    <body onload="${pageProperty(name:"body.onload")}" class="${pageProperty(name:"body.class")}">
        <g:pageProperty name="page.header" default="${ render(template:"/layouts/headers/Center/header") }"/>
        <g:layoutBody/>
        <g:pageProperty name="page.footer" default="${ render(template:"/layouts/footers/Center/footer") }"/>
        <asset:image src="spinner.gif" id="spinner" style="position:fixed;left:15px;bottom:30px;display:none;"/>
    </body>
</html>

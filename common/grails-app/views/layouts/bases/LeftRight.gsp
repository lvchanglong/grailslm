<asset:stylesheet src="LeftRight/main.css"/>

<div class="container-fluid layout-left-right">
    <div class="row">
        <div class="col-md-2 sidebar">
            <g:pageProperty name="page.sidebar" default="${ render(template:"/layouts/bars/LeftRight/sidebar") }"/>
        </div>
        <div class="col-md-offset-2 col-md-10 main">
            <g:layoutBody/>
        </div>
    </div>
</div>

<content tag="header">
    <g:render template="/layouts/headers/LeftRight/header"/>
</content>

<content tag="footer">
    <g:render template="/layouts/footers/LeftRight/footer"/>
</content>
<div class="container-fluid main-container">
    <div class="row">
        <div class="col-md-2">
            <g:pageProperty name="page.menu" default="${ render(template:"/layouts/menus/LeftRight/menu") }"/>
        </div>
        <div class="col-md-10">
            <g:layoutBody/>
        </div>
    </div>
</div>

<content tag="header">
    <g:render template="/layouts/headers/LeftRight/header"/>
</content>
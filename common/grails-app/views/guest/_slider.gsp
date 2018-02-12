<%@ page import="common.Slide" %>

<style>
    .layout-center {
        padding:50px 0 20px 0 !important;
    }
</style>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <g:set var="slides" value="${Slide.list()}"/>

    <ol class="carousel-indicators">
        <g:each in="${slides}" var="slide" status="i">
            <li data-target="#carousel-example-generic" data-slide-to="${i}" class="${i == 0?"active":""}"></li>
        </g:each>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <g:if test="${slides}">
            <g:each in="${slides}" var="slide" status="i">
                <div class="item ${i == 0?"active":""}">
                    <div class="carousel-caption">
                        <h1>
                            <g:link url="${slide.url}">${slide.title}</g:link>
                        </h1>
                        <p class="subheading">
                            ${slide.content}
                        </p>
                    </div>
                    <img src="${createLink(controller:'slide', action:"image", params:["id": slide.id])}" class="slide-image"/>
                </div>
            </g:each>
        </g:if>
        <g:else>
            <div class="item active">
                <div class="carousel-caption">
                    <h1>默认标题</h1>
                    <p class="subheading">
                        默认内容
                    </p>
                </div>
                <img src="${assetPath(src:"web/plane01.png")}" class="slide-image"/>
            </div>
        </g:else>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
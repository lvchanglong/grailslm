<ul class="nav nav-sidebar">
    <li class="${params.action=="index"?"active":""}"><g:link controller="guest" action="index">首页</g:link></li>
    <li class="${params.action=="software"?"active":""}"><g:link controller="guest" action="software">软件下载</g:link></li>
    <li class="${params.action=="documentation"?"active":""}"><g:link controller="guest" action="documentation">参考文档</g:link></li>
    <li class="${params.action=="learning"?"active":""}"><g:link controller="guest" action="learning">在线学习</g:link></li>
    <li class="${params.action=="suggest"?"active":""}"><g:link controller="guest" action="suggest">建议</g:link></li>
    %{--<li><g:link controller="guest" action="community">社区</g:link></li>--}%
</ul>
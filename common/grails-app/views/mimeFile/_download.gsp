<g:if test="${instance.isDir()}">
    <span class="glyphicon glyphicon-minus"></span>
</g:if>
<g:else>
    <g:link controller="${params.controller}" action="download" id="${instance.id}" target="_blank">
        <span class="glyphicon glyphicon-download" data-toggle="tooltip" title="下载"></span>
    </g:link>
</g:else>
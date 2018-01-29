<a href="#" data-toggle="modal" data-target="#regenetateModal">
    ${vip.nickname}（${vip.username}）
</a>

<div class="modal fade" id="regenetateModal" tabindex="-1" role="dialog" aria-labelledby="regenetateModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <g:form controller="vip" action="regenerate" id="${vip.id}" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="regenetateModalLabel">个人信息修改</h3>
                </div>
                <div class="modal-body">
                    <g:textField name="password" value="" placeholder="密码" class="form-control" style="margin-bottom:5px;"/>
                    <g:textField name="repassword" value="" placeholder="再次输入" class="form-control" style="margin-bottom:5px;"/>
                    <g:textField name="nickname" value="${vip.nickname}" placeholder="昵称" class="form-control"/>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-lg btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
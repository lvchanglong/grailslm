<a href="#fakelink" class="btn btn-lg btn-info" data-toggle="modal" data-target="#createSlideModal">
    <span class="fui-plus-circle"></span>&nbsp;信息添加
</a>
<div class="modal fade" id="createSlideModal" tabindex="-1" role="dialog" aria-labelledby="createSlideModalTitle" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <g:form name="createSlideForm" url="[controller:params.controller, action: 'save']" class="ajaxForm">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="createSlideModalTitle">信息添加</h4>
                </div>
                <div class="modal-body">
                    <fieldset class="form">
                        <fieldset class="form">
                            <div class="fieldcontain required">
                                <label for="title">Title
                                    <span class="required-indicator">*</span>
                                </label>
                                <input type="text" name="title" value="" required="" id="title">
                            </div>
                            <div class="fieldcontain">
                                <label for="content">Content</label>
                                <input type="text" name="content" value="" id="content">
                            </div>
                            <div class="fieldcontain">
                                <label for="file">Data</label>
                                <input type="file" id="file" name="file" value="" style="padding: 10px 0;display:inline-block;"/>
                            </div>
                        </fieldset>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <g:submitButton name="submit" value="确定" class="btn btn-sm btn-primary"/>
                </div>
            </g:form>
        </div>
    </div>
</div>
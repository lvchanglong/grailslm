<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>项目样例</title>
        <style>
            .layout-center {
                padding:50px 0 20px 0 !important;
            }
            .dashboard-header {
                border:none !important;
            }
            .dashboard-header .container-fluid {
                background-color: #202e57 !important;
            }
            table {
                border: none !important;
                font-size:14px;
                display:block;
            }
            td, th {
                border: 1px solid black !important;
                text-align: center !important;
            }
            .table-header {
                text-align: center;
                font-weight: bold;
            }
            .table-main table {
                display:block;
                margin:0 auto;
            }
            .table-main tr {
                height:auto !important;
            }
        </style>
    </head>
    <body>
        <g:applyLayout name="${application.platform.layout}">
            <div class="row">
                <div class="col-md-12">
                    <g:form controller="guest" action="demo" name="main-search" class="navbar-form" role="search" style="margin-top:50px;">
                        <div class="form-group" style="display:block;width:700px;margin:0 auto;">
                            <div class="row">
                                <div class="col-md-12" style="padding-left:0;">
                                    <div class="input-group" style="width:100%;">
                                        <input name="con" class="form-control" id="navbarInput-01" type="search" placeholder="例如：MS27488-22-2 或 军标零件号#MS27488-22-2" value="${params.con}" style="width:100%;"/>
                                        <span class="input-group-btn">
                                            <button type="submit" class="btn">检索</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </g:form>
                </div>
                <div class="col-md-12">
                    <g:if test="${excelHtmlList.size() <= 0}">
                        <span>暂无数据</span>
                    </g:if>
                    <g:each in="${excelHtmlList}" var="excel">
                        <h1 class="table-header">${excel.tableName}（${excel.number}）</h1>
                        <div style="margin-bottom:30px;" class="table-main">${excel.content}</div>
                    </g:each>
                </div>
            </div>

            <script>
                jQuery(".excel-html").each(function (i, it) {
                    var data = jQuery(it).html();
//                    console.log(it);
                    jQuery.each("${params.con}".split("#"), function (j, key) {
                        console.log(key);
                        if(key) {
                            var reg = new RegExp(key, "g");
                            data = data.replace(reg, "<span style='background-color:yellow;'>" + key + "</span>");
                            console.log(data)
                        }
                    });
                    jQuery(it).html(data);
                });

                jQuery(".excel-html tr:not(.th-father)").each(function (i, it) {
                    var data = jQuery(it).html();
                    console.log(it);

                    if(!data.match("${params.con}".split("#").join("|"))) {
                        jQuery(it).html("");
                    }
                });
            </script>
        </g:applyLayout>
    </body>
</html>

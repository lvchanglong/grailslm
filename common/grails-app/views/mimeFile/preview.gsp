<%@ page import="common.User; common.MimeFile;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="offset" value="${params.offset?:0}"/>
        <g:set var="vip" value="${User.get(session.uid)}"/>
        <g:set var="parent" value="${MimeFile.get(params.pid)}"/>
        <style>
            .preview-backings {
                margin-top:15px;
                font-size:12px;
            }
            .preview-backings .download {
                margin-top:5px;
            }
        </style>
    </head>
    <body>
        <g:applyLayout name="${application.platform.layout}">
            <div class="row">
                <div class="col-md-12">
                    <div id="preview-pdf" class="media"></div>
                    <p class="preview-backings">
                        预览存在问题？请下载并安装以下软件后重试。安装前最好卸载其他PDF阅读工具，推荐使用右键“目标另存为(A)...”方式进行下载，安装成功后需手动按F5键刷新页面，特殊情况下需重启浏览器。<br/>
                    <div class="download">
                        <g:link uri="${assetPath(src: "AcroRdrDC/AcroRdrDC1500720033_zh_CN.msi")}">Windows-Adobe Acrobat Reader DC</g:link>
                    </div>
                </p>
                    <script>
                        if(PDFObject.supportsPDFs) {
                            console.log("PDFObject");
                            var options = {
                                width: "100%",
                                height: "700px",
                                page: "1",
                                pdfOpenParams: {
                                    view: 'FitV',
                                    pagemode: "thumbs",
                                    search: "lorem ipsum"
                                }
                            };
                            PDFObject.embed("${path}", "#preview-pdf", options);
                        } else {
                            console.log("media");
                            var opts = {
                                width: "100%",
                                height:700,
                                autoplay:true,
                                src:"${path}"
                            };
                            jQuery('.media').media(opts);
                        }
                    </script>
                </div>
            </div>
        </g:applyLayout>
    </body>
</html>
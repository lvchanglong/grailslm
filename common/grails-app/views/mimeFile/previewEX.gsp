<!DOCTYPE html>
<html>
    <head>
        <title>文档预览</title>
        <style>
            html, body {
                height:100%;
                padding:0;
                margin:0;
            }
            iframe {
                width: 97% !important;
                display:block;
                margin: 0 auto;
                height: 97% !important;
            }
        </style>
    </head>
    <body>
        <iframe src="${assetPath(src: "pdf/pdfjs-1.9.426-dist/web/viewer.html")}?file=${path}" width="100%" height="100%" />
    </body>
</html>
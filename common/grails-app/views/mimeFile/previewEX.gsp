<!DOCTYPE html>
<html>
    <head>
        <title>文件预览</title>
        <style>
            html, body {
                height:100%;
                padding:0;
                margin:0;
            }
            iframe {
                width: 98% !important;
                height: 98% !important;
                margin: 0 auto;
                display:block;
            }
        </style>
    </head>
    <body>
        <iframe src="${assetPath(src: "pdf/pdfjs-1.9.426-dist/web/viewer.html")}?file=${path}"/>
    </body>
</html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>首页</title>
    </head>
    <body>
        <div style="text-align:center">
            <form id="uploadForm">
                <label>日志文件：</label>
                <input id="logFile" name="file" type="file" />
                <button id="submit">提交</button>
            </form>
        </div>
        <hr/>
        <div>
            <label>最大暂停时间：</label><label id="maxGCPause"></label>
        </div>
        <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="/js/main.js"></script>
    </body>
</html>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>首页</title>
        <link rel="stylesheet" type="text/css" href="/css/app.css">
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
        <div class="summary-box color-2">
            <div class="summary-head">摘要</div>
            <div class="summary-item-box">
                <div class="summary-item">新生代GC：<span id="youngGCName"></span></div>
                <div class="summary-item">老年代GC：<span id="oldGCName"></span></div>
                <div class="clearfix"></div>
                <div class="summary-item">最大暂停时间：<span id="maxGCPause"></span></div>
                <div class="clearfix"></div>
                <div class="summary-item">Minor GC平均时间间隔：<span id="minorGCInterval"></span></div>
                <div class="summary-item">Minor GC提升对象的次数：<span id="promotionTimes"></span></div>
                <div class="summary-item">Minor GC每次提升对象的平均大小：<span id="promotionSize"></span></div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="chart-box">
            <div id="gcCauseChartDiv" class="chart-div color-1" style="height:400px;"></div>
        </div>
        <div class="chart-box">
            <div id="gcCauseChartDiv" class="chart-div color-3" style="height:400px;"></div>
        </div>
        <script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="/js/echarts.min.js"></script>
        <script type="text/javascript" src="/js/app.js"></script>
    </body>
</html>
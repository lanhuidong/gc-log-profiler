$(function(){
    $('#submit').click(function(e){
        e.preventDefault();
        var fileNumbers = $("#uploadForm")[0]['file'].files.length
        if(fileNumbers > 0){
            var formData = new FormData($("#uploadForm")[0]);
            $.ajax({
                url: '/form',
                method: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                dataType: 'json',
                success: function(result){
                    processGCData(result);
                },
                error: function(result){
                    alert(result);
                }
            });
        } else {
            alert('请选择日志文件');
        }
    });
});

function processGCData(data){
    $('#youngGCName').text(data['cmd_data']['young_gc_name']);
    $('#oldGCName').text(data['cmd_data']['old_gc_name']);
    $('#maxGCPause').text(data['max_gc_pause'] + '秒');
    var interval = data['minor_gc_duration'] / data['minor_gc_times'];
    if(interval > 1000){
        interval = (interval / 1000).toFixed(2) + '秒';
    } else {
        interval = interval.toFixed(2) + '毫秒';
    }
    $('#minorGCInterval').text(interval);
    $('#promotionTimes').text(data['promotion_times']);
    var promotionSize = data['promotion_size']/data['promotion_times'];
    if(promotionSize > 1024 * 1024 * 1024){
        promotionSize = (promotionSize / 1024 / 1024 / 1024).toFixed(2) + 'GB';
    } else if(promotionSize > 1024 * 1024){
        promotionSize = (promotionSize / 1024 / 2014).toFixed(2) + 'MB';
    } else if(promotionSize > 1024){
        promotionSize = (promotionSize / 1024).toFixed(2) + 'KB';
    } else {
        promotionSize = promotionSize.toFixed(2) + 'B';
    }
    $('#promotionSize').text(promotionSize);
    drawGcCauseChart(data['cause_map']);
}

function drawGcCauseChart(data){
    var gcCauseChart = echarts.init(document.getElementById('gcCauseChartDiv'));
    var legendData = [];
    var seriesData = [];
    for(var prop in data){
        legendData.push(prop);
        seriesData.push({value:data[prop], name:prop})
    }
    option = {
        title : {
            text: 'GC Cause',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: legendData
        },
        series : [
            {
                name: 'GC Cause',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:seriesData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    gcCauseChart.setOption(option);
}
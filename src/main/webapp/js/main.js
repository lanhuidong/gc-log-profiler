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
    $('#maxGCPause').text(data['maxGCPause'] + '秒');
}
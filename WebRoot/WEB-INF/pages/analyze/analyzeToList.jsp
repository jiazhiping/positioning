<%--
    User: Administrator   Date: 2017/11/19 0019    Time: 下午 6:27
--%>
<%@ page language="Java" contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html manifest="cache.manifest">
<head>
    <base href="<%=basePath%>">
    <title>${currentTitle}</title>
</head>
<body>
<script src="http://echarts.baidu.com/dist/echarts.min.js"></script>
<div class="rightInfo">
    <form action="analyze_analyzeToList.action" method="post" name="mrForm" id="mrForm">
        <div class="tools">
            <ul class="toolbar" style="width:15%;"></ul>
            <div class="sEachForm" style="width:85%;"></div>
        </div>
        <div id="main" style="width: 100%;height:500px;"></div>
    </form>
</div>
<script>
    // 绘制图表。
    echarts.init(document.getElementById('main')).setOption({
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['提示 Information', '次要 minor', '主要 major', '严重 critical']
        },
        series: [
            {
                name: '告警级别',
                type: 'pie',
                radius: '80%',
                center: ['45%', '50%'],
                data: [
                    {value: ${monitoringType3}, name: '严重 critical'},
                    {value: ${monitoringType2}, name: '主要 major'},
                    {value: ${monitoringType1}, name: '次要 minor'},
                    {value: ${monitoringType0}, name: '提示 Information'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });
</script>
</body>
</html>

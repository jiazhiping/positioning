<%--
    User: Administrator   Date: 2017/11/19 0019    Time: 下午 2:08
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
    <form action="analyze_analyzeList.action" method="post" name="mrForm" id="mrForm">
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
            data: ['定位器位置异常', '定位器电量低', 'POS终端信息未绑定', '非法POS终端信息', 'POS终端信息不匹配', '未知定位器上线', '已知定位器上线']
        },
        series: [
            {
                name: '告警类型',
                type: 'pie',
                radius: '80%',
                center: ['45%', '50%'],
                data: [
                    {value: ${monitoringType6}, name: '定位器位置异常'},
                    {value: ${monitoringType5}, name: '定位器电量低'},
                    {value: ${monitoringType4}, name: 'POS终端信息未绑定'},
                    {value: ${monitoringType3}, name: '非法POS终端信息'},
                    {value: ${monitoringType2}, name: 'POS终端信息不匹配'},
                    {value: ${monitoringType1}, name: '未知定位器上线'},
                    {value: ${monitoringType0}, name: '已知定位器上线'}
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

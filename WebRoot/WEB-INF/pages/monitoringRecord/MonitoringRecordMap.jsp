<%--
    User: Myk   Date: 2017/11/16    Time: 14:00
--%>
<%@ page language="Java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html manifest="cache.manifest">
<head>
    <base href="<%=basePath%>">
    <title>${currentTitle}</title>
    <script type="text/javascript" src="js/monitoringRecord.js"></script>
</head>
<body>
<div id="allMap"></div>
<div class="rightInfoMAW">
    <form action="monitoringRecord_toMonitoringRecordMap.action" method="post" name="mrForm" id="mrForm">
        <table class="tableListMAW">
            <tr>
                <th width="16%">告警时间</th>
                <th width="20%">告警类型</th>
                <th width="18%">告警级别</th>
                <th width="15%">处理状态</th>
                <th width="16%">处理时间</th>
                <th>操作</th>
            </tr>
            <s:iterator value="monitoringViews" status="st">
                <tr>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${monitoringType}</td>
                    <td>
                        <s:if test="%{monitoringGrade == 3}">
                            <div style="width: 100%;height: 100%;background: #ff6754;">critical</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 2}">
                            <div style="width: 100%;height: 100%;background: #fdf16c;">major</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 1}">
                            <div style="width: 100%;height: 100%;background: #FFaa24;">minor</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 0}">
                            <div style="width: 100%;height: 100%;background: #74abfe;">Information</div>
                        </s:if>
                    </td>
                    <td>
                        <s:if test="%{monitoringStatus == 0}">
                            活跃
                        </s:if>
                        <s:else>
                            已处理
                        </s:else>
                    </td>
                    <td>
                        <s:if test="%{UpdateTime == null}">
                            未处理
                        </s:if>
                        <s:else>
                            <s:date name="UpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
                        </s:else>
                    </td>
                    <td>
                        <a href="javascript:mapCenter('${monitoringId}')" class='tableLink'>定位该告警</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </form>
</div>
<script type="text/javascript">
    var map = new BMap.Map("allMap");// 创建Map实例，构造底图时，关闭底图可点功能，设置地图允许的最小/大级别
    map.centerAndZoom("靖边县", 5);                            // 初始化地图,设置中心点坐标和地图级别
    map.addControl(new BMap.NavigationControl());           //左上角，添加默认缩放平移控件
    map.enableContinuousZoom(true);					        //启用地图惯性拖拽，默认禁用
    map.enableScrollWheelZoom(true);                        //开启鼠标滚轮缩放

    var myIcon = new BMap.Icon("images/icon_pos.png", new BMap.Size(56, 63));
    var opts = {
        width: 240,     // 信息窗口宽度
        height: 110,     // 信息窗口高度
        title: "监控设备告警信息", // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };

    <s:iterator value="monitoringViews" status="st">
    var point = new BMap.Point(${longitude}, ${latitude});              // 填充标注点
    var marker = new BMap.Marker(point, {icon: myIcon});                // 创建标注
    map.addOverlay(marker);                                             // 将标注添加到地图中

    var content = "告警时间：<s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/><br/>告警位置：${longitude},${latitude}<br/>告警类型：${monitoringType}<br/>告警级别：<s:if test='%{monitoringGrade == 3}'>critical</s:if><s:if test='%{monitoringGrade == 2}'>major</s:if><s:if test='%{monitoringGrade == 1}'>minor</s:if><s:if test='%{monitoringGrade == 0}'>Information</s:if><br/>处理时间：<s:if test='%{monitoringStatus == 0}'>活跃</s:if><s:else>已处理</s:else>";
    addClickHandler(content, marker);
    var label = new BMap.Label("&nbsp;时间：<s:date name="CreateTime" format="yy-MM-dd HH:mm:ss"/>&nbsp;", {offset: new BMap.Size(-45, 52)});
    marker.setLabel(label);                                             //为标注添加文本标注
    </s:iterator>

    function addClickHandler(content, marker) {
        marker.addEventListener("click", function (e) {
                openInfo(content, e)
            }
        );
    }

    function openInfo(content, e) {
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var infoWindow = new BMap.InfoWindow(content, opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow, point); //开启信息窗口
    }

    function mapCenter(monitoringId) {
        jQuery.ajax({
            url: 'monitoringRecord_MonitoringRecordMap.action',
            data: {
                'monitoringRecordForm.monitoringId': monitoringId
            },
            type: "post",
            cache: false,
            success: function (data) {
                if (data.flag === 0) {
                    var point = new BMap.Point(data.mapLongitude, data.mapLatitude);              // 填充标注点
                    map.setZoom(14);
                    map.panTo(point);
                }
            }
        });
    }

</script>
</body>
</html>

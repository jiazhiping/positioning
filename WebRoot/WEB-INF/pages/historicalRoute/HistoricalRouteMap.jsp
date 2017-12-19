<%--
    User: Myk   Date: 2017/11/10    Time: 15:01
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
    <script type="text/javascript" src="js/deviceUpLineGps.js"></script>
</head>
<body>
<div id="allMap"></div>
<div class="rightInfoMAW">
    <form action="historicalRoute_toHistoricalRoute.action" method="post" name="hForm" id="hForm">
        <table class="tableListMAW">
            <tr>
                <th width="20%">定位时间</th>
                <th width="20%">定位器序列号</th>
                <th width="20%">定位位置</th>
                <th width="20%">偏移距离</th>
                <th>操作</th>
            </tr>
            <s:iterator value="historicalRouteViews" status="st">
                <tr>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${locatorName}</td>
                    <td>${longitude},${latitude}</td>
                    <td>${distance}</td>
                    <td>
                        <a href="javascript:mapCenter('${historicalRouteId}')" class='tableLink'>定位该历史</a>
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
        height: 80,     // 信息窗口高度
        title: "监控设备定位信息", // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };

    var points = [];                                                    // 添加折线运动轨迹
    <s:iterator value="historicalRouteViews" status="st">
    var point = new BMap.Point(${longitude}, ${latitude});              // 填充标注点
    var marker = new BMap.Marker(point, {icon: myIcon});                // 创建标注
    map.addOverlay(marker);                                             // 将标注添加到地图中

    var content = "定位时间：<s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/><br/>定位位置：${longitude},${latitude}<br/>预设位置偏移距离：${distance}(米)";
    addClickHandler(content, marker);
    var label = new BMap.Label("&nbsp;时间：<s:date name="CreateTime" format="yy-MM-dd HH:mm:ss"/>&nbsp;", {offset: new BMap.Size(-45, 52)});
    marker.setLabel(label);                                             //为标注添加文本标注
    points.push(point);
    </s:iterator>
    var polyline = new BMap.Polyline(points, {strokeColor: "blue", strokeWeight: 2, strokeOpacity: 0.5});
    map.addOverlay(polyline);                                           //绘制曲线

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

    function mapCenter(historicalRouteId) {
        jQuery.ajax({
            url: 'historicalRoute_historicalRoute.action',
            data: {
                'historicalRouteForm.historicalRouteId': historicalRouteId
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

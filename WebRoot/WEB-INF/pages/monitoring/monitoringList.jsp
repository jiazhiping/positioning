<%--
  Created by IntelliJ IDEA.
  User: Myk
  Date: 2017/10/30
  Time: 10:59
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
    <script type="text/javascript" src="js/monitoring.js"></script>
</head>
<body>
<div id="allMap"></div>
<div class="rightInfoMAW">
    <form action="monitoring_monitoringList.action" method="post" name="mForm" id="mForm">
        <table class="tableListMAW">
            <tr>
                <th width="20%">告警时间</th>
                <th width="20%">终端序列号</th>
                <th width="25%">告警类别</th>
                <th width="20%">告警级别</th>
                <th>操作</th>
            </tr>
            <s:iterator value="monitoringViews" status="st">
                <tr>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${locatorName}</td>
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

    var myIcon = new BMap.Icon("images/icon_pos.png", new BMap.Size(54, 61));
    var myIcon1 = new BMap.Icon("images/icon_pos_1.gif", new BMap.Size(54, 61));
    var myIcon2 = new BMap.Icon("images/icon_pos_2.gif", new BMap.Size(54, 61));
    var myIcon3 = new BMap.Icon("images/icon_pos_3.gif", new BMap.Size(54, 61));
    var myIcon4 = new BMap.Icon("images/icon_pos_4.gif", new BMap.Size(54, 61));
    var opts = {
        width: 240,     // 信息窗口宽度
        height: 170,     // 信息窗口高度
        title: "监控设备信息", // 信息窗口标题
        enableMessage: true//设置允许信息窗发送短息
    };

    <s:iterator value="deviceUpLines" status="st">
    <s:if test="%{deviceAlarm == 0}" >
    var marker = new BMap.Marker(new BMap.Point(${deviceCurrentPositionLongitude}, ${deviceCurrentPositionLatitude}), {icon: myIcon});  // 创建标注
    </s:if>
    <s:elseif test="%{deviceAlarm == 1}">
    var marker = new BMap.Marker(new BMap.Point(${deviceCurrentPositionLongitude}, ${deviceCurrentPositionLatitude}), {icon: myIcon1});  // 创建标注
    </s:elseif>
    <s:elseif test="%{deviceAlarm == 2}">
    var marker = new BMap.Marker(new BMap.Point(${deviceCurrentPositionLongitude}, ${deviceCurrentPositionLatitude}), {icon: myIcon2});  // 创建标注
    </s:elseif>
    <s:elseif test="%{deviceAlarm == 3}">
    var marker = new BMap.Marker(new BMap.Point(${deviceCurrentPositionLongitude}, ${deviceCurrentPositionLatitude}), {icon: myIcon3});  // 创建标注
    </s:elseif>
    <s:elseif test="%{deviceAlarm == 4}">
    var marker = new BMap.Marker(new BMap.Point(${deviceCurrentPositionLongitude}, ${deviceCurrentPositionLatitude}), {icon: myIcon4});  // 创建标注
    </s:elseif>
    var content = "${deviceAlarm} 监控器序列号:${locatorName}<br/>POS终端序列号：${terminalName}<br/>定位时间：<s:date name="deviceTime" format="yyyy-MM-dd HH:mm:ss"/><br/>定位位置：${deviceCurrentPositionLongitude},${deviceCurrentPositionLatitude}<br/>预设坐标：<s:if test="%{devicePositionLongitude == ''}">此设备未管理</s:if><s:else>${devicePositionLongitude},${devicePositionLatitude}</s:else><br/>偏移距离：${deviceDistance}(米)<br/>上报频率：${deviceFrequency}<div class='formTitle' style='height: 15px;'></div><div style='text-align: center;'><form action=\"\" method=\"post\" name=\"fForm\" id=\"fForm\"><a href=\"javascript:toFrequencyClick('${deviceId}')\" class='tableLink'>设置上报频率</a>&nbsp;&nbsp;<a href=\"javascript:toHistoryClick('${locatorName}') \" class='tableLink'>查看历史记录</a></form></div>";
    map.addOverlay(marker);               // 将标注添加到地图中
    addClickHandler(content, marker);
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

    // 定义一个控件类,即function
    function ZoomControl() {
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
        this.defaultOffset = new BMap.Size(10, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    ZoomControl.prototype.initialize = function (map) {
        // 创建一个DOM元素
        var div = document.createElement("div");
        div.innerHTML = '<form action=\'monitoring_monitoringList.action\' method=\'post\' name=\'mpForm\' id=\'mpForm\'><div class=\'sEachForm\'> <input type=\'button\' class=\'scBtn\' value=\'查询\' onclick=\'mapQueryClick()\' style=\'float: right;\'/> <div style=\'float: right; margin-right: 4px;\'> <s:textfield name='monitoringForm.locatorName' placeholder='请输入监控器序列号' cssClass='scinput' maxlength='16' style="width:200px;"/></div></div></form>';
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div);
        // 将DOM元素返回
        return div;
    };
    // 创建控件
    var myZoomCtrl = new ZoomControl();
    // 添加到地图当中
    map.addControl(myZoomCtrl);

</script>
<script type="text/javascript">
    window.onload = function () {
        if (${location == 1}) {
            map.setZoom(${mapLevel});
            var point = new BMap.Point(${mapLongitude}, ${mapLatitude});              // 填充标注点
            map.panTo(point);
        }
    }
</script>
</body>
</html>




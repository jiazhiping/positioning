<%--
    User: Myk   Date: 2017/11/3    Time: 12:36
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
    <!-- ☆此句话很重要（使用sitemesh模板，必须放到head的第一行，否则无效） -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <base href="<%=basePath%>">
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" type="text/css" href="css/moBan.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/placeholder.js"></script>
    <script type="text/javascript" src="js/binding.js"></script>
    <script type="text/javascript">
        //★★★★★只读文本框，退格键失效★★★★★
        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
            var ev = e || window.event; //获取event对象
            var obj = ev.target || ev.srcElement; //获取事件源
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型
            //获取作为判断条件的事件类型
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况
            vReadOnly = (vReadOnly === undefined) ? false : vReadOnly;
            vDisabled = (vDisabled === undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            //并且readOnly属性为true或disabled属性为true的，则退格键失效
            var flag1 = ev.keyCode === 8 && (t === "password" || t === "text" || t === "textarea") && (vReadOnly === true || vDisabled === true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = ev.keyCode === 8 && t !== "password" && t !== "text" && t !== "textarea";
            //判断
            if (flag2 || flag1) return false;
        }

        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
    </script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="lForm" id="lForm">
        <div class="formTitle">
            <span>定位器</span>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%"></th>
                <th width="15%">定位器序列号</th>
                <th width="15%">预设位置</th>
                <th>创建时间</th>
            </tr>
            <s:iterator value="locators" status="st">
                <tr>
                    <td align="center">
                        <input type="radio" name="rdb" title="双击选择定位器！"
                               ondblclick="dbSelectLocatorClick('${locatorId}','${locatorName}')">
                    </td>
                    <td>${locatorName}</td>
                    <td>${locatorPositionLng},${locatorPositionLat}</td>
                    <td><s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </s:iterator>
        </table>
        <%-- ★解决在文本框按回车键刷新界面的问题★ --%>
        <input id="hiddenText" type="text" style="display: none;"/>
    </form>
</div>
<script type="text/javascript">
    $(".tableList tr:odd").addClass("odd");
</script>
</body>
</html>

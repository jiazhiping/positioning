<%--
    User: Myk   Date: 2017/11/13    Time: 15:49
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
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/moBan.css"/>
    <link rel="stylesheet" type="text/css" href="css/chosen.css">
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/placeholder.js"></script>
    <script type="text/javascript" src="js/monitoring.js"></script>
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
            var flag1 = ev.keyCode === 8
                && (t === "password" || t === "text" || t === "textarea")
                && (vReadOnly === true || vDisabled === true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = ev.keyCode === 8 && t !== "password" && t !== "text"
                && t !== "textarea";
            //判断
            if (flag2 || flag1)
                return false;
        }

        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
    </script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="fForm" id="fForm">
        <div style="width: 100%;">
            <ul class="formInfo" style="padding: 5px;">
                <s:div style="height:15px;"/>
                <li>
                    <label style="width:65px;"> 上报频率 </label>
                    <s:select id="fid" name="monitoringForm.deviceFrequency" list="Frequency" headerKey=""
                              headerValue="全部" class="chosen-select" style="width:320px;"/>
                </li>
                <li>
                    <label> &nbsp; </label>
                    <input type="button" class="scBtn" value="确认修改" onclick="updateFrequency()"
                           style="margin: 20px 0 0 40px;"/>
                </li>
            </ul>
        </div>
        <s:hidden name="monitoringForm.deviceId"/>
    </form>
</div>
<%-- 下拉列表（开始） --%>
<script src="js/chosen.jquery.js"></script>
<%-- 下拉列表（结束） --%>
</body>
</html>

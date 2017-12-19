<%--
    User: Myk   Date: 2017/11/15    Time: 10:18
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
    <script type="text/javascript" src="js/foundation.js"></script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="fForm" id="fForm">
        <div class="formTitle">
            <span>
                ${currentTitle}
            </span>
        </div>
        <div>
            <ul class="formInfo">
                <li>
                    <label> 偏离距离告警值(米) </label>
                    <s:textfield id="thresholdId" cssClass="dfInput" placeholder="请输入最大偏移距离" maxlength="6"
                                 name="foundationForm.threshold"/>
                </li>
                <li>
                    <label> 电量告警值（百分比） </label>
                    <s:textfield id="batteryId" cssClass="dfInput" placeholder="请输入最低电量" maxlength="6"
                                 name="foundationForm.battery"/>
                </li>
                <li style="margin-top: 50px;">
                    <label> &nbsp; </label>
                    <input type="button" class="scBtn" value="确认修改" onclick="updateClick()"
                           style="margin: 10px 0 0 250px;"/>
                </li>
            </ul>
        </div>
        <s:hidden name="foundationForm.systemId"/>
    </form>
</div>
</body>
</html>

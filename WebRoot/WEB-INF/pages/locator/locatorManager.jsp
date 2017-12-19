<%--
    User: Myk   Date: 2017/11/1    Time: 17:18
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
    <s:set value="currOpt" name="opt"/>
    <script type="text/javascript" src="js/locator.js"></script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="lForm" id="lForm">
        <div class="formTitle">
            <span>
                ${currentTitle}
            </span>
        </div>
        <div>
            <ul class="formInfo">
                <li>
                    <label> 序列号 </label>
                    <s:textfield id="lNameId" cssClass="dfInput" placeholder="请输入序列号" maxlength="12"
                                 name="locatorForm.locatorName"/>
                </li>
                <li style="height: 20px;">&nbsp;</li>
                <li>
                    <label> 预设位置:经度 </label>
                    <s:textfield id="lPositionLngId" cssClass="dfInput" placeholder="请输入预设位置:经度" maxlength="12"
                                 name="locatorForm.locatorPositionLng" style="width: 230px;"/>
                    <label style="margin-left: 5px;"> 预设位置:纬度 </label>
                    <s:textfield id="lPositionLatId" cssClass="dfInput" placeholder="请输入预设位置:纬度" maxlength="12"
                                 name="locatorForm.locatorPositionLat" style="width: 230px;"/>
                </li>
                <li style="height: 20px;">&nbsp;</li>
                <li>
                    <label> 上报频率 </label>
                    <s:select id="lFrequencyId" name="locatorForm.locatorFrequency" list="Frequency" headerKey=""
                              headerValue="全部" class="chosen-select" style="width:230px;"/>
                </li>
                <li style="margin-top: 50px;">
                    <label> &nbsp; </label>
                    <s:if test="%{#opt == 'add'}">
                        <input type="button" class="scBtn" value="确认添加" onclick="addClick()"
                               style="margin: 10px 0 0 145px;"/>
                    </s:if>
                    <s:else>
                        <input type="button" class="scBtn" value="确认修改" onclick="updateClick()"
                               style="margin: 10px 0 0 145px;"/>
                    </s:else>
                    <input type="button" class="scBtn" value="返回" onclick="queryClick()"
                           style="margin: 10px 0 0 48px;"/>
                </li>
            </ul>
        </div>
        <s:hidden name="locatorForm.locatorId"/>
        <s:hidden name="curPage"/>
    </form>
</div>
</body>
</html>

<%--
    User: Myk   Date: 2017/11/3    Time: 12:19
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
    <script type="text/javascript" src="js/binding.js"></script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="bForm" id="bForm">
        <div class="formTitle">
            <span>
                ${currentTitle}
            </span>
        </div>
        <div>
            <ul class="formInfo">
                <li>
                    <label> 定位器序列号 <b>*</b> </label>
                    <s:textfield id="lNameId" cssClass="dfInput" placeholder="点击选择定位器" maxlength="12"
                                 name="bindingForm.locatorName" onfocus="focusConsultantClick('0')"/>
                    <s:hidden id="lId" name="bindingForm.locatorId"/>
                </li>
                <li style="height: 20px;">&nbsp;</li>
                <li>
                    <label> POS终端序列号 <b>*</b> </label>
                    <s:textfield id="tNameId" cssClass="dfInput" placeholder="点击选择POS终端" maxlength="12"
                                 name="bindingForm.terminalName" onfocus="focusConsultantClick('1')"/>
                    <s:hidden id="tId" name="bindingForm.terminalId"/>
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
</div>
</body>
</html>

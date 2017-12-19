<%--
    User: Myk   Date: 2017/11/2    Time: 14:29
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
    <script type="text/javascript" src="js/terminal.js"></script>
</head>
<body>
<div class="formBody">
    <form action="" method="post" name="tForm" id="tForm">
        <div class="formTitle">
            <span>
                ${currentTitle}
            </span>
        </div>
        <div>
            <ul class="formInfo">
                <li>
                    <label> POS终端序列号 </label>
                    <s:textfield id="tNameId" cssClass="dfInput" placeholder="请输入POS终端序列号" maxlength="12"
                                 name="terminalForm.terminalName"/>
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
        <s:hidden name="terminalForm.terminalId"/>
        <s:hidden name="curPage"/>
    </form>
</div>
</body>
</html>

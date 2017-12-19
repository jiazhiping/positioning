<%--
    User: Myk   Date: 2017/11/1    Time: 14:00
--%>
<%@ page language="Java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/MaYuePagination" prefix="p" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html manifest="cache.manifest">
<head>
    <base href="<%=basePath%>">
    <title>${currentTitle}</title>
    <script type="text/javascript" src="js/user.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="" method="post" name="urForm" id="urForm">
        <div class="tools">
            <ul class="toolbar"></ul>
            <div class="sEachForm"></div>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%"></th>
                <th width="6%">序号</th>
                <th width="6%">用户登录名</th>
                <th width="6%">用户名</th>
                <th>上次登录时间</th>
                <th width="10%">上次登录IP</th>
            </tr>
            <s:iterator value="userRecordViews" status="st">
                <tr>
                    <td align="center"></td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${userLoginName}</td>
                    <td>${userName}</td>
                    <td>
                        <s:date name="recordTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${recordIp}</td>
                </tr>
            </s:iterator>
        </table>
        <%--分页--%>
        <p:outpage pageSize="${pageSize}" totalPage="${totalPage}" pageMaxNumber="${pageMaxNumber}"
                   curPage="${curPage}"/>
        <%-- ★解决在文本框按回车键刷新界面的问题★ --%>
        <input id="hiddenText" type="text" style="display: none;"/>
    </form>
</div>
</body>
</html>

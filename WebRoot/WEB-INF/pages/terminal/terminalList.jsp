<%--
User: Myk   Date: 2017/11/2    Time: 13:56
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
    <script type="text/javascript" src="js/terminal.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="terminal_terminalList.action" method="post" name="tForm" id="tForm">
        <div class="tools">
            <ul class="toolbar">
                <li onclick="toAddClick()">
                    <span><img src="images/t01.png"/> </span>添加
                </li>
                <li onclick="deleteClick()">
                    <span><img src="images/t03.png"/> </span>删除
                </li>
            </ul>
            <div class="sEachForm"></div>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%">
                </th>
                <th width="6%">序号</th>
                <th width="15%">POS终端序列号</th>
                <th width="8%">绑定状态</th>
                <th>创建时间</th>
            </tr>
            <s:iterator value="terminals" status="st">
                <tr>
                    <td align="center">
                        <div class="radio i-checks">
                            <label> <input type="checkbox" value="${terminalId}" name="rdb"></label>
                        </div>
                    </td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${terminalName}</td>
                    <td>
                        <s:if test="%{terminalState == 0}">未绑定</s:if>
                        <s:else>已绑定</s:else>
                    </td>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
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

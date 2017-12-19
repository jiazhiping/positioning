<%--
    User: Myk   Date: 2017/11/2    Time: 16:12
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
    <script type="text/javascript" src="js/binding.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="binding_bindingList.action" method="post" name="bForm" id="bForm">
        <div class="tools">
            <ul class="toolbar">
                <li onclick="toAddClick()">
                    <span><img src="images/t01.png"/> </span>添加绑定
                </li>
                <li onclick="deleteClick()">
                    <span><img src="images/t03.png"/> </span>解除绑定
                </li>
            </ul>
            <div class="sEachForm"></div>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%"></th>
                <th width="6%">序号</th>
                <th width="15%">定位器序列号</th>
                <th width="15%">POS终端序列号</th>
                <th width="20%">绑定时间</th>
                <th width="10%">设备状态</th>
                <th>操作</th>
            </tr>
            <s:iterator value="bindingViews" status="st">
                <tr>
                    <td align="center">
                        <div class="radio i-checks">
                            <label> <input type="checkbox" value="${bindingId}" name="rdb"> </label>
                        </div>
                    </td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${locatorName}</td>
                    <td>${terminalName}</td>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <s:if test="%{deviceId == null }">
                            未上线
                        </s:if>
                        <s:else>
                            已上线
                        </s:else>
                    </td>
                    <td>
                        <a href="javascript:toMapClick('${bindingId}')" class="tableLink">地图定位此设备</a>
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

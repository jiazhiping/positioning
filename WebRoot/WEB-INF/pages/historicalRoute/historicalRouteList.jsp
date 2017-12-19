<%--
    User: Myk   Date: 2017/11/10    Time: 13:52
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
    <script type="text/javascript" src="js/deviceUpLineGps.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="historicalRoute_historicalRouteList.action" method="post" name="hrForm" id="hrForm">
        <div class="tools">
            <ul class="toolbar"></ul>
            <div class="sEachForm">
                <input type="button" class="scBtn" value="查询" onclick="queryClick()" style="float: right;"/>
                <div style="float: right; margin-right: 4px;">
                    <s:textfield name="historicalRouteQueryForm.locatorName" placeholder="请输入监控器序列号" cssClass="scinput"
                                 maxlength="16"/>
                </div>
                <label> 监控器序列号 </label>
            </div>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%">序号</th>
                <th width="15%">历史定位时间</th>
                <th width="9%">监控器序列号</th>
                <th width="12%">预设坐标</th>
                <th width="12%">定位坐标</th>
                <th width="9%">偏移距离</th>
                <th>操作</th>
            </tr>
            <s:iterator value="historicalRouteViews" status="st">
                <tr>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${locatorName}</td>
                    <td>
                        <s:if test="%{devicePositionLongitude != ''}">
                            ${devicePositionLongitude},${devicePositionLatitude}
                        </s:if>
                        <s:else>
                            此设备未管理
                        </s:else>
                    </td>
                    <td>${longitude},${latitude}</td>
                    <td>${distance}(米)</td>
                    <td>
                        <a href="javascript:HistoricalRouteMapClick('${locatorName}')" class="tableLink">查看该设备历史记录</a>
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

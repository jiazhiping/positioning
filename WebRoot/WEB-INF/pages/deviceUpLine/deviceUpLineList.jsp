<%--
    User: Myk   Date: 2017/11/8    Time: 17:56
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
    <script type="text/javascript" src="js/deviceUpLineList.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="deviceUpLine_deviceUpLineList.action" method="post" name="dForm" id="dForm">
        <div class="tools">
            <ul class="toolbar"></ul>
            <div class="sEachForm">
                <input type="button" class="scBtn" value="查询" onclick="queryClick()" style="float: right;"/>
                <div style="float: right; margin-right: 4px;">
                    <s:textfield name="deviceUpLineQueryForm.terminalName" placeholder="请输入POS终端序列号" cssClass="scinput"
                                 maxlength="16"/>
                </div>
                <label> POS终端序列号 </label>
                <div style="float: right; margin-right: 4px;">
                    <s:textfield name="deviceUpLineQueryForm.locatorName" placeholder="请输入监控器序列号" cssClass="scinput"
                                 maxlength="16"/>
                </div>
                <label> 监控器序列号 </label>
            </div>
        </div>
        <table class="tableList">
            <tr>
                <th width="5%"></th>
                <th width="5%">序号</th>
                <th width="9%">监控器序列号</th>
                <th width="9%">POS终端序列号</th>
                <th width="13%">预设坐标</th>
                <th width="13%">定位位置</th>
                <th width="9%">偏移距离</th>
                <th width="12%">定位时间</th>
                <th>操作</th>
            </tr>
            <s:iterator value="deviceUpLines" status="st">
                <tr>
                    <td align="center"></td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${locatorName}</td>
                    <td>${terminalName}</td>
                    <td>
                        <s:if test="%{devicePositionLongitude == ''}">
                            设备未录入系统
                        </s:if>
                        <s:else>
                            ${devicePositionLongitude},${devicePositionLatitude}
                        </s:else>
                    </td>
                    <td>${deviceCurrentPositionLongitude},${deviceCurrentPositionLatitude}</td>
                    <td>${deviceDistance}(米)</td>
                    <td>
                        <s:date name="deviceTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:monitoringClick('${deviceId}') " class='tableLink'>查看设备</a>
                        <a href="javascript:HistoricalRouteMapClick('${locatorName}') " class='tableLink'>历史轨迹</a>
                        <a href="javascript:tomonitoringRecordList('${locatorName}') " class='tableLink'>告警记录</a>
                        <a href="javascript:toFrequencyClick('${deviceId}')" class='tableLink'>设置上报频率</a>
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

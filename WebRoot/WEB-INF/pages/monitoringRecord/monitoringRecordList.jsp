<%--
    User: Myk   Date: 2017/11/9    Time: 14:13
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
    <script type="text/javascript" src="js/monitoringRecord.js"></script>
</head>
<body>
<div class="rightInfo">
    <form action="monitoringRecord_monitoringRecordList.action" method="post" name="mrForm" id="mrForm">
        <div class="tools">
            <ul class="toolbar" style="width:15%;">
                <li onclick="updateClick()">
                    <span><img src="images/t02.png"/> </span>处理告警
                </li>
            </ul>
            <div class="sEachForm" style="width:85%;">
                <input type="button" class="scBtn" value="查询" onclick="queryClick()" style="float: right;"/>
                <div style="float: right; margin-right: 4px;">
                    <s:select id="lFrequencyId" name="monitoringRecordQueryForm.monitoringGradeName" list="alarm"
                              headerKey=""
                              headerValue="全部" class="chosen-select" style="width:230px;"/>
                </div>
                <label> 告警级别 </label>
                <div style="float: right; margin-right: 4px;">
                    <s:textfield name="monitoringRecordQueryForm.terminalName" placeholder="请输入POS终端序列号"
                                 cssClass="scinput"
                                 maxlength="16"/>
                </div>
                <label> POS终端序列号 </label>
                <div style="float: right; margin-right: 4px;">
                    <s:textfield name="monitoringRecordQueryForm.locatorName" placeholder="请输入监控器序列号" cssClass="scinput"
                                 maxlength="16"/>
                </div>
                <label> 监控器序列号 </label>
            </div>
        </div>
        <table class="tableListMAW">
            <tr>
                <th width="5%"></th>
                <th width="5%">序号</th>
                <th width="9%">监控器序列号</th>
                <th width="9%">POS终端序列号</th>
                <th width="13%">告警时间</th>
                <th width="13%">告警类型</th>
                <th width="9%">告警级别</th>
                <th width="6%">处理状态</th>
                <th width="15%">处理时间</th>
                <th>操作</th>
            </tr>
            <s:iterator value="monitoringViews" status="st">
                <tr>
                    <td align="center">
                        <s:if test="%{monitoringStatus == 0}">
                            <div class="radio i-checks">
                                <label> <input type="checkbox" value="${monitoringId}" name="rdb"></label>
                            </div>
                        </s:if>
                    </td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${locatorName}</td>
                    <td>${terminalName}</td>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${monitoringType}</td>
                    <td>
                        <s:if test="%{monitoringGrade == 3}">
                            <div style="width: 100%;height: 100%;background: #ff6754;">critical</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 2}">
                            <div style="width: 100%;height: 100%;background: #fdf16c;">major</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 1}">
                            <div style="width: 100%;height: 100%;background: #FFaa24;">minor</div>
                        </s:if>
                        <s:if test="%{monitoringGrade == 0}">
                            <div style="width: 100%;height: 100%;background: #74abfe;">Information</div>
                        </s:if>
                    </td>
                    <td>
                        <s:if test="%{monitoringStatus == 0}">
                            活跃
                        </s:if>
                        <s:else>
                            已处理
                        </s:else>
                    </td>
                    <td>
                        <s:date name="UpdateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a href="javascript:MonitoringRecordClick('${locatorName}')" class="tableLink">查看该设备告警信息</a>
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

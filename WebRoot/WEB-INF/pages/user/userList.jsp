<%--
  Created by IntelliJ IDEA.
  User: Myk
  Date: 2017/10/31
  Time: 14:28
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/MaYuePagination" prefix="p" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
    <title>${currentTitle}</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="POS机定位系统">
    <meta http-equiv="description" content="POS机定位系统">
    <script type="text/javascript" src="js/user.js"></script>
</head>

<body>
<div class="rightInfo">
    <form action="user_userList.action" method="post" name="uForm" id="uForm">
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
                <th width="5%"></th>
                <th width="6%">序号</th>
                <th width="8%">用户名</th>
                <th width="7%">姓名</th>
                <th width="9%">电话</th>
                <th width="13%">邮箱</th>
                <th width="6%">部门</th>
                <th width="6%">权限</th>
                <th width="6%">登陆状态</th>
                <th width="12%">创建时间</th>
                <th>操作</th>
            </tr>
            <s:iterator value="userViews" status="st">
                <tr>
                    <td align="center">
                        <s:if test="%{userId != 1}">
                            <div class="radio i-checks">
                                <label> <input type="checkbox" value="${userId}" name="rdb"> </label>
                            </div>
                        </s:if>
                    </td>
                    <td>${st.count + pageSize * curPage}</td>
                    <td>${userLoginName}</td>
                    <td>${userName}</td>
                    <td>${userPhone}</td>
                    <td>${userEmail}</td>
                    <td>${userDepartment}</td>
                    <td>管理员</td>
                    <td>
                        <s:if test="%{userState == 0 }">
                            未登录
                        </s:if>
                        <s:elseif test="%{userState == 1 }">
                            已登录
                        </s:elseif>
                    </td>
                    <td>
                        <s:date name="CreateTime" format="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <s:if test="%{userId != 1}">
                            <a href="javascript:toUpdateClick('${userId}')" class="tableLink">修改用户信息</a>
                        </s:if>
                        <a href="javascript:LogClick('${userId}')" class="tableLink">查看操作日志</a>
                        <a href="javascript:seeClick('${userId}')" class="tableLink">重置密码</a>
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




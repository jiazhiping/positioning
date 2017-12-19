<%--
  Created by IntelliJ IDEA.
  User: Myk
  Date: 2017/10/22
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
    <!-- ☆此句话很重要（使用sitemesh模板，必须放到head的第一行，否则无效）-->
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE;chrome=1">
    <base href="<%=basePath%>">
    <title>欢迎登陆系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="定位系统">
    <meta http-equiv="description" content="定位系统">
    <link rel="Shortcut icon" href="images/web_ico.ico"/>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="stylesheet" type="text/css" href="css/icheck.css">
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/cloud.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <script type="text/javascript" src="js/placeholder.js"></script>
</head>

<body onkeydown="changFocus(event)">
<form action="" method="post" name="loginForm" id="loginForm">
    <div id="mainBody">
        <div id="cloud1" class="cloud"></div>
        <div id="cloud2" class="cloud"></div>
    </div>
    <div class="loginTop">
        <span>欢迎登录POS机位置管理系统</span>
        <ul>
            <li>
                <a href="javascript:">帮助</a>
            </li>
            <li>
                <a href="javascript:">关于</a>
            </li>
        </ul>
    </div>
    <div class="loginBody">
        <span class="systemLogo"></span>
        <div class="loginBox">
            <ul>
                <li>
                    <s:textfield id="LNId" name="userForm.userLoginName" class="loginUser" value="Admin"
                                 maxlength="20" placeholder="登录名"/>
                </li>
                <li>
                    <s:password id="LPId" name="userForm.userLoginPassword" class="loginPassword" value="123456"
                                showPassword="true" maxlength="16" placeholder="密码"/>
                </li>
                <li>
                    <div class="checkbox i-checks">
                        <input type="button" class="loginBtn" value="登录"
                               onclick="loginClick()"/>
                        <label>
                            <input type="checkbox" id="ck_rmbUser">
                            <i></i>记住密码
                        </label>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="loginBm">
        &copy; 版权所有
    </div>
    <script type="text/javascript" src="js/icheck.min.js"></script>
    <script>
        $(document).ready(function () {
            $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
        });
    </script>
</form>
</body>
</html>




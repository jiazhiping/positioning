<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    Object str = session.getAttribute("currentMenuId");
    String currentMenuId = "";
    String currentMenuSubId = "";
    if (str != null) {
        String strs[] = str.toString().split("_");
        currentMenuId = strs[0];
        if (strs.length > 1) {
            currentMenuSubId = strs[1];
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <!-- ☆此句话很重要（使用sitemesh模板，必须放到head的第一行，否则无效）-->
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE;chrome=1">
    <base href="<%=basePath%>"/>
    <script type="text/javascript">
        var basePath = "<%=basePath%>";
    </script>
    <title><decorator:title/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="POS机定位系统">
    <meta http-equiv="description" content="POS机定位系统">
    <link rel="stylesheet" type="text/css" href="css/moBan.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/map.css">
    <%-- 单选、多选（开始） --%>
    <link rel="stylesheet" type="text/css" href="css/icheck.css">
    <%-- 单选、多选（结束） --%>
    <%-- 分页（开始） --%>
    <link rel="stylesheet" type="text/css" href="css/pagein.css">
    <%-- 分页（结束） --%>
    <%-- 下拉列表（开始） --%>
    <link rel="stylesheet" type="text/css" href="css/chosen.css">
    <%-- 下拉列表（结束） --%>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/placeholder.js"></script>
    <script type="text/javascript" src="js/mainPage2017.js"></script>
    <script language="javascript" type="text/javascript"
            src="date/My97DatePicker/WdatePicker.js" charset="utf-8"
            defer="defer"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=bS5b0Oy9hbE9XGheCvvizAkyamuOzVEi"></script>
    <script type="text/javascript">
        var curw = $(window).width();
        if (curw < 1250) {
            curw = 1250;
        }
        var curh = $(window).height();
    </script>
    <script type="text/javascript">
        //★★★★★只读文本框，退格键失效★★★★★
        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
            var ev = e || window.event; //获取event对象
            var obj = ev.target || ev.srcElement; //获取事件源
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型
            //获取作为判断条件的事件类型
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            //并且readOnly属性为true或disabled属性为true的，则退格键失效
            var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
            //判断
            if (flag2 || flag1) return false;
        }

        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
    </script>
    <decorator:head/>
</head>
<body onload="funTime()">
<div id="topId">
    <div id="top">
        <div class="topLeft">
            <img src="images/logo.png"/>
        </div>
        <ul class="nav">
            <li>
                <a onclick="TopClick('实时监控')" class="selected">
                    <img src="images/icon01.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('历史轨迹')" class="selected">
                    <img src="images/icon02.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('告警记录')" class="selected">
                    <img src="images/icon03.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('设备管理')" class="selected">
                    <img src="images/icon04.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('统计分析')" class="selected">
                    <img src="images/icon05.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('用户管理')" class="selected">
                    <img src="images/icon06.png"/>
                </a>
            </li>
            <li>
                <a onclick="TopClick('系统设置')" class="selected">
                    <img src="images/icon07.png"/>
                </a>
            </li>
        </ul>
        <div class="topRight">
            <ul>
                <li>
                    <span>
                        <img src="images/help.png"/>
                    </span>
                    <a href="javascript:">帮助</a>
                </li>
                <li>
                    <a href="javascript:">关于</a>
                </li>
                <li>
                    <a href="javascript:UpdateUserPasswordTo();">修改密码</a>
                </li>
                <li>
                    <a href="javascript:exitClick('${currUserId}');">注销</a>
                </li>
                <li>
                    <a href="javascript:closeClick('${currUserId}');">退出</a>
                </li>
            </ul>
            <div class="user">
                <span class="span">${currUserName}</span>
                <span class="time" id="spId"></span>
            </div>
        </div>
    </div>
    <div id="container">
        <div id="menuId" class="container_left">
            <!-- <div class="leftTop"><span></span>系统功能列表</div> -->
            <dl class="leftMenu">
                <dd>
                    <div class="title" id="menu_01">
                        <span><img src="images/leftico01.png"/></span>实时监控
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0101".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('在线设备')">
                            <cite></cite><a href="javascript:">在线设备 </a><i></i>
                        </li>
                        <li <%if ("0102".equals(currentMenuSubId)) {%> class="active"<%}%>
                                                                       onclick="selectSubClick('实时监控')">
                            <cite></cite><a href="javascript:">实时监控</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_02">
                        <span><img src="images/leftico01.png"/></span>历史轨迹
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0201".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('历史轨迹')">
                            <cite></cite><a href="javascript:">历史轨迹</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_03">
                        <span><img src="images/leftico03.png"/></span>告警记录
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0301".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('告警记录管理')">
                            <cite></cite><a href="javascript:">告警记录管理</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_04">
                        <span><img src="images/leftico04.png"/></span>设备管理
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0401".equals(currentMenuSubId)) {%> class="active"<%}%>
                                                                       onclick="selectSubClick('定位器管理')">
                            <cite></cite><a href="javascript:">定位器管理</a><i></i>
                        </li>
                        <li <%if ("0402".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('POS终端管理')">
                            <cite></cite><a href="javascript:">POS终端管理</a><i></i>
                        </li>
                        <li <%if ("0403".equals(currentMenuSubId)) {%> class="active"<%}%>
                                                                       onclick="selectSubClick('关系管理')">
                            <cite></cite><a href="javascript:">关系管理</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_05">
                        <span><img src="images/leftico05.png"/></span>统计分析
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0501".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('告警类型统计')">
                            <cite></cite><a href="javascript:">告警类型统计</a><i></i>
                        </li>
                        <li <%if ("0502".equals(currentMenuSubId)) {%> class="active"<%}%>
                                                                       onclick="selectSubClick('告警级别统计')">
                            <cite></cite><a href="javascript:">告警级别统计</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_06">
                        <span><img src="images/leftico06.png"/></span>用户管理
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0601".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('用户列表')">
                            <cite></cite><a href="javascript:">用户列表</a><i></i>
                        </li>
                        <li <%if ("0602".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('操作日志')">
                            <cite></cite><a href="javascript:">操作日志</a><i></i>
                        </li>
                    </ul>
                </dd>
                <dd>
                    <div class="title" id="menu_07">
                        <span><img src="images/leftico07.png"/></span>系统设置
                    </div>
                    <ul class="menusOn">
                        <li <%if ("0701".equals(currentMenuSubId)) {%> class="active" <%}%>
                                                                       onclick="selectSubClick('告警阈值设置')">
                            <cite></cite><a href="javascript:">告警阈值设置</a><i></i>
                        </li>
                    </ul>
                </dd>
            </dl>
            <script type="text/javascript">
                var mmid = "<%=currentMenuId%>";
                $("#menu_" + mmid).next('ul').show();//slideDown
            </script>
        </div>
        <div id="workId" class="container_middle">
            <div class="place">
                <span>当前位置：</span>
                <ul class="placeUl">
                    <li>
                        <a href="javascript:">${currentTitle}</a>
                    </li>
                </ul>
            </div>
            <div id="workContentId">
                <decorator:body/>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    //$("#topId").width(curw - 11);
    //$("#workId").width(curw - 188 - 2 - 10);
    var temph = curh - 100 - 5;
    if (temph < 550) {
        temph = 550;
    }
    $("#menuId").height(temph);
    //$("#workContentId").width(curw - 188 - 2 - 10);
    $("#workContentId").height(temph - 40);
    $(".tableList tr:odd").addClass("odd");
    $(".tableListMAW tr:odd").addClass("odd");
</script>
<%-- 单选、多选（开始） --%>
<script type="text/javascript" src="js/icheck.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green"})
    });
</script>
<%-- 单选、多选（结束） --%>
<%-- 下拉列表（开始） --%>
<script src="js/chosen.jquery.js"></script>
<%-- 下拉列表（结束） --%>
</body>
</html>

$(function () {
    //顶部导航切换
    $(".nav li a").click(function () {
        $(".nav li a.selected").removeClass("selected");
        $(this).addClass("selected");
    })
});

function UpdateUserPasswordTo() {
    myzLayerOpen('修改当前用户密码界面', 360, 260, 'user_UpdateUserPasswordTo.action', "");
}

function exitClick(userId) {
    layer.confirm("您确认要注销吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            //★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'user_userExitUser.action',
                dataType: 'json',
                async: true,
                data: {
                    'userForm.userId': userId
                },
                success: function (data) {
                    if (data.flag === 0) {
                        window.location.href = "exitUser.action";
                    }
                },
                complete: function (XHR, textStatus) {
                    var str = XHR.responseText;
                    if (str === "noLoginAjax") {
                        layer.alert("对不起，系统未登录，不能继续操作！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.top.location.href = "exitUser.action";
                        });
                    }
                }
            });
        }, function (index) {
            layer.close(index);
        });
}

function closeClick(userId) {
    layer.confirm("您确认要退出吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            //★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'user_userExitUser.action',
                dataType: 'json',
                async: true,
                data: {
                    'userForm.userId': userId
                },
                success: function (data) {
                    if (data.flag === 0) {
                        if (navigator.userAgent.indexOf("MSIE") > 0) {
                            if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
                                window.top.opener = null;
                                window.close();
                            } else {
                                window.open('', '_top');
                                window.top.close();
                            }
                        } else if (navigator.userAgent.indexOf("Firefox") > 0) {
                            window.location.href = 'about:blank ';
                        } else {
                            window.top.opener = null;
                            window.open('', '_self', '');
                            window.close();
                        }
                    }
                },
                complete: function (XHR, textStatus) {
                    var str = XHR.responseText;
                    if (str === "noLoginAjax") {
                        layer.alert("对不起，系统未登录，不能继续操作！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.top.location.href = "exitUser.action";
                        });
                    }
                }
            });
        }, function (index) {
            layer.close(index);
        });
}

function returnWeek(currWeek) {
    var weeks = ["日", "一", "二", "三", "四", "五", "六"];
    return "星期" + weeks[currWeek];
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,//月份
        "d+": this.getDate(),  //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(),//分
        "s+": this.getSeconds()//秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

function funTime() {
    var t = new Date();
    var str = t.Format("yyyy-MM-dd HH:mm:ss");
    var week = returnWeek(t.getDay());
    spId.innerHTML = str + " " + week;
    window.setTimeout("funTime()", 1000);
}

$(function () {
    //导航菜单切换
    $(".menusOn li").click(function () {
        $(".menusOn li.active").removeClass("active")
        $(this).addClass("active");
    });
    $('.title').click(function () {
        var $ul = $(this).next('ul');
        $('dd').find('ul').slideUp();//slideUp
        if ($ul.is(':visible')) {
            $(this).next('ul').slideUp();//slideUp
        } else {
            $(this).next('ul').slideDown();//slideDown
        }
    });
});

function selectSubClick(title) {
    var url = "";
    if (title === "在线设备") {
        url = "deviceUpLine_deviceUpLineList.action";
    }
    if (title === "实时监控") {
        url = "monitoring_monitoringList.action";
    }
    if (title === "历史轨迹") {
        url = "historicalRoute_historicalRouteList.action";
    }
    if (title === "告警记录管理") {
        url = "monitoringRecord_monitoringRecordList.action";
    }
    if (title === "告警类型统计") {
        url = "analyze_analyzeList.action";
    }
    if (title === "告警级别统计") {
        url = "analyze_analyzeToList.action";
    }
    if (title === "用户列表") {
        url = "user_userList.action";
    }
    if (title === "操作日志") {
        url = "user_userRecordList.action";
    }
    if (title === "定位器管理") {
        url = "locator_locatorList.action";
    }
    if (title === "POS终端管理") {
        url = "terminal_terminalList.action";
    }
    if (title === "关系管理") {
        url = "binding_bindingList.action";
    }
    if (title === "告警阈值设置") {
        url = "foundation_foundationList.action";
    }
    if (url === "") {
        windowAlert("该功能正在研发中……", "");
        return;
    }
    window.location.href = basePath + url;
}

function TopClick(title) {
    var url = "";
    if (title === "实时监控") {
        url = "monitoring_monitoringList.action";
    }
    if (title === "用户管理") {
        url = "user_userList.action";
    }
    if (title === "设备管理") {
        url = "binding_bindingList.action";
    }
    if (title === "告警记录") {
        url = "monitoringRecord_monitoringRecordList.action";
    }
    if (title === "历史轨迹") {
        url = "historicalRoute_historicalRouteList.action";
    }
    if (title === "系统设置") {
        url = "foundation_foundationList.action";
    }
    if (title === "统计分析") {
        url = "analyze_analyzeList.action";
    }
    if (url === "") {
        windowAlert("该功能正在研发中……", "");
        return;
    }
    window.location.href = basePath + url;
}



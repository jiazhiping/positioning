$(function () {
    $(".loginBox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    $(window).resize(function () {
        $(".loginBox").css({"position": "absolute", "left": ($(window).width() - 692) / 2});
    });
});

function checkForm() {
    var Name = $("#LNId").val();
    if (Name === "") {
        windowAlert("对不起，【登录名】不能为空！", "LNId");
        return true;
    }

    var Password = $("#LPId").val();
    if (Password === "") {
        windowAlert("对不起，【密码】不能为空！", "LPId");
        return true;
    }

    return false;
}

function loginClick() {
    if (checkForm()) {
        return;
    }
    $.ajax({
        type: 'post',
        url: 'login.action',
        dataType: 'json',
        async: true,
        data: $("#loginForm").serialize(),
        success: function (data) {
            if (data.flag === 0) {
                saveCookie();
                window.location.href = "monitoring_monitoringList.action";
            } else if (data.flag === 1) {
                windowAlert("对不起，【登录名】不存在!", "LNId");
            } else if (data.flag === 2) {
                windowAlert("对不起，【密码】不正确!", "LPId");
            }
        }
    });
}

// 保存用户名和密码的Cookie
function saveCookie() {
    var expireDays = 15;// 存储一个带15天期限的cookie
    if ($("#ck_rmbUser").attr("checked")) {
        var username = $("#LNId").val();
        var password = $("#LPId").val();
        $.cookie("rmbUser", "true", {
            expires : expireDays
        });
        $.cookie("username", username, {
            expires : expireDays
        });
        $.cookie("password", password, {
            expires : expireDays
        });
    } else {
        $.cookie("rmbUser", "false", {
            expire : -1
        });
        $.cookie("username", "", {
            expires : -1
        });
        $.cookie("password", "", {
            expires : -1
        });
    }
}

$(document).ready(function() {
    if ($.cookie("rmbUser") === "true") {
        $("#ck_rmbUser").attr("checked", true);
        $("#LNId").val($.cookie("username"));
        $("#LPId").val($.cookie("password"));
    } else {
        $("#ck_rmbUser").attr("checked", false);
        $("#LNId").val("");
        $("#LPId").val("");
    }
});



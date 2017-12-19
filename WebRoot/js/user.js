//★★★单选按钮单击激发事件★★★
$(function () {
    $('input').on('ifClicked', function (event) {
        if ($(this).attr("checked")) {
            $(this).iCheck('uncheck');// 不选中
        }
    });
});

function queryClick() {
    $("#fileId").attr("value", "");
    document.uForm.action = "user_userList.action";
    document.uForm.submit();
}

function toAddClick() {
    document.uForm.action = "user_toAddUser.action";
    document.uForm.submit();
}


function toUpdateClick(userId) {
    document.uForm.action = "user_toUpdateUser.action?userForm.userId=" + userId;
    document.uForm.submit();
}

function checkForm(type) {
    if (type === 0) {
        var uLoginPasswordId = $("#uLoginPasswordId").val();
        if (uLoginPasswordId === "") {
            windowAlert("对不起，请输入【密码】！", "uLoginPasswordId");
            return true;
        }

        var uLoginPasswordIds = $("#uLoginPasswordIds").val();
        if (uLoginPasswordIds === "") {
            windowAlert("对不起，请确认【密码】！", "uLoginPasswordIds");
            return true;
        }

        if (uLoginPasswordIds !== uLoginPasswordId) {
            windowAlert("对不起，两次输入【密码】不一致！", "uLoginNameIds");
            return true;
        }
    }

    if (type === 1) {
        var upLoginPasswordId = $("#upLoginPasswordId").val();
        var upLoginPasswordIds = $("#upLoginPasswordIds").val();
        if (upLoginPasswordId !== "" && upLoginPasswordIds === "") {
            windowAlert("对不起，请确认【新密码】！", "upLoginPasswordIds");
            return true;
        }

        if (upLoginPasswordId !== "" && upLoginPasswordIds !== "" && upLoginPasswordId !== upLoginPasswordIds) {
            windowAlert("对不起，两次输入【新密码】不一致！", "upLoginPasswordIds");
            return true;
        }
    }

    if (type === 2) {
        var uLoginPasswordId = $("#uLoginPasswordId").val();
        var uLoginPasswordIds = $("#uLoginPasswordIds").val();
        var uLoginPasswordIdNew = $("#uLoginPasswordIdNew").val();
        if (uLoginPasswordId === "") {
            windowAlert("对不起，请输入【原密码】！", "uLoginPasswordId");
            return true;
        }

        if (uLoginPasswordIds === "") {
            windowAlert("对不起，请输入【新密码】！", "uLoginPasswordIds");
            return true;
        }

        if (uLoginPasswordIdNew === "") {
            windowAlert("对不起，请确认【新密码】！", "uLoginPasswordIdNew");
            return true;
        }

        if (uLoginPasswordIds !== "" && uLoginPasswordIdNew !== "" && uLoginPasswordIds !== uLoginPasswordIdNew) {
            windowAlert("对不起，两次输入【新密码】不一致！", "uLoginPasswordIdNew");
            return true;
        }

    } else {
        var uLoginNameId = $("#uLoginNameId").val();
        if (uLoginNameId === "") {
            windowAlert("对不起，请输入【用户名】！", "uLoginNameId");
            return true;
        }

    }
}

function addClick() {
    if (checkForm(0)) {
        return;
    }
    layer.confirm("您确认要添加用户吗？", {
        btn: ['确认', '取消'],
        closeBtn: 2,
        icon: 3,
        title: '提示'
    }, function (index) {
        layer.close(index);
        var msg = $("#uForm").serialize();
        layer.msg('系统正在提交数据，请稍等...', {
            icon: 16,
            time: 0,
            shade: 0.7
        });
        // 上传图片
        $.ajaxFileUpload({
            url: 'user_addUser.action?' + msg,
            type: 'POST',
            secureuri: false,
            fileElementId: ['fileId'],
            dataType: 'json',
            success: function (data) {
                if (data.flag === 0) {
                    layer.alert("恭喜您，添加用户成功！", {
                        skin: 'layui-layer-molv',
                        closeBtn: 0,
                        icon: 1,
                        title: '警告'
                    }, function (index) {
                        layer.close(index);
                        queryClick();
                    });
                } else if (data.flag === 1) {
                    windowAlert("对不起，此用户已存在！", "");
                } else if (data.flag === 2) {
                    windowAlert("对不起，Bean赋值有误！", "");
                } else if (data.flag === 3) {
                    windowAlert("对不起，添加失败！", "");
                }
            },
            complete: function (XHR, textStatus) {
                var str = XHR.responseText;
                if (str === "noLoginAjax") {
                    layer.alert("对不起，系统未登录，不能继续操作！", {
                        skin: 'layui-layer-molv',
                        closeBtn: 0,
                        icon: 0,
                        title: '提示'
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

function updateClick() {
    // （1）判断组件内容是否为空
    if (checkForm(1)) {
        return;
    }
    layer.confirm("您确认要修改用户信息吗？", {
        btn: ['确认', '取消'],
        closeBtn: 2,
        icon: 3,
        title: '提示'
    }, function (index) {
        layer.close(index);
        var msg = $("#uForm").serialize();
        layer.msg('系统正在提交数据，请稍等...', {
            icon: 16,
            time: 0,
            shade: 0.7
        });
        // 上传图片
        $.ajaxFileUpload({
            url: 'user_updateUser.action?' + msg,
            type: 'POST',
            secureuri: false,
            fileElementId: ['fileId'],
            dataType: 'json',
            success: function (data) {
                if (data.flag === 0) {
                    layer.alert("恭喜您，修改用户信息成功！", {
                        skin: 'layui-layer-molv',
                        closeBtn: 0, icon: 0, title: '提示'
                    }, function (index) {
                        layer.close(index);
                        window.parent.queryClick();
                    });
                } else if (data.flag === 1) {
                    windowAlert("对不起，此用户已经存在，不能继续修改！", "");
                } else if (data.flag === 2) {
                    windowAlert("对不起，修改用户信息失败！", "");
                } else if (data.flag === 3) {
                    windowAlert("对不起，Bean赋值有误！", "");
                }
            },
            complete: function (XHR, textStatus) {
                var str = XHR.responseText;
                if (str === "noLoginAjax") {
                    layer.alert("对不起，系统未登录，不能继续操作！", {
                        skin: 'layui-layer-molv',
                        closeBtn: 0,
                        icon: 0,
                        title: '提示'
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

function deleteClick() {
    var userIds = selectCheckedClick(1);
    if (userIds === "") {
        return;
    }
    layer.confirm("您确认要删除用户信息吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'user_deleteUser.action',
                dataType: 'json',
                async: true,
                data: {
                    'userForm.userIds': userIds
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，删除用户信息成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            queryClick();
                        });
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

function LogClick(userId) {
    document.uForm.action = "user_userRecordList.action?userForm.userId=" + userId;
    document.uForm.submit();
}

function seeClick(userId) {
    layer.confirm("您确认要重置密码么？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'user_updateUserPassword.action',
                dataType: 'json',
                async: true,
                data: {
                    'userForm.userId': userId
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("密码已重置！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            queryClick();
                        });
                    }
                },
                complete: function (XHR, textStatus) {
                    var str = XHR.responseText;
                    if (str == "noLoginAjax") {
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

function updateUserPasswordClick() {
    if (checkForm(2)) {
        return;
    }
    layer.confirm("您确认要修改密码吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // 此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'user_updatePassword.action',
                dataType: 'json',
                async: true,
                data: $("#uForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，修改密码成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.top.location.href = "exitUser.action";
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，【原密码】不正确！", "uLoginPasswordId");
                    } else if (data.flag === 2) {
                        windowAlert("对不起，修改密码失败！", "");
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
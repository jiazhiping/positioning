// //★★★按钮单击激发事件★★★
$(function () {
    $('input').on('ifClicked', function (event) {
        if ($(this).attr("checked")) {
            $(this).iCheck('uncheck');// 不选中
        }
    });
});

function queryClick() {
    document.tForm.action = "terminal_terminalList.action";
    document.tForm.submit();
}

function toAddClick() {
    document.tForm.action = "terminal_toAddTerminal.action";
    document.tForm.submit();
}

function checkForm() {
    var tNameId = $("#tNameId").val();
    if (tNameId === "") {
        windowAlert("对不起，请输入【POS终端序列号】！", "tNameId");
        return true;
    }

    return false;
}

function addClick() {
    if (checkForm()) {
        return;
    }
    layer.confirm("您确认要添加POS终端吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // 此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'terminal_addTerminal.action',
                dataType: 'json',
                async: true,
                data: $("#tForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，添加POS终端成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，此POS终端已存在！", "");
                    } else if (data.flag === 2) {
                        windowAlert("对不起，添加失败！", "");
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

function toUpdateClick() {
    var terminalId = selectRdoClick(1);
    if (terminalId === "") {
        return;
    }
    document.tForm.action = "terminal_toUpdateTerminal.action?terminalForm.terminalId=" + terminalId;
    document.tForm.submit();
}

function updateClick() {
    // （1）判断组件内容是否为空
    if (checkForm()) {
        return;
    }
    // （2）Ajax提交数据
    layer.confirm("您确认要修改POS终端吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'terminal_updateTerminal.action',
                dataType: 'json',
                async: true,
                data: $("#tForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，修改POS终端成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，此POS终端已经存在，不能继续修改！", "");
                    } else if (data.flag === 2) {
                        windowAlert("对不起，修改POS终端失败！", "");
                    } else if (data.flag === 3) {
                        windowAlert("对不起，Bean赋值有误！", "");
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

function deleteClick() {
    var terminalIds = selectCheckedClick(1);
    if (terminalIds === "") {
        return;
    }
    layer.confirm("您确认要删除POS终端信息吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'terminal_deleteTerminal.action',
                dataType: 'json',
                async: true,
                data: {
                    'terminalForm.terminalIds': terminalIds
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，删除POS终端信息成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，" + data.terminalName + "已绑定，解绑后才能进行删除操作！", "");
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




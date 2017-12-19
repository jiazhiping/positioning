//★★★单选按钮单击激发事件★★★
$(function () {
    $('input').on('ifClicked', function (event) {
        if ($(this).attr("checked")) {
            $(this).iCheck('uncheck');// 不选中
        }
    });
});

function queryClick() {
    document.bForm.action = "binding_bindingList.action";
    document.bForm.submit();
}

function toAddClick() {
    document.bForm.action = "binding_toAddBinding.action";
    document.bForm.submit();
}

function checkForm() {
    var lNameId = $("#lNameId").val();
    if (lNameId === "") {
        windowAlert("对不起，请选择【定位器】！", "lNameId");
        return true;
    }

    var tNameId = $("#tNameId").val();
    if (tNameId === "") {
        windowAlert("对不起，请选择【POS终端】！", "tNameId");
        return true;
    }

}

function addClick() {
    if (checkForm()) {
        return;
    }
    var lNameId = $("#lNameId").val();
    var tNameId = $("#tNameId").val();
    layer.confirm("您确认将" + lNameId + "与" + tNameId + "绑定吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // 此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'binding_addBinding.action',
                dataType: 'json',
                async: true,
                data: $("#bForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，绑定成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，绑定失败！", "");
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
    var bindingIds = selectCheckedClick(1);
    if (bindingIds === "") {
        return;
    }
    layer.confirm("您确认要解除此绑定吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'binding_deleteBinding.action',
                dataType: 'json',
                async: true,
                data: {
                    'bindingForm.bindingIds': bindingIds
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，解绑成功！", {
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

function focusConsultantClick(type) {
    if (type === '0') {
        myzLayerOpen("未绑定定位器选择", 700, 550, 'locator_locatorSelect.action', "")
    }

    if (type === '1') {
        myzLayerOpen("未绑定POS终端选择界面", 700, 550, 'terminal_terminalSelect.action', "")
    }

}

function dbSelectLocatorClick(locatorId, locatorName) {
    window.parent.fillSelectLocator(locatorId, locatorName);
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iFrame层的索引
    parent.layer.close(index); //再执行关闭
}

function dbSelectTerminalClick(terminalId, terminalName) {
    window.parent.fillSelectTerminal(terminalId, terminalName);
    var index = parent.layer.getFrameIndex(window.name); //先得到当前iFrame层的索引
    parent.layer.close(index); //再执行关闭
}

function fillSelectLocator(locatorId, locatorName) {
    $("#lId").attr("value", locatorId);
    $("#lNameId").attr("value", locatorName);
}

function fillSelectTerminal(terminalId, terminalName) {
    $("#tId").attr("value", terminalId);
    $("#tNameId").attr("value", terminalName);
}

function toMapClick(bindingId) {
    $.ajax({
        type: 'post',
        url: 'binding_toMapBinding.action',
        dataType: 'json',
        async: true,
        data: {
            'bindingForm.bindingId': bindingId
        },
        success: function (data) {
            if (data.flag === 0) {
                document.bForm.action = "monitoring_monitoringList.action?monitoringForm.deviceId=" + data.deviceId;
                document.bForm.submit();
            } else if (data.flag === 1) {
                windowAlert("对不起，该设备未上线！", "");
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
}
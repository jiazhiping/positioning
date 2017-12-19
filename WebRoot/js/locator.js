//★★★单选按钮单击激发事件★★★
$(function () {
    $('input').on('ifClicked', function (event) {
        if ($(this).attr("checked")) {
            $(this).iCheck('uncheck');// 不选中
        }
    });
});

function queryClick() {
    document.lForm.action = "locator_locatorList.action";
    document.lForm.submit();
}

function toAddClick() {
    document.lForm.action = "locator_toAddLocator.action";
    document.lForm.submit();
}

function checkForm() {
    var lNameId = $("#lNameId").val();
    if (lNameId === "") {
        windowAlert("对不起，请输入【序列号】！", "lNameId");
        return true;
    }

    var lPositionLngId = $("#lPositionLngId").val();
    if (lPositionLngId === "") {
        windowAlert("对不起，请输入【预设位置:经度】！", "lPositionLngId");
        return true;
    }

    var lPositionLatId = $("#lPositionLatId").val();
    if (lPositionLatId === "") {
        windowAlert("对不起，请输入【预设位置:纬度】！", "lPositionLatId");
        return true;
    }
    var lFrequencyId = $("#lFrequencyId").val();
    if (lFrequencyId === "") {
        windowAlert("对不起，请选择【上报频率】！", "lFrequencyId");
        return true;
    }

    return false;
}

function addClick() {
    if (checkForm()) {
        return;
    }
    layer.confirm("您确认要添加定位器吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // 此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'locator_addLocator.action',
                dataType: 'json',
                async: true,
                data: $("#lForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，添加定位器成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，此定位器已存在！", "");
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
    var locatorId = selectRdoClick(1);
    if (locatorId === "") {
        return;
    }
    document.lForm.action = "locator_toUpdateLocator.action?locatorForm.locatorId=" + locatorId;
    document.lForm.submit();
}

function updateClick() {
    // （1）判断组件内容是否为空
    if (checkForm()) {
        return;
    }
    // （2）Ajax提交数据
    layer.confirm("您确认要修改定位器吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'locator_updateLocator.action',
                dataType: 'json',
                async: true,
                data: $("#lForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，修改定位器成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，此定位器已经存在，不能继续修改！", "");
                    } else if (data.flag === 2) {
                        windowAlert("对不起，修改定位器失败！", "");
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
    var locatorIds = selectCheckedClick(1);
    if (locatorIds === "") {
        return;
    }
    layer.confirm("您确认要删除定位器信息吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'locator_deleteLocator.action',
                dataType: 'json',
                async: true,
                data: {
                    'locatorForm.locatorIds': locatorIds
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，删除定位器信息成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，" + data.locatorName + "定位器已绑定，解绑后才能进行删除操作！", "");
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

function ckAll() {
    var flag = document.getElementById("allChecks").checked;
    var cks = document.getElementsByName("check");
    for (var i = 0; i < cks.length; i++) {
        cks[i].checked = flag;
    }
}

function toMapClick(bindingId) {
    $.ajax({
        type: 'post',
        url: 'locator_toMapLocator.action',
        dataType: 'json',
        async: true,
        data: {
            'locatorForm.bindingId': bindingId
        },
        success: function (data) {
            if (data.flag === 0) {
                document.lForm.action = "monitoring_monitoringList.action?monitoringForm.deviceId=" + data.deviceId;
                document.lForm.submit();
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
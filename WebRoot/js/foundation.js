function checkForm() {
    var thresholdId = $("#thresholdId").val();
    if (thresholdId === "") {
        windowAlert("对不起，【最大告警距离】不能为空！", "thresholdId");
        return true;
    }
    var batteryId = $("#batteryId").val();
    if (batteryId === "") {
        windowAlert("对不起，【最低电量】不能为空！", "batteryId");
        return true;
    }

    return false;
}


function updateClick() {
    // （1）判断组件内容是否为空
    if (checkForm()) {
        return;
    }
    // （2）Ajax提交数据
    layer.confirm("您确认要修改系统基本设置吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'foundation_updateFoundation.action',
                dataType: 'json',
                async: true,
                data: $("#fForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，修改全局告警阈值成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            window.parent.queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，修改失败！", "");
                    } else if (data.flag === 2) {
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
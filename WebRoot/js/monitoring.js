function toFrequencyClick(deviceId) {
    myzLayerOpen('设备上报频率变更', 470, 300, 'monitoring_updateFrequencyTo.action?monitoringForm.deviceId=' + deviceId, "")
}

function checkForm() {
    var fid = $("#fid").val();
    if (fid === "") {
        windowAlert("对不起，【上报频率】不能为空！", "fid");
        return true;
    }

    return false;
}

function updateFrequency() {
    if (checkForm()) {
        return;
    }
    layer.confirm("您确认要修改上报频率吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'monitoring_updateFrequency.action',
                dataType: 'json',
                async: true,
                data: $("#fForm").serialize(),
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，上报频率修改成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            window.parent.location.reload();
                            layer.close(index);
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

function toHistoryClick(locatorName) {
    document.fForm.action = "historicalRoute_toHistoricalRoute.action?historicalRouteForm.locatorName=" + locatorName;
    document.fForm.submit();
}

function mapCenter(monitoringId) {
    $.ajax({
        type: 'post',
        url: 'monitoring_monitoring.action',
        dataType: 'json',
        async: true,
        data: {
            'monitoringForm.monitoringId': monitoringId
        },
        success: function (data) {
            if (data.flag === 0) {
                map.setZoom(14);
                var point = new BMap.Point(data.mapLongitude, data.mapLatitude);              // 填充标注点
                map.panTo(point);
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

function mapQueryClick() {
    $.ajax({
        type: 'post',
        url: 'monitoring_mapQuery.action',
        dataType: 'json',
        async: true,
        data: $("#mpForm").serialize(),
        success: function (data) {
            if (data.flag === 0) {
                map.setZoom(14);
                var point = new BMap.Point(data.mapLongitude, data.mapLatitude);              // 填充标注点
                map.panTo(point);
            } else if (data.flag === 1) {
                windowAlert("对不起，该定位器未上线！", "");
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

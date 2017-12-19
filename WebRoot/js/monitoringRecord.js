// //★★★按钮单击激发事件★★★
$(function () {
    $('input').on('ifClicked', function (event) {
        if ($(this).attr("checked")) {
            $(this).iCheck('uncheck');// 不选中
        }
    });
});

function queryClick() {
    document.mrForm.action = "monitoringRecord_monitoringRecordList.action";
    document.mrForm.submit();
}

function updateClick() {
    var monitoringIds = selectCheckedClick(0);
    if (monitoringIds === "") {
        return;
    }
    // Ajax提交数据
    layer.confirm("您确认该告警已处理吗？", {
            btn: ['确认', '取消'], closeBtn: 2, icon: 3, title: '提示'
        },
        function (index) {
            layer.close(index);
            // ★★★此处确认之后提交数据
            $.ajax({
                type: 'post',
                url: 'monitoringRecord_updateMonitoringRecord.action',
                dataType: 'json',
                async: true,
                data: {
                    'monitoringRecordForm.monitoringIds': monitoringIds
                },
                success: function (data) {
                    if (data.flag === 0) {
                        layer.alert("恭喜您，告警处理成功！", {
                            skin: 'layui-layer-molv',
                            closeBtn: 0, icon: 0, title: '提示'
                        }, function (index) {
                            layer.close(index);
                            queryClick();
                        });
                    } else if (data.flag === 1) {
                        windowAlert("对不起，告警处理失败！", "");
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

function MonitoringRecordClick(locatorName) {
    document.mrForm.action = "monitoringRecord_toMonitoringRecordMap.action?monitoringRecordForm.locatorName=" + locatorName;
    document.mrForm.submit();
}
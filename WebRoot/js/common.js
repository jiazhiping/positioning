//当你想关闭当前页的某个层时
//var index = layer.open();
//var index = layer.alert();
//var index = layer.load();
//var index = layer.tips();
//正如你看到的，每一种弹层调用方式，都会返回一个index
//layer.close(index); //此时你只需要把获得的index，轻轻地赋予layer.close即可
//当你在iframe页面关闭自身时
//var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
//parent.layer.close(index); //再执行关闭   
//layer.closeAll(); //疯狂模式，关闭所有层

//（1）弹出层
//标题，层宽度，层高度，跳转url，层关闭设置组件焦点的id值
function myzLayerOpen(title, width, height, url, idFocus) {
    layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: 0.7,
        area: [width + 'px', height + 'px'],
        closeBtn: 2,
        skin: 'layui-layer-rim',
        content: url,
        cancel: function () {
            $("#" + idFocus).focus();
        }
    });
}

function myzLayerOpenLayer(title, width, height, url, idFocus) {
    layer.open({
        type: 2,
        title: title,
        shadeClose: false,
        shade: 0.7,
        area: [width + 'px', height + 'px'],
        closeBtn: 1,
        skin: 'layui-layer-rim',
        content: url,
        cancel: function () {
            $("#" + idFocus).focus();
        }
    });
}

// （2）警告对话框： 此方法不支持堵塞
// 标题，层关闭设置组件焦点的id值
function windowAlert(title, idFocus) {
    layer.alert(title, {
        skin: 'layui-layer-molv',
        closeBtn: 0,
        icon: 0,
        title: '警告'
    }, function (index) {
        $("#" + idFocus).select();
        layer.close(index);
    });
}

// （3）确认对话框：此方法不支持堵塞
function windowConfirm() {
    layer.confirm("您确认要修改用户信息吗？", {
        btn: ['确认', '取消'],
        closeBtn: 2,
        icon: 3,
        title: '提示'
    }, function (index) {
        layer.close(index);
        // ★★★此处确认之后提交数据

    }, function (index) {
        layer.close(index);
    });
}

// （4）提交大量的数据时候使用：界面显示加载界面
// layer.msg('系统正在提交数据，请稍等...', {icon: 16,time: 0,shade: 0.7});
// 关闭加载界面：layer.closeAll();

function selectRdoClick(type) {
    var id = "";
    $("form input[name='rdb']:radio").each(function (i, o) {
        if (o.checked) {
            id = o.value;
            return false;
        }
    });
    if (id === "") {
        var str = "修改";
        if (type === 2) {
            str = "删除";
        } else if (type === 3) {
            str = "添加";
        }
        windowAlert("对不起，请先选择要" + str + "的记录！", "");
        return "";
    }
    return id;
}

function selectCheckedClick(type) {
    var codes = "";
    $("input[name='rdb']:checked").each(function (index, ele) {
        codes += $(ele).val() + ",";
    });
    if (codes === "") {
        var str = "";
        if (type === 0) {
            str = "处理";
        } else if (type === 1) {
            str = "删除";
        }
        windowAlert("对不起，请先选择要" + str + "的记录！", "");
        return "";

    }
    return codes;
}

// 月份增减方法
function addMonth(date, num) {
    num = parseInt(num);
    var sDate = dateToDate(date);
    var sYear = sDate.getFullYear();
    var sMonth = sDate.getMonth();
    var sDay = sDate.getDate();
    if (num == 0) {
        return new Date(sYear, sMonth, sDay);
    }
    var eYear = sYear;
    var eMonth = sMonth + num;
    var eDay = sDay;
    while (eMonth > 12) {
        eYear++;
        eMonth -= 12;
    }

    var eDate = new Date(eYear, eMonth, eDay);

    while (eDate.getMonth() != eMonth) {
        eDay--;
        eDate = new Date(eYear, eMonth, eDay);
    }
    return eDate;
};

// 月份增减方法
function dateToDate(date) {
    var sDate = new Date();
    if (typeof date == 'object' && typeof new Date().getMonth == "function") {
        sDate = date;
    } else if (typeof date == "string") {
        var arr = date.split('-')
        if (arr.length == 3) {
            sDate = new Date(arr[0] + '-' + arr[1] + '-' + arr[2]);
        }
    }
    return sDate;
};

// 月份增减方法格式化
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds()
        // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
                : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};



//自定义弹框控件 使用layer 参考http://layer.layui.com/api.html
"use strict";
(function (win, $) {
    //returnObj：是否需要返回当前创建的modal对象；size：弹窗宽度；ftitle：副标题；subTitle：提交按钮名称；shadeClose：是否点击遮罩层关闭；isSubmit：是否显示提交按钮；content：弹窗主题内容，可以是字符串或dome对象，为null时必须设置url
    var dfop = { top: 100, returnObj: false, formName: "#form", size: 500, ftitle: "", subTitle: "保存",closeTitle:"取消", shadeClose: true, isSubmit: true, content: null };
    var obj = function (settings) {
        var thi = this;
        thi.config = $.extend({}, dfop, settings);
    };
    obj.pt = obj.prototype;
    obj.pt.initModal = function () {
        this.container = $("#layui-layer" + this.index);//modal-dialog modal-sm modal-size1100
        this.dialog = this.container.find(".modal-dialog");
        this.body = this.dialog.find(".modal-body");
        this.title = this.dialog.find(".modal-title");
        this.ftitle = this.dialog.find(".ftitle");
        this.message = this.dialog.find(".message");
        this.btnSub = this.dialog.find(".btn-submit");
        var thi = this;

        //确定
        this.btnSub.bind("click", function () {
            var o = sykModal.submit;
            if (thi.submit)
                o = thi.submit;//使用具体实体的submit
            if (typeof (o) == "function") {
                o();//只执行设置的方法
                return;
            }
            var data = o.data;
            if (typeof (data) == "function")
                data = data();//获取要提交的数据
            if (data) {
                thi.sending();
                $.ajax({
                    type: "POST",
                    url: o.url,
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    dataType: "json",
                    success: function(ret) {
                        thi.sended();
                        o.done(ret);
                    },
                    error: function(errMsg) {
                        layer.alert(JSON.stringify(errMsg));
                    }
                });
            }
        });
        if (!thi.config.isSubmit)
            this.btnSub.unbind("click").hide();//不提交
        this.dialog.find(".btn-close,.close").bind("click", function () {
            thi.close();
        });
        if (this.config.ftitle.length > 0)
            this.ftitle.show();
    };
    obj.pt.getHtml = function () {
        var o = this.config;
        var w = $(window).width();
        if (o.size >= w)
            o.size = w - 20;
        return $("#modalTmpl").tmpl({ title: o.title, ftitle: o.ftitle, subTitle: o.subTitle, closeTitle: o.closeTitle, size: o.size }).html();
    };
    obj.pt.exeOpen = function () {
        var option = this.config;
        var w = $(window).width();
        if (option.size >= w)
            option.size = w - 25;
        var thi = this;
        var h = $(window).height();

        if (h <= 780) {
            option.top = 10;
        }

        if (option.content == null) {
            syk.get(option.url, function (html) {
                thi.openShow(html, thi, option, h);
            });
        } else {
            thi.openShow(option.content, thi, option, h);
        }
        
        if (option.returnObj)
            return thi;//需要返回窗口对象
        else
            return void (0);
    };
    obj.pt.openShow = function (html, thi, option, h) {
        thi.index = layer.open({
            type: 1,
            title: false,
            closeBtn: false,
            shadeClose: option.shadeClose, //是否点击遮罩关闭
            skin: 'layer-modal',
            move: ".modal-title-text",
            moveType: 1,
            offset: option.top + "px",
            area: option.size + "px",
            content: thi.getHtml(),
            success: function () {
                setTimeout(function () {
                    thi.initModal();
                    if (typeof html === "string")
                        thi.body.html(html);
                    else
                        thi.body.append(html);
                    syk.initUI(thi.body);
                    if (thi.config.isSubmit) {
                        setTimeout(function () {
                            thi.body.find("input").keyup(function (e) {
                                if (e.keyCode === 13)
                                    thi.btnSub.click();
                                else 
                                    thi.message.hide();
                            });
                        }, 200);
                    }

                    if (h <= (thi.body.height() + option.top + 100)) {
                        // h = h - thi.body.height() - option.top - 40;
                        h = $(window).height() - option.top - 160;
                        if (h < 100)
                            h = 100;
                        thi.body.height(h);
                    }
                }, 50);
            }
        });
    };
    obj.pt.msg = function (mes) {
        this.message.html(mes).show();
    };
    obj.pt.close = function () {
        var index = this.index;
        sykModal.reset();
        layer.close(index);
    };
    obj.pt.sending = function () {
        this.btnSub.hide();
        this.message.html('<img src="/images/loading1.gif" title="loading" alt="loading"/>').show();
    };
    obj.pt.sended = function () {
        this.btnSub.show();
        this.message.html('').hide();
    };
    obj.pt.isError = function () {
        var errors = this.body.find(".error");
        for (var i = 0; i < errors.length; i++) {
            if (!$(errors[i]).is(":hidden"))
                return true;
        }
        return false;
    };

    win.sykModal = {
        reset: function () {
            sykModal.msg = null;
            sykModal.close = null;
            sykModal.sending = null;
            sykModal.sended = null;
            sykModal.isError = null;
        },
        open: function (config) {
            var ob = new obj(config);
            sykModal.msg = function (mess) {
                ob.msg(mess);
            };
            sykModal.close = function () {
                ob.close();
            };
            sykModal.sending = function () {
                ob.sending();
            };
            sykModal.sended = function () {
                ob.sended();
            };
            sykModal.isError = function () {
                return ob.isError();
            };
            sykModal.container = function () {
                return ob.container;
            };
            return ob.exeOpen();
        }
    };
})(window, jQuery);


var syk = {};
syk.parent = parent;
syk.t = {};//用于页面临时执行的方法，以便及时回收垃圾，及避免方法名冲突

//=========供父页面调用 start
var setParentWin = function (win) {
    //已废弃
};
//=========供父页面调用 end

(function ($) {
    //设置全选效果
    $(document).delegate('.check-all,.i-checks', 'ifClicked', function (event) {
        //return;
        var thi = $(this);
        var checked = thi.is(":checked");
        var ck = "check";
        if (checked)
            ck = "uncheck";
        if (thi.hasClass("check-all")) {
            thi.parents("thead").next().find(".i-checks").iCheck(ck); //全选
        } else {
            var tbody = thi.parents("tbody");
            if (tbody.length === 1) {
                var all = tbody.prev().find(".check-all");
                var ckCount = tbody.find(".i-checks").length;
                var ckeckedCount = tbody.find(".i-checks:checked").length;
                if (ckCount - ckeckedCount === 1 && !checked)
                    ck = "check";
                else
                    ck = "uncheck";
                all.iCheck(ck);
            }
        }
    }).on("keydown", function (e) {
        var win = window;
        var container = $("#pageContainer");
        if (container.length === 0) {
            win = syk.parent.window;//快捷键都调用父窗口
            container = win.$("#pageContainer");
        }

        if (e.keyCode === 27) {
            //ESC关闭窗口
            win.layer.closeAll();
        } else if (e.altKey && e.keyCode === 67) {
            //Alt+C 刷新当前页
            var obj = container.find(".active");
            var src = obj.attr("src");
            if (src)
                win.frames[obj.attr("name")].location.reload();
            else
                win.location.reload();
        } else if (e.altKey && e.keyCode === 88) {
            //Alt+X 关闭当前页
            syk.closeThis();
        }
    });

    window.DataGrid = function (options) {
        var dataGrid = options.dataGridTable;
        var o = {
            datatype: function (postdata) {
                postdata = mergeObject(jsonCondition, postdata);
                var width = 0;
                if (options.width == null) {
                    width = $('.jqGrid_wrapper').width();
                } else {
                    width = options.width;
                }
                dataGrid.setGridWidth(width);
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    url: options.url,
                    data: JSON.stringify(postdata),
                    dataType: 'json',
                    beforeSend: function () {
                        $('#spinner').show();
                        dataGrid.hide();
                    },
                    success: function (resp) {
                        dataGrid[0].addJSONData(resp);
                    },
                    error: function (errMsg) {
                        layer.alert(JSON.stringify(errMsg));
                    },
                    complete: function () {
                        $('#spinner').hide();
                        dataGrid.show();
                    }
                });
            },
            height: '100%',
            rowNum: '10',
            jsonReader: {
                repeatitems: false
            },
            colNames: options.colNames,
            colModel: options.colModel,
            viewrecords: true,
            multiselect: options.multiselect,
            hidegrid: false,
            autowidth: true,
            footerrow: options.footerrow,
            loadtext: "Loading....",
            rowList: [10, 20, 30],
            pager: options.pagerId,
            gridComplete: options.gridComplete
        };
        dataGrid.jqGrid(o);
    }
})(jQuery);

function mergeObject(src, dest) {
    var i;
    for (i in src) {
        dest[i] = src[i];
    }
    return dest;
}

syk.setSaleRedLine = function (week, trs, start) {	//设置预售表格 标识线		            	 	
    var red = start + 2;
    var blue = start + 5;
    if (week === "四") {
        red = start + 3;
        blue = start + 6;
    }
    trs.each(function (i, tr) {
        tr = $(tr);
        var tds = tr.find("td");
        tds.eq(red).addClass("sale-red");
        tds.eq(blue).addClass("sale-blue");
        var ths = tr.find("th");
        ths.eq(red).addClass("sale-red");
        ths.eq(blue).addClass("sale-blue");
    });
};

syk.formatTime = function (val) {
    var time = "";
    if (val != null && val != "") {
        time = val.substring(0, 19);
    }
    return time;
};
syk.formatDate = function (val) {
    var time = "";
    if (val != null && val != "") {
        time = val.substring(0, 10);
    }
    return time;
};
//获取时间的小时和分钟
syk.formatTimeHour = function (val) {
    var time = "";
    if (val != null && val != "") {
        time = val.substring(11, 16);
    }
    return time;
};
syk.initFixedChosen = function (resp, option) {
    var selector = option.selector;
    if (!option.width) {
        option.width = "100%";
        if (selector.hasClass("select-fixed")) {
            var width = selector.parent().css("margin-bottom", "31px").width();
            if (width > 100)
                option.width = width + "px";
            else
                option.width = "";
        }
    }

    var html = '<option value="">--请选择--</option>';
    var val = selector.attr("value");
    for (i in resp) {
        html = html + '<option value="' + resp[i].id + '" ' + (resp[i].id.toString() == val ? 'selected' : '') + '>' + resp[i].name + '</option>';
    }

    selector.html(html);
    selector.chosen({ inherit_select_classes: true, width: option.width });
    selector.trigger("chosen:updated");
};
syk.initUI = function (o) {
    //this.initA(o);
    syk.initFarms();
    syk.initSeeds();
    syk.initChecks();
    syk.initInputDates(o);
    syk.initProducts();
};
syk.get = function (url, func) {
    syk.ajax({ url: url, type: "GET" }, func);
};

syk.post = function (url, data, func) {
    syk.ajax({ url: url, data: data, type: "POST", contentType: "application/json; charset=utf-8" }, func);
};
syk.getSynchro = function (url, func) {
    syk.ajax({ url: url, type: "GET", async: false }, func);//执行同步请求
};

syk.ajax = function (o, done) {
    //o.url = syk.setUrl(o);
    if (o.url.length === 0)
        return;
    Pace.restart();
    Pace.track(function () {
        $.ajax(o).done(done).error(function (jqXHR, textStatus, errorThrown) {
            syk.alertSysError();
        });
    });
};
syk.onlyPost = function (url, data, func) {
    var o = { url: url, data: data, type: "POST", contentType: "application/json; charset=utf-8" };
    $.ajax(o).done(func).error(function (jqXHR, textStatus, errorThrown) {
        syk.alertSysError();
    });
};
syk.refresh = function () {
    location.reload();
};

syk.alertError = function (msg, func) {
    layer.alert(msg, { icon: 2 }, func);
};
syk.alertSysError = function () {
    layer.alert("发生系统错误，请与信息部联系", { icon: 2 }, null);
};
//初始化 复选、单项框
syk.initChecks = function (o) {
    var checks;
    var all;
    if (o) {
        checks = o.find('.i-checks');
        all = o.find(".check-all");
    } else {
        checks = $('.i-checks');
        all = $.find(".check-all");
    }
    checks.iCheck({ checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-green' });
    if (all && all.length > 0) {
        all = $(all);
        var thead = all.parents("thead").next();
        checks = thead.find(".i-checks");
        if (all.is(":checked"))
            checks.iCheck("check"); //全选
        else if (checks.length > 0 && checks.length == thead.find(".i-checks:checked").length)
            all.iCheck("check");
    }
};

syk.initTbPopover = function () {
    $(".sum-amount").each(function () {
        var element = $(this);
        element.popover({
            trigger: "manual",
            placement: "bottom",
            html: "true",
            container: "body",
            content: function () {
                var div = element.find("div");
                return div.html();
            }
        }).on("mouseenter", function () {
            var thi = this;
            $(this).popover("show");
            $(".popover").on("mouseleave", function () {
                $(thi).popover("hide");
            });
        }).on("mouseleave", function () {
            var thi = this;
            if (!$(".popover:hover").length) {
                $(thi).popover("hide");
            }
        });
    });
};

//获取iframe的标题
syk.getTitle = function () {
    if (!syk.title)
        syk.title = syk.parent.$("#pageContainer").find(".active").attr("title");
    return syk.title;
};

//使用父窗口打开modal 展示info信息
syk.openInfo = function (o, content, size) {
    setTimeout(function () {
        var title = o.find(".modal-title").text();
        content = $("#gbox_" + content);
        syk.parent.sykModal.open({ content: content.prop("outerHTML"), title: title, top: '10%', ftitle: '', isSubmit: false, closeTitle: "关闭", size: size });
    }, 100);
};

//打开父窗口表单
syk.openForm = function (o, size) {
    var title = o.find(".modal-title").text();
    var content = o.find(".modal-body").html();
    syk.parent.sykModal.open({ content: content, title: title, top: '10%', ftitle: '', size: size });
};

//执行当前 tab 跳转页面
syk.localGoTo = function (url, title, isWinTitle) {
    navPage(url, title, isWinTitle, true);
};

//导航新tab
function navPage(url, title, isWinTitle, isLocal) {
    if (isWinTitle == undefined || isWinTitle === true)
        title = title + syk.getTitle();
    var index = syk.parent.index;
    if (isLocal) { //使用当前tab 跳转
        index.thisIframe().attr("src", url).attr("title", title);
        index.thisLiA().text(title);
    } else {
        var winId = index.thisIframe().attr("id");//传递当前ifarme的id
        index.addTab(url, title, winId);
    }
    return false;
}

//关闭当前窗口
syk.closeThis = function () {
    syk.parent.$("#tabs-ul").find(".active").find("i").click();
};

//数组插入
syk.insertAt = function (data, index, obj) {
    data.splice(index, 0, obj);
};

//初始化 农场下拉列表
syk.initFarms = function () {
    var farms = $(".select-farm");
    if (farms.length > 0) {
        $.ajax({
            type: "GET",
            url: "/farms.json",
            async: true,
            success: function (resp) {
                farms.each(function (i, farm) {
                    syk.initFixedChosen(resp, { selector: $(farm) });

                });
            }
        });
    }
};
//初始化 种子下拉列表
syk.initSeeds = function () {
    var seeds = $(".select-seed");
    if (seeds.length > 0) {
        $.ajax({
            type: "GET",
            url: "/seeds.json",
            async: true,
            success: function (resp) {
                for (i in resp) {
                    resp[i].name = resp[i].seedName;
                }
                seeds.each(function (i, seed) {
                    syk.initFixedChosen(resp, { selector: $(seed) });
                });
            }
        });
    }
};

//初始化 品种下拉列表
syk.initProducts = function () {
    var s = $(".select-product");
    if (s.length > 0) {
        $.ajax({
            type: "GET",
            url: "/batch/products.json",
            async: true,
            success: function (resp) {
                s.each(function (i, o) {
                    syk.initFixedChosen(resp, { selector: $(o) });
                });
            }
        });
    }
};
//初始化 日期输入框
syk.initInputDates = function (o) {
    var form = null;
    if (o) {
        form = o.form;
        if (!form && o.find)
            form = o.find("form");//绑定modal 弹窗的form
    }
    $(".input-date:not(:read-only)").click(function () {
        var thi = $(this);
        window.WdatePicker({
            onpicked: function (dp) {
                var id = thi.attr("id");
                if (form)
                    form.data('bootstrapValidator').updateStatus(id, 'NOT_VALIDATED', null).validateField(id);
            }
        });
    }).attr("autoComplete", "off");

    $(".input-time:not(:read-only)").click(function () {
        var thi = $(this);
        window.WdatePicker({
            onpicked: function (dp) {
                var id = thi.attr("id");
                if (form)
                    form.data('bootstrapValidator').updateStatus(id, 'NOT_VALIDATED', null).validateField(id);
            },
            dateFmt: 'yyyy-MM-dd HH:mm'
        }).attr("autoComplete", "off");
    });
};

//初始化列表页
syk.initList = function (o) {
    syk.initUI(o);
    var searchForm = $('#searchForm');
    var listTable = $("#table_list_1");
    var search = function () {
        $(searchForm.serializeArray()).each(function () {
            if (this.name.startsWith('start') && this.value != "") {
                jsonCondition[this.name] = this.value + " 00:00:00:000";
            } else if (this.name.startsWith('end') > 0 && this.value != "") {
                jsonCondition[this.name] = this.value + " 23:59:59:000";
            } else {
                jsonCondition[this.name] = this.value;
            }
        });
        listTable.jqGrid("setGridParam", { search: true }).trigger("reloadGrid", [{ page: 1 }]);
    };
    $("#search").click(search);
    $("#search-reset").click(function () {
        syk.refresh();
    });
    window.saveRefresh = function () {//供新增编辑tab调用
        search();
    };
    searchForm.keydown(function (e) {
        if (e.keyCode === 13) {
            search();
            e.preventDefault();
        }
    });
    setTimeout(function () {
        searchForm.find("input[type='text']").eq(0).focus();
    }, 600);
    $(window).resize(function () {
        var width = $('.jqGrid_wrapper').width();
        listTable.setGridWidth(width);
    });
    $("#delete").click(function () {
        var rowIds = listTable.jqGrid('getGridParam', 'selarrrow');
        if (rowIds == null || rowIds == '') {
            layer.msg("请选择要删除的数据");
            return;
        }

        syk.confirm("确认要删除选择的数据吗？", function () {
            syk.post(o.deleteUrl + "?ids=" + rowIds, null, function (data) {
                if (data.result) {
                    layer.msg('删除成功');
                    listTable.trigger("reloadGrid");
                } else {
                    syk.alertError(data.msg);
                }
            });

        });
    });
};

// 初始化新增编辑页面
syk.initEdit = function (o) {
    syk.initUI(o);
    $(".btn-close").click(syk.closeThis);
    var form = o.form;
    if (o.validate) {
        o.validate = $.extend({},
        {
            message: 'This value is not valid',
            excluded: ':disabled',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            }
        }, o.validate);

        form.bootstrapValidator(o.validate);
    }

    var sub = $("#submit").click(function () {
        if (o.validate) {
            var bootstrapValidator = form.data('bootstrapValidator').validate();
            if (!bootstrapValidator.isValid()) {
                $(".has-error input[type='text']").eq(0).focus();
                return;
            }
        }
        if (o.customValidate && !o.customValidate())
            return;
        $.ajax({
            type: 'POST',
            url: o.url,
            data: JSON.stringify(form.serializeJson()),
            contentType: "application/json; charset=utf-8",
            beforeSend: function () {
                sub.hide();
                $("#cover").show();
            },
            success: function (data) {
                if (data.result) {
                    var parentWin = syk.parent.index.parentWin();
                    if (parentWin)
                        parentWin.saveRefresh();
                    syk.parent.layer.msg("保存成功");
                    $(".btn-close").click();//刷新列表页
                } else {
                    syk.alertError(data.msg);
                }
            },
            error: function (errMsg) {
                syk.alertSysError();
            },
            complete: function () {
                sub.show();
                $("#cover").hide();
            }
        });
    });
    if (o.done)
        o.done();
};

//初始化表格单元格编辑功能
syk.initCellEdit = function (o) {
    var tds = $(".cell-edit-td").click(function () {
        var thi = $(this);
        var thiTr = thi.parent();

        var spanNum = thi.find(".num");
        spanNum.hide();
        var num = spanNum.text().trim();
        if (num.indexOf("|") > -1) {
            var nums = num.split("|");
            num = nums[1];
            thi.attr("num-old", nums[0]);
        }

        thi.attr("num", num == "" ? "0" : num);//备份数量
        var text = thi.find("input[name='num']");
       
        if (text.length == 0) {
            var textHtm = '<input type="text" name="num" class="input-sm form-control cell-edit-text" value="' + num + '"/>';//添加编辑框
            thi.append(textHtm);
        } else {
            text.val(num).show();
        }

        var tdInput = thi.find("input");

        tdInput.select().click(function (e) {
            e.stopPropagation();
        }).blur(function () {
            var input = $(this);

            var value = input.val();
            if (o.validate(value)) {
                input.focus();
                return;
            }

            input.hide();
            spanNum.show();
            var numAttr = thi.attr("num");
            var oldNum = thi.attr("num-old");
            if (oldNum && oldNum.length > 0) {
                numAttr = numAttr + "|" + oldNum;
            }
            spanNum.text(numAttr == "0" ? "" : numAttr);//先还原成旧值
            if (value == "")
                value = "0";

            if (numAttr === value)
                return;
            //如果单元格内容发生变化
            spanNum.text(value == "0" ? "" : value);//更新单元格内容
            thi.attr("num", value);
            var id = thiTr.find("input[name='id']").val();

            if (o.submit)
                o.submit(id, value, thi);
        }).on("keydown", function (e) {
            if (e.keyCode === 13)
                $(this).blur();
        });
    });
    tds.each(function () {
        var thi = $(this);
        if (thi.text() === "0")
            thi.text("");
        if (o.rightClick) {
            thi.on("mousedown", function (e) {
                if (window.Event) {
                    if (e.which == 2 || e.which == 3) {
                        return false;
                    }
                } else if (event.button == 2 || event.button == 3) {
                    event.cancelBubble = true;
                    event.returnValue = false;
                    return false;
                }
                return false;
            }
            );
            thi.on("contextmenu", function () {
                event.cancelBubble = true;
                event.returnValue = false;

                $(".right-div").hide();
                var div = thi.find(".right-div");
                if (div.length === 0) {
                    $("#rightTmpl").tmpl().appendTo(thi);//添加右键菜单
                    var period = thi.attr("period");
                    if (period.length == 0 || period.indexOf("1") > -1)
                        thi.find("input[data-type='one']").attr("checked", "checked");
                    if (period.length == 0 || period.indexOf("2") > -1)
                        thi.find("input[data-type='two']").attr("checked", "checked");
                    if (period.length == 0 || period.indexOf("3") > -1)
                        thi.find("input[data-type='three']").attr("checked", "checked");
                    if (thi.attr("status") == "true")
                        thi.find("input[data-type='order']").attr("checked", "checked");
                    thi.find(".i-checks").on("ifChanged", function () { o.rightClick(thi, $(this)); });
                    syk.initChecks(thi);
                    div = thi.find(".right-div");
                }
                div.show();//.unbind("click").bind("click", function (){event.stopPropagation();});
                return false;
            });
            $(document).on("click", function () {
                $(".right-div").hide();
            });
        }
    });
};


//计划系统编辑备注
syk.initCellDesc = function (o) {
    o.tbody.find(".cell-description").click(function () {
        var thi = $(this);
        var p = syk.parent;

        p.sykModal.open({ url: "/pages/plan/remark?remark=" + thi.text(), title: "备注", top: '10%' });

        p.sykModal.submit = {
            url: o.url,
            data: function () {
                var value = p.$("#description").val();
                if (value.length === 0) {
                    p.layer.msg("请输入备注");
                    return null;
                }
                return o.data(thi, value);
            },
            done: function (ret) {
                if (!ret.result) {
                    p.layer.alert("保存失败：{" + ret.msg + "}");
                } else {
                    var value = p.$("#description").val();
                    thi.text(value).attr("title", value);
                    p.sykModal.close();
                }
            }
        };
    });
};

//删除数据
syk.deleteDatas = function (o) {
    var checks = $(o.container + " .i-checks:checked");
    if (checks.length === 0) {
        layer.msg("请勾选要删除的数据");
        return;
    }
    var ids = "";
    checks.each(function (k, c) {
        ids += $(c).attr("data-id") + ",";
    });
    syk.confirm("确认要删除选择的数据吗？", function () {
        syk.post(o.url + '&ids=' + ids.trimEnd(","), null, function (data) {
            if (data.result) {
                o.fun();
            } else {
                syk.alertError(data.msg);
            }
        });
    });
};

//关闭当前选项卡 并刷新列表页
syk.saveSuccRefresh = function (msg) {
    if (msg)
        syk.parent.layer.msg(msg);
    var parentWin = syk.parent.index.parentWin();
    if (parentWin) {
        parentWin.saveRefresh();
    }
    setTimeout(function () { syk.closeThis(); }, 600);
};

syk.confirm = function (title, fun) {
    var index = syk.parent.layer.confirm(title, function () {
        syk.parent.layer.close(index);//调用父窗口弹出
        fun();
    });
};
//去掉小数部分多余的0
syk.clearZero = function (str, isEmpty) {
    if (str)
        str = str.toString().replace(".00", "").replace(".0", "");
    else
        str = "";
    if (isEmpty && str === "0")
        str = "";
    return str;
};

//将表单序列化为json
$.fn.serializeJson = function () {
    var serializeObj = {};
    var array = this.serializeArray();

    //var str = this.serialize();
    $(array).each(function () {
        var input = $("#" + this.name);
        if (input.hasClass("input-date"))
            this.value = this.value + " 00:00:00:000";
        else if (input.hasClass("input-time"))
            this.value = this.value + ":00:000";
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });

    return serializeObj;
};

//执行表单检查
$.fn.btValidate = function () {
    var bv = this.data('bootstrapValidator');
    if (bv) { // 修复记忆的组件不验证
        bv.validate();
        if (!bv.isValid()) {
            layer.msg("验证未通过，请检查");
            return true;
        }
    }
    return false;
};

//初始化表单验证
$.fn.initBtValidate = function (fields) {
    var o = {
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: null
    };
    var items = this.find(".form-group");
    var isAllMust = true;
    items.each(function (i, item) {
        var lable = $(item).find("label:first-child");
        if (lable.length > 0 && lable.text().startWith("*")) {
            isAllMust = false;//如果没有*开头的栏位，那么就是全部不能为空
            return false;
        }
        return true;
    });

    if (!fields)
        fields = {};//可从外部传入验证规则
    items.each(function (i, item) {
        var lable = $(item).find("label:first-child");
        if (isAllMust || (lable.length > 0 && lable.text().startWith("*"))) {
            var next = lable.next().find(":first-child");//找到输入项
            var name = next.attr("name");
            if (!fields[name]) {//避免覆盖外部传入的规则
                fields[name] = {
                    validators: {
                        notEmpty: {
                            message: '不能为空'
                        }
                    }
                };
            }
        }
    });

    o.fields = fields;
    this.bootstrapValidator(o);
};

//对字符串 做hash编码
syk.hashCode = function (str) {
    var hash = 0;
    if (str.length === 0) return hash;
    for (i = 0; i < str.length; i++) {
        var char = str.charCodeAt(i);
        hash = ((hash << 5) - hash) + char;
        hash = hash & hash;
    }
    return hash;
};

// 初始化 锁定表头 或 列
syk.initFixTable = function (tab) {
    var init = function (o) {
        var tableId = o.id;// 要锁定的Table的ID
        var fixColumnNumber = o.column;// 要锁定列的个数
        var width = o.width;// 显示的宽度
        var height = o.height;// 显示的高度
        if (!o.maxWidth)
            o.maxWidth = 900;
        var table = $("#" + tableId);
        table.css({ width: width < o.maxWidth ? o.maxWidth : width, height: height });
        var layoutId = tableId + "_tableLayout";
        var layout = $("#" + layoutId);

        var head, column, fix;
        if (layout.length !== 0) {
            layout.before(table);
            layout.empty();
        } else {
            table.after("<div id='" + layoutId + "' style='overflow:hidden;height:" + height + "px; width:" + width + "px;'></div>");
            layout = $("#" + layoutId);
        }
        $('<div id="' + tableId + '_tableFix"></div>'
           + '<div id="' + tableId + '_tableHead"></div>'
           + '<div id="' + tableId + '_tableColumn"></div>'
           + '<div id="' + tableId + '_tableData"></div>').appendTo("#" + layoutId);

        var tableFixClone = table.clone(true);
        tableFixClone.attr("id", tableId + "_tableFixClone");
        fix = $("#" + tableId + "_tableFix");
        fix.append(tableFixClone);
        var tableHeadClone = table.clone(true);
        tableHeadClone.attr("id", tableId + "_tableHeadClone");
        head = $("#" + tableId + "_tableHead");
        head.append(tableHeadClone);
        var tableColumnClone = table.clone(true);
        tableColumnClone.attr("id", tableId + "_tableColumnClone");
        column = $("#" + tableId + "_tableColumn");
        column.append(tableColumnClone);
        var tableData = $("#" + tableId + "_tableData");
        tableData.append(table);
        layout.find("table").each(function () {
            $(this).css("margin", "0");
        });
        var headHeight = $("#" + tableId + "_tableHead thead").height();
        headHeight += 2;
        head.css("height", headHeight);
        fix.css("height", headHeight);
        var columnsWidth = 0;
        var columnsNumber = 0;
        $("#" + tableId + "_tableColumn tr:last td:lt(" + fixColumnNumber + ")").each(function () {
            columnsWidth += $(this).outerWidth(true);
            columnsNumber++;
        });
        columnsWidth += 2;
        if (false && $.browser.msie) {
            switch ($.browser.version) {
                case "7.0":
                    if (columnsNumber >= 3) columnsWidth--;
                    break;
                case "8.0":
                    if (columnsNumber >= 2) columnsWidth--;
                    break;
            }
        }
        column.css("width", columnsWidth);
        fix.css("width", columnsWidth);
        tableData.scroll(function () {
            head.scrollLeft(tableData.scrollLeft());
            column.scrollTop(tableData.scrollTop());
        });
        fix.css({ "overflow": "hidden", "position": "relative", "z-index": "50", "background-color": "#fff" });
        head.css({ "overflow": "hidden", "width": width - 17, "position": "relative", "z-index": "45", "background-color": "#fff" });
        column.css({ "overflow": "hidden", "height": height - 17, "position": "relative", "z-index": "40", "background-color": "#fff" });
        tableData.css({ "overflow": "scroll", "width": width, "height": height, "position": "relative", "z-index": "35" });
        if (width > o.maxWidth)
            tableData.css({ "overflow-y": "scroll", "overflow-x": "hidden" });
        var fixTable = $("#" + tableId + "_tableFix table");
        var spanNum = 17;
        if (head.width() > fixTable.width()) {
            head.css("width", fixTable.width());
            tableData.css("width", fixTable.width() + spanNum);
        }
        var columnTable = $("#" + tableId + "_tableColumn table");
        if (column.height() > columnTable.height()) {
            column.css("height", columnTable.height());
            tableData.css("height", columnTable.height() + spanNum);
        }
        var offset = layout.offset();
        fix.offset(offset);
        head.offset(offset);
        column.offset(offset);
        tableData.offset(offset);
    };

    if (typeof tab === "string")
        tab = $("#" + tab);
    var id = tab.attr("id");
    if (!id) {
        id = "tab-fix-" + new Date().getMilliseconds();
        tab.attr("id", id);
    }

    var ini = function () {
        var width = $(".wrapper-content").eq(0).width() - 60;
        var height = $(window).height() - 220;

        //layer.msg(width +"  --  "+ height);
        if ($("#" + id).height() > 400)
            init({ id: id, column: 2, width: width, height: height });
    };

    var resizeTimer = null;
    $(window).resize(function () {
        resizeTimer = resizeTimer ? null : setTimeout(ini, 0);
    });

    return ini;
};

//初始化 菜品表头搜索
syk.initHeadSearch = function () {
    $(document).delegate('.head-search', 'keydown', function (e) {
        if (e.keyCode !== 13)
            return;
        var thi = $(this);
        var theadTr = thi.parent().parent();
        var trs = theadTr.parent().next().find("tr");
        var index = theadTr.find("th").index(thi.parent());

        trs.each(function (i, o) {
            var tr = $(o);
            var text = tr.find("td").eq(index).text();
            tr.removeClass("hide");
            if (thi.val().length > 0 && !text.startWith(thi.val()))
                tr.addClass("hide");
        });
    });
};

// =========字符串补充方法 start
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}
String.prototype.trimEnd = function (s) {
    var reg = new RegExp("(" + s + "*$)", "gi");
    return this.replace(reg, "");
}
String.prototype.endWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substring(this.length - str.length) == str)
        return true;
    else
        return false;
}
String.prototype.startWith = function (str) {
    if (str == null || str == "" || this.length == 0 || str.length > this.length)
        return false;
    if (this.substr(0, str.length) == str)
        return true;
    else
        return false;
}
String.prototype.padLeft = function (len, charStr) {
    var s = this + '';
    return new Array(len - s.length + 1).join(charStr || '') + s;
};
String.prototype.padRight = function (len, charStr) {
    var s = this + '';
    return s + new Array(len - s.length + 1).join(charStr || '');
};
// =========字符串补充方法 end

//移动终端浏览器版本信息
syk.browser = {
    versions: function () {
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/),// || !!u.match(/AppleWebKit/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
        };
    }(),
    language: (navigator.browserLanguage || navigator.language).toLowerCase() //语言版本
};
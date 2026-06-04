define(function(require, exports, module) {
    // 通用工具模块
    var Utils = require("global/utils.js");

    // 侧边栏菜单列表接口
    srvMap.add("getSidebarMenuList", "global/getSidebarMenuList.json", "global/menu");

    // 模板对象
    var Tpl = {
        sidebar: require('tpl/global/sidebar.tpl')
    };

    // 容器对象
    var Dom = {
        sidebar: '#JS_sidebar',
        menuList: '#JS_menuList',
        mainTabs: '#JS_mainTabs',
        mainTabsContent: '#JS_mainTabsContent',
        indexPageLoader: '#JS_indexPageLoader',
        searchbtn: '#search-btn'
    };

    var Data = {
        sidebar: []
    }

    // 渲染对象
    var Query = {
        init: function() {
            this._render();
        },
        _render: function() {
            var self = this;
            // 请求：侧边栏菜单列表接口
            Rose.ajax.getJson(srvMap.get('getSidebarMenuList'), '', function(json, status) {
                if (status) {
                    var template = Handlebars.compile(Tpl.sidebar);
                    $(Dom.sidebar).html(template(json));
                    Data.sidebar = json.data;
                    var height = document.body.clientHeight - $(".main-header").height();
                    //$("body").addClass('sidebar-collapse');
                    $(Dom.indexPageLoader).remove();

                    Utils.setScroll($(Dom.sidebar).children(".sidebar"), height);

                    self.bindMenuClickEvent();
                    self.initHomePage();
                    self.menuSearch();
                }
            });
        },
        getSidebarList: function() {
            return Data.sidebar;
        },
        getSidebarInfo: function(id) {
            // something todo
        },
        creatTab: function(objData) {
            var self = this;
            var timeStamp = '';
            var objId = '#JS_childTab_' + objData.id;
            if ($(objId).length > 0) {
                $("a[href='" + objId + "']").click();
                $('#JS_childTab_' + objData.id).data("cmd", objData.cmd); // 页面重新加载时，传入新参数
                Rose.ajax.loadHtml($('#JS_childTab_' + objData.id), objData.href + timeStamp);
            } else {
                // 如果是邮件页面，当大于8个页签的时候，关闭第二个页签
                if (objData.id == Static.getConstantID("email")) {
                    if ($(Dom.mainTabs).children("li").length == 8) {
                        self.delTab($(Dom.mainTabs).find("a").eq(1).data("id"));
                    }
                }
                // 如果是性能测试用例新增或修改页面，当大于8个页签的时候，关闭第二个页签
                if (objData.id == Static.getConstantID("perTestCase")) {
                    if ($(Dom.mainTabs).children("li").length == 8) {
                        self.delTab($(Dom.mainTabs).find("a").eq(1).data("id"));
                    }
                }

                if ($(Dom.mainTabs).children("li").length < 8) {
                    var _delDom = '';
                    if (objData.name != "首页") {
                        _delDom = '<i class="fa fa-remove"></i>';
                    }
                    var _dataCmd = objData.cmd || '';
                    $(Dom.mainTabs).children('li.active').removeClass('active').end().append('<li class="active">' +
                        '<a href="#JS_childTab_' + objData.id + '" data-toggle="tab" title="' + objData.name + '" data-id="' + objData.id + '">' +
                        objData.name + _delDom +
                        '</a>' +
                        '</li>');
                    var _loader = '<div class="text-loader min-height-page">' +
                        '<div class="text-loader-content">' +
                        '<i class="fa-home"></i>' +
                        '</div>' +
                        '</div>';
                    var _content = '<div class="tab-pane active" id="JS_childTab_' + objData.id + '" data-funid ="' + objData.id + '" data-cmd ="' + _dataCmd + '">' + _loader + '</div>';
                    $(Dom.mainTabsContent).children('div.active').removeClass('active').end().append(_content);
                    Rose.ajax.loadHtml($('#JS_childTab_' + objData.id), objData.href + timeStamp);

                    var _length = $(Dom.mainTabs).children("li").length;
                    if (_length >= 4) {
                        var _width = 100 / (_length) + "%";
                        $(".main-tab>.nav-tabs").find("li").css('width', _width);
                    } else {
                        var _width = "25%";
                        $(".main-tab>.nav-tabs").find("li").css('width', _width);
                    }
                    // 绑定删除事件
                    self.bindTabDelEvent();
                } else {
                    window.XMS.msgbox.show('页签最多只能打开8个！', 'error', 3000);
                }
            }

        },

        menuSearch: function() {
            var _form = $("#JS_sidebarMenuSearch");
            $(Dom.searchbtn).unbind();
            _form.find("[name='q']").unbind();
            _form.find("[name='q']").bind('change', function() {
                var menuName = _form.find("[name='q']").val();
                $(Dom.menuList).find("span").each(function(index, el) {
                    if ($(el).html() == menuName) {
                        $(el).parent().click();
                        $("li.treeview").removeClass('active');
                        $("ul.treeview-menu").removeClass('menu-open').css("display", "none");
                        $(el).parents("li.treeview").addClass('active');
                        $(el).parents("ul.treeview-menu").addClass("menu-open").css("display", "block");
                    }
                });
            });
            $(Dom.searchbtn).bind('click', function() {
                var menuName = _form.find("[name='q']").val();
                $(Dom.menuList).find("span").each(function(index, el) {
                    if ($(el).html() == menuName) {
                        $(el).parent().click();
                        $("li.treeview").removeClass('active');
                        $("ul.treeview-menu").removeClass('menu-open').css("display", "none");
                        $(el).parents("li.treeview").addClass('active');
                        $(el).parents("ul.treeview-menu").addClass("menu-open").css("display", "block");
                    }
                });
            });
        },
        delTab: function(id) {
            var objContent = '#JS_childTab_' + id;
            var $li = $("a[href='" + objContent + "']").parent("li");

            // 当期为active，删除前显示前一个节点
            if ($li.hasClass("active")) {
                if ($li.next().length > 0) {
                    $li.next().find("a").click();
                } else {
                    $li.prev().find("a").click();
                }
            }
            // 删除节点
            $li.remove();
            $(objContent).remove();
            var _length = $(Dom.mainTabs).children("li").length;
            if (_length >= 4) {
                var _width = 100 / (_length) + "%";
                $(".main-tab>.nav-tabs").find("li").css('width', _width);
            }

        },
        // 绑定点击事件
        bindMenuClickEvent: function() {
            var self = this;

            function CheckUrl(str) {
                var strRegex = '^((https|http|ftp|rtsp|mms)?://)';
                var RegUrl = new RegExp();
                RegUrl.compile(strRegex);
                if (!RegUrl.test(str)) {
                    return false;
                }
                return true;
            }
            $(Dom.menuList).find("a").bind('click', function() {
                var objData = {
                    id: $(this).data('id'),
                    name: $.trim($(this).text()),
                    href: $(this).data('href'),
                    cmd: $(this).data('cmd')
                }
                if (CheckUrl(objData.href)) {
                    self.creatIFrame(objData);
                } else {
                    if (objData.href.indexOf('.html') >= 0) {
                        self.creatTab(objData);
                    }
                }
            });
        },
        creatIFrame: function(objData) {
            var self = this;
            var timeStamp = '';
            var objId = '#JS_childTab_' + objData.id;

            var objId = '#JS_childTab_' + objData.id;
            if ($(objId).length > 0) {
                $("a[href='" + objId + "']").click();
                $('#JS_childTab_' + objData.id).data("cmd", objData.cmd); // 页面重新加载时，传入新参数

            } else {
                if ($(Dom.mainTabs).children("li").length < 8) {
                    var _delDom = '';
                    if (objData.name != "首页") {
                        _delDom = '<i class="fa fa-remove"></i>';
                    }
                    var _dataCmd = objData.cmd || '';
                    $(Dom.mainTabs).children('li.active').removeClass('active').end().append('<li class="active">' +
                        '<a href="#JS_childTab_' + objData.id + '" data-toggle="tab" title="' + objData.name + '" data-id="' + objData.id + '">' +
                        objData.name + _delDom +
                        '</a>' +
                        '</li>');
                    var _loader = '<div class="text-loader min-height-page">' +
                        '<div class="text-loader-content">' +
                        '<i class="fa-home"></i>' +
                        '</div>' +
                        '</div>';
                    var _content = '<div class="tab-pane active" id="JS_childTab_' + objData.id + '" data-funid ="' + objData.id + '" data-cmd ="' + _dataCmd + '">' + _loader + '</div>';
                    iframeWidth = $(Dom.mainTabsContent).children('div.active').width()
                    iframeHeight = 1500
                    $(Dom.mainTabsContent).children('div.active').removeClass('active').end().append(_content);

                    var _iframe = '<iframe src="' + objData.href + '" width="' + iframeWidth + '" height="' + iframeHeight + '" frameborder="no" border="0" marginwidth="0" marginheight="0"></iframe>';

                    console.log(123)
                    $(Dom.mainTabsContent).children('div.active').html(_iframe)
$(Dom.mainTabsContent).children('div.active').find("iframe").height(5000)
// setIframeHeight($(Dom.mainTabsContent).children('div.active').find("iframe")[0])

                    function setIframeHeight(iframe) {
                        if (iframe) {
                            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
                            if (iframeWin.document.body) {
                                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
                            }
                        }
                    };

                    var _length = $(Dom.mainTabs).children("li").length;
                    if (_length >= 4) {
                        var _width = 100 / (_length) + "%";
                        $(".main-tab>.nav-tabs").find("li").css('width', _width);
                    } else {
                        var _width = "25%";
                        $(".main-tab>.nav-tabs").find("li").css('width', _width);
                    }
                    // 绑定删除事件
                    self.bindTabDelEvent();
                } else {
                    window.XMS.msgbox.show('页签最多只能打开8个！', 'error', 3000);
                }
            }


        },
        bindTabDelEvent: function(monitoringTest, realTimetimer) {
            var self = this;
            var $removeObj = $(Dom.mainTabs).find(".fa-remove");
            $removeObj.unbind();
            $removeObj.bind("click", function() {
                if (monitoringTest) {
                    clearInterval(realTimetimer);
                    var _id = $(this).parent().data("id");
                    self.delTab(_id);
                    return realTimetimer = null;
                } else {
                    var _id = $(this).parent().data("id");
                    self.delTab(_id);
                }
            })
        },
        initHomePage: function() {
            $(Dom.sidebar).find("li:first > a").click();
            //          if(data && data.length > 0){
            //              var _thisData = data[0];
            //              var _href = _thisData.viewname;
            //              if(_href){
            //                  this.loadHtml(_href);
            //              }else{
            //                  this.initLoadPage(_thisData.subMenus);
            //              }
            //          }
            /*var firstLi = ulHtml.children("li:first");
            var a = firstLi.children("a:first");
            if(a.length > 0){
                a.click();
                var _href = a.data('href');
                if(!_href){
                    var nextUl = firstLi.children("ul:first");
                    this.initLoadPage(nextUl);
                }
            }*/
        }
    };
    Query.init();
    // 暴露渲染对象
    module.exports = Query;
});
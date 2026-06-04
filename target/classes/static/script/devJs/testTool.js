define(function (require, exports, module) {

    var Utils = require("global/utils.js");
    //var Sidebar = require('global/sidebar.js');
    var Page = Utils.initPage('csfTestTool');
    var Data = {

    };
     //执行
    srvMap.add("runCsfTestTool", "testTool/retMessage.json", "dev/csfTestTool");
   
    var Tpl = {
    };

    // 容器对象
    var Mod = {

    };



    var Init = {
        init: function () {
            this.selectRun();
        },

        selectRun: function () {
            var self = this;
            var _form = Page.findId('csfTestToolForm');
            var _run = _form.find("[name='run']");
            _run.unbind();
            _run.bind('click', function () {
                
            	_form.find('input').each(function(){
                    var spacesBehindValue=$.trim($(this).val());
                    $(this).val(spacesBehindValue);
                    });
                    var cmd = _form.serialize();
                
                Rose.ajax.postJson(srvMap.get('runCsfTestTool'), cmd, function (json, status) {

                    if (status) {
                        window.XMS.msgbox.hide();
                        var p = document.getElementById('csfResult');
                        p.value = json.data; 
                    }

                });

            });

        }
    };
    module.exports = Init;
});
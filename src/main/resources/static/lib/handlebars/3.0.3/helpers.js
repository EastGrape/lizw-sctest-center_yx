/* Handlebars Helpers - Dan Harper (http://github.com/danharper) */

/*
 * 根据状态码获取员工的状态值
 */
Handlebars.registerHelper('getUserState', function(value, fn) {
    if (value == "1") {
        return "有效";
    }
    if (value == "0") {
        return "失效";
    }
});
Handlebars.registerHelper('getYorN', function(value, fn) {
    if (value == "Y") {
        return "是";
    }
    if (value == "N") {
        return "否";
    }
});
/*
 * 根据状态码获取任务执行结果
 */
Handlebars.registerHelper('getResultType', function(value, fn) {
    if (value == "0") {
        return "成功";
    }
    if (value == "1") {
        return "失败";
    }
    if (value == "2") {
        return "未执行";
    }
    if (value == "3") {
        return "中断";
    }
});
/*
 * 根据状态码获取任务执行状态
 */
Handlebars.registerHelper('getTaskType', function(value, fn) {
    if (value == "1") {
        return "未执行";
    }
    if (value == "2") {
        return "执行中";
    }
    if (value == "3") {
        return "执行完成";
    }
    if (value == "4") {
        return "执行失败";
    }
    if (value == "5") {
        return "等待执行";
    }
});

/*
 * 根据是否成功让input框变化
 */
Handlebars.registerHelper('checkedState', function(value, fn) {
    if (value == "Y") {
        return "class=hide";
    }
    if (value == "N") {
        return "";
    }
});
Handlebars.registerHelper('remainDaysBla', function(value, days, fn) {
    if (value == "true") {
        if (days != null && days != undefined && days != "") {
            return "还剩";
        } else {
            return "";
        }

    }
});

Handlebars.registerHelper('remainDaysRed', function(value, days, fn) {
    if (value == "true") {
        if (days != null && days != undefined && days != "") {
            return days;
        } else {
            return "";
        }

    }
});
Handlebars.registerHelper('remainDaysStop', function(value, days, fn) {
    if (value == "true") {

    } else {
        return "欠费停机";
    }
});

Handlebars.registerHelper('remainDaysBla1', function(value, days, fn) {
    if (value == "true") {
        if (days != null && days != undefined && days != "") {
            return "天到期";
        } else {
            return "";
        }

    }
});

/**
 * If Equals if_eq this compare=that
 */
Handlebars.registerHelper('if_eq', function(context, options) {
    if (context == options.hash.compare)
        return options.fn(this);
    return options.inverse(this);
});

/**
 * Unless Equals unless_eq this compare=that
 */
Handlebars.registerHelper('unless_eq', function(context, options) {
    if (context == options.hash.compare)
        return options.inverse(this);
    return options.fn(this);
});

/**
 * If Greater Than if_gt this compare=that
 */
Handlebars.registerHelper('if_gt', function(context, options) {
    if (context > options.hash.compare)
        return options.fn(this);
    return options.inverse(this);
});

/**
 * Unless Greater Than unless_gt this compare=that
 */
Handlebars.registerHelper('unless_gt', function(context, options) {
    if (context > options.hash.compare)
        return options.inverse(this);
    return options.fn(this);
});

/**
 * If Less Than if_lt this compare=that
 */
Handlebars.registerHelper('if_lt', function(context, options) {
    if (context < options.hash.compare)
        return options.fn(this);
    return options.inverse(this);
});

/**
 * Unless Less Than unless_lt this compare=that
 */
Handlebars.registerHelper('unless_lt', function(context, options) {
    if (context < options.hash.compare)
        return options.inverse(this);
    return options.fn(this);
});

/**
 * If Greater Than or Equal To if_gteq this compare=that
 */
Handlebars.registerHelper('if_gteq', function(context, options) {
    if (context >= options.hash.compare)
        return options.fn(this);
    return options.inverse(this);
});

/**
 * Unless Greater Than or Equal To unless_gteq this compare=that
 */
Handlebars.registerHelper('unless_gteq', function(context, options) {
    if (context >= options.hash.compare)
        return options.inverse(this);
    return options.fn(this);
});

/**
 * If Less Than or Equal To if_lteq this compare=that
 */
Handlebars.registerHelper('if_lteq', function(context, options) {
    if (context <= options.hash.compare)
        return options.fn(this);
    return options.inverse(this);
});

/**
 * Unless Less Than or Equal To unless_lteq this compare=that
 */
Handlebars.registerHelper('unless_lteq', function(context, options) {
    if (context <= options.hash.compare)
        return options.inverse(this);
    return options.fn(this);
});

/**
 * 带序号的循环 {{index}}为序号。 eg ： {{#each_with_index array}} {{index}}
 * {{/each_with_index}}
 */
Handlebars.registerHelper("each_with_index", function(array, fn) {
    var buffer = "";
    array = array ? array : [''];
    for (var i = 0, j = array.length; i < j; i++) {
        var item = array[i];

        // stick an index property onto the item, starting with 1, may make
        // configurable later
        item.index = i + 1;

        // show the inside of the block
        buffer += fn(item);
    }
    // return the finished buffer
    return buffer;
});

/**
 * Check current index is odd or even 判断当前行的奇偶
 */
Handlebars.registerHelper("isOdd", function(index, options) {
    var str = (index % 2 == 0) ? 'even' : 'odd';
    return new Handlebars.SafeString(str);
});

/**
 * 裁剪内容,对于过长的string,使用...来替代 参数：内容,保留长度
 */
Handlebars.registerHelper("shrink", function(content, length) {
    content = content ? content : '';
    if (content.length > length) {
        content = content.slice(0, length);
        content += '...';
    }
    return new Handlebars.SafeString(content);
});

/**
 * 裁剪内容,对于过长的string,使用...来替代 参数：内容,保留长度
 */
Handlebars.registerHelper("shrinkHtmlFilter", function(content, length) {
    content = content ? content : '';
    if (content.length > length) {
        content = content.slice(0, length);
        content += '...';
    }
    return new Handlebars.SafeString(Rose.htmlFilter(content, 3));
});

Handlebars.registerHelper("subStr", function(content, start, length) {
    content = content ? content : '';
    if (content.length > length && content.length > start) {
        content = content.slice(start, length);
    }
    return new Handlebars.SafeString(content);
});

Handlebars.registerHelper("ternary", function(content, value) {
    content = content ? content : value;
    return new Handlebars.SafeString(content);
});

/**
 * SafeString
 */
Handlebars.registerHelper('safeString', function(text, options) {
    text = text ? text : '';
    return new Handlebars.SafeString(text);
});

Handlebars.registerHelper('add', function(value, addition) {
    return value + addition;
});
Handlebars.registerHelper('subtract', function(value, substraction) {
    return value - substraction;
});
Handlebars.registerHelper('divide', function(value, divisor) {
    return value / divisor;
});
Handlebars.registerHelper('multiply', function(value, multiplier) {
    return value * multiplier;
});
Handlebars.registerHelper('floor', function(value) {
    return Math.floor(value);
});
Handlebars.registerHelper('ceil', function(value) {
    return Math.ceil(value);
});
Handlebars.registerHelper('round', function(value) {
    return Math.round(value);
});

/**
 * 保留几位小数 value：数值 decimalNu：保留的小数个数
 */
Handlebars.registerHelper('toFixed', function(value, decimalNum) {
    value = Number(value);
    decimalNum = decimalNum || 2;
    return value.toFixed(decimalNum);
});
/**
 * 计算执行成功率
 */
Handlebars.registerHelper("getSuccessRate", function(successCase, totalCase, fn) {
    var successRate = Math.round(parseInt(successCase) / parseInt(totalCase) * 10000) / 100.00 + '%';
    return successRate;
});
/**
 * 计算执行时间
 */
Handlebars.registerHelper("getSpendTime", function(spendTime, fn) {
    var _spendTime = Math.floor(spendTime / 1440) + "天" + Math.floor(spendTime / 60) + "小时" + (spendTime % 60) + "分";
    return _spendTime;
});

/**
 * 手工用例和自动化
 */
Handlebars.registerHelper("transformatParamTempType", function(value) {
    if (value == 1) {
        return "测试用例";
    } else if (value == 2) {
        return "自动化用例";
    }
});
/**
 * 用例的重要程度
 */
Handlebars.registerHelper("transformatImp", function(value) {
    if (value == 1) {
        return "一级用例";
    } else if (value == 2) {
        return "二级用例";
    } else if (value == 3) {
        return "三级用例";
    } else if (value == 4) {
        return "四级用例";
    }
});
/**
 * 用例类型
 */
Handlebars.registerHelper("transformatCaseType", function(value) {
    if (value == 1) {
        return "UI类";
    } else if (value == 2) {
        return "接口类";
    } else if (value == 3) {
        return "后台进程类";
    } else if (value == 11){
        return "APP类";
    }
});
/**
 * 接口类型
 */

Handlebars.registerHelper("transformatCbossType", function(value) {
    if (value == 1) {
        return "网状网报文";
    } else if (value == 2) {
        return "网状网soap报文";
    } else if (value == 3) {
        return "星状网报文";
    } else {
        return "其他";
    }
});
/**
 * 用例的环境类型
 */
Handlebars.registerHelper("transformatEnv", function(value) {
    if (value == 3) {
        return "生产环境";
    } else {
        return "测试环境";
    }
});
/**
 * 用例的状态
 */
Handlebars.registerHelper("transformatStatus", function(value) {
    if (value == 0) {
        return "可用";
    } else if (value == 1) {
        return "不可用";
    }
});
/**
 * 测试用例名称分割
 */
Handlebars.registerHelper('getModalName', function(value, fn) {
    var _testName = value;
    var ipos = _testName.indexOf("_")
    var _caseModalName = _testName.substring(0, ipos);
    return _caseModalName;
});
/**
 *
 */
Handlebars.registerHelper("stateTran", function(value) {
    if (value == 0) {
        return "备份中";
    } else if (value == 1) {
        return "初始化失败";
    } else if (value == 2) {
        return "成功";
    } else if (value == 3) {
        return "失败";
    } else if (value == 4) {
        return "删除中";
    } else if (value == 6) {
        return "删除失败";
    } else {
        return "异常";
    }

});
/**
 * 参数是否初始化
 */
Handlebars.registerHelper("getInitFlag", function(value) {
    if (value == 0) {
        return "否";
    } else if (value == 1) {
        return "是";
    }

});
/**
 * 任务的执行方式
 */
Handlebars.registerHelper("transformatRunType", function(value) {
    if (value == 1) {
        return "立即执行";
    } else if (value == 2) {
        return "定时执行一次";
    } else if (value == 3) {
        return "分布式执行";
    } else if (value == 4) {
        return "定时重复执行";
    }
});
/**
 * 任务是否轮循
 */
Handlebars.registerHelper("transformatCycleType", function(value) {
    if (value == 1) {
        return "不轮循";
    } else if (value == 2) {
        return "轮循";
    }
});
/**
 * 任务定时重复执行周期
 */
Handlebars.registerHelper("getTaskTimingCycle", function(value) {
    if (value == '') {
        return "";
    } else if (value == 0) {
        return "周日";
    } else if (value == 1) {
        return "周一";
    } else if (value == 2) {
        return "周二";
    } else if (value == 3) {
        return "周三";
    } else if (value == 4) {
        return "周四";
    } else if (value == 5) {
        return "周五";
    } else if (value == 6) {
        return "周六";
    } else if (value == 7) {
        return "每天";
    }
});
/**
 * 执行机器的状态
 */
Handlebars.registerHelper("runMachineStatus", function(value) {
    if (value == 1) {
        return "离线";
    } else if (value == 2) {
        return "空闲";
    } else if (value == 3) {
        return "占用";
    }
});
/**
 * 任务类型
 */
Handlebars.registerHelper("autoTaskTypes", function(value) {
    if (value == 1) {
        return "普通类";
    } else if (value == 2) {
        return "开通类";
    }
});
/**
 * 任务执行结果
 */
Handlebars.registerHelper("autoTaskResults", function(value) {
    if (value == 1) {
        return "未执行";
    } else if (value == 2) {
        return "执行中";
    } else if (value == 3) {
        return "执行完成";
    } else if (value == 4) {
        return "执行失败";
    } else if (value == 5) {
        return "等待执行";
    }
});
/**
 * 流程配置:任务状态
 */
Handlebars.registerHelper("taskConfigStatus", function(value) {
    if (value == 0) {
        return "未执行";
    } else if (value == 1) {
        return "执行中";
    } else if (value == 2) {
        return "执行成功";
    } else if (value == 3) {
        return "已删除";
    }else if (value == 4) {
        return "已中止";
    }else if (value == 5) {
        return "执行失败";
    }  
});
/**
 * 任务配置节点记录表:执行状态
 */
Handlebars.registerHelper("tansferExecStatus", function(value) {
    if (value == 0) {
        return "未完成";
    } else if (value == 1) {
        return "已完成";
    } 
});
/**
 * 任务配置记录表:调用状态
 */
Handlebars.registerHelper("tranRecordStatus", function(value) {
    if (value == 0) {
        return "未执行";
    } else if (value == 1) {
        return "执行中";
    } else if (value == 2) {
        return "执行成功";
    } else if (value == 3) {
        return "执行失败";
    } 
});
/**
 * 环境类别
 */
Handlebars.registerHelper("envTypes", function(value) {
    if (value == 1) {
        return "个人环境配置";
    } else if (value == 2) {
        return "公共环境配置";
    }
});
/**
 * 执行环境
 */
Handlebars.registerHelper("runEnvs", function(value) {
    if (value == 1) {
        return "验收环境";
    } else if (value == 2) {
        return "准发布环境";
    } else if (value == 3) {
        return "生产环境";
    }else if (value == 4) {
        return "测试环境";
    }
});
/**
 * 自动化执行结果
 */
Handlebars.registerHelper("transAutoResult", function(value) {
    if (value == "") {
        return "未执行";
    } else if (value == 1) {
        return "失败";
    } else if (value == 2) {
        return "未执行";
    } else if (value == 3) {
        return "中断";
    } else if (value == 0) {
        return "成功";
    }
});
/**
 * 资源计划类型
 */
Handlebars.registerHelper("transformatPlanType", function(value) {
    if (value == 1) {
        return "查询类资源";
    } else if (value == 2) {
        return "受理类资源";
    }
});
/**
 * 资源类型
 */
Handlebars.registerHelper("transformatSourceType", function(value) {
    if (value == 1) {
        return "手机号码";
    } else if (value == 2) {
        return "宽带号码";
    } else if (value == 3) {
        return "固话号码";
    };
});
/**
 * 后台验收任务类型
 */
Handlebars.registerHelper("transformatBackTaskType", function(value) {
    if (value == 3) {
        return "ESB手工用例";
    } else if (value == 4) {
        return "ESB自动化";
    } else if (value == 5) {
        return "CBOSS手工用例";
    } else if (value == 6) {
        return "CBOSS自动化";
    }
});
/**
 * 后台验收任务子任务类型
 */
Handlebars.registerHelper("transformatState", function(value) {
    if (value == 0) {
        return "未处理";
    } else if (value == 1) {
        return "处理中";
    } else if (value == 2) {
        return "处理完成";
    } else {
        return "未定义";
    }
});
/**
 * 后台功能验收子任务处理状态
 */
Handlebars.registerHelper("transformatCaseState", function(value) {
    if (value == 0) {
        return "未处理";
    } else if (value == 1) {
        return "处理完成";
    } else {
        return "未定义";
    }
});

/**
 * // 后台功能验收子任务重要程度
 */
Handlebars.registerHelper("transformatImprot", function(value) {
    if (value == 1) {
        return "一级用例";
    } else if (value == 2) {
        return "二级用例";
    } else if (value == 3) {
        return "三级用例";
    } else if (value == 4) {
        return "四级用例";
    } else {
        return "";
    }
});

// Handlebars.registerHelper("transformatIf", function(value) {
//     if (value == 0) {
//         return "否";
//     } else if (value == 1) {
//         return "是";
//     } else {
//         return " ";
//     }
// });

/**
 * // 后台验收任务测试报告计划状态
 */
Handlebars.registerHelper("transferPlanState", function(value) {
    if (value == 1) {
        return "新增";
    } else if (value == 2) {
        return "处理中";
    } else if (value == 3) {
        return "完成";
    } else if (value == 4) {
        return "取消";
    }
});
/**
 * // 后台验收任务测试报告处理状态
 */
Handlebars.registerHelper("transferDealState", function(value) {
    if (value == 1) {
        return "未处理";
    } else if (value == 2) {
        return "处理中";
    } else if (value == 3) {
        return "处理完成";
    }
});
Handlebars.registerHelper('getOnlineTaskType', function(value, fn) {
    if (value == "1") {
        return "手工用例";
    }
    if (value == "2") {
        return "自动化用例";
    }
    if (value == "0") {
        return "用例组";
    }
});

Handlebars.registerHelper("correlationTypeChange", function(value) {
    if (value == 1 || value == null) {
        return "普通模式";
    } else if (value == 2) {
        return "跨库关联模式";
    }
});
Handlebars.registerHelper("transAutoMark", function(value) {
    if (value == 0) {
        return "否";
    } else if (value == 1) {
        return "是";
    }
});
//变更计划管理****************************************************************
Handlebars.registerHelper("plan_state", function(value) {
    if (value == 1) {
        return "新建";
    } else if (value == 2) {
        return "处理中";
    } else if (value == 3) {
        return "完成";
    } else if (value == 4) {
        return "取消";
    }
});
Handlebars.registerHelper("changePlanType", function(value) {
    if (value == 1) {
        return "计划上线";
    } else if (value == 2) {
        return "紧急上线";
    } else if (value == 3) {
        return "计划变更";
    } else if (value == 4) {
        return "紧急变更";
    }
});
Handlebars.registerHelper("results", function(value) {
    if (value == 1) {
        return "通过";
    } else if (value == 2) {
        return "不通过";
    }
});
Handlebars.registerHelper("timelys", function(value) {
    if (value == 1) {
        return "是";
    } else if (value == 2) {
        return "否";
    }
});
Handlebars.registerHelper("fileTypes", function(value) {
    if (value == 0) {
        return "否";
    } else if (value == 20) {
        return "变更交付物";
    }
});
// Handlebars.registerHelper("getFileType", function(value) {
//     if (value == 1) {
//         return "上线系统模块清单";
//     } else if (value == 2) {
//         return "计划上线清单";
//     } else if (value == 3) {
//         return "测试遗留问题清单";
//     } else if (value == 4) {
//         return "测试情况";
//     } else if (value == 5) {
//         return "进程变更清单";
//     } else if (value == 6) {
//         return "服务变更上线清单";
//     } else if (value == 7) {
//         return "主机类配置";
//     } else if (value == 8) {
//         return "需联调需求";
//     } else if (value == 9) {
//         return "生产环境需配置菜单需求";
//     } else if (value == 10) {
//         return "集团需求";
//     }
// });
Handlebars.registerHelper("getFileType", function(value) {
    if (value == 100) {
        return "代码包清单";
    } else if (value == 101) {
        return "上线需求清单";
    } else if (value == 102) {
        return "测试遗留问题清单";
    } else if (value == 103) {
        return "测试情况";
    } else if (value == 104) {
        return "进程变更清单";
    } else if (value == 105) {
        return "服务变更上线清单";
    } else if (value == 106) {
        return "主机类配置";
    } else if (value == 107) {
        return "需联调需求";
    } else if (value == 108) {
        return "生产环境需配置菜单需求";
    } else if (value == 109) {
        return "集团需求";
    } else if (value == 110) {
        return "割接脚本清单";
    } else if (value == 111) {
        return "数据库信息";
    } else if (value == 112) {
        return "SQL清单信息";
    } else if (value == 113) {
        return "系统架构清单";
    } else if (value == 114) {
        return "非功能验收测试报告";
    } else if (value == 115) {
        return "功能验收测试报告";
    } else if (value == 117) {
        return "上线步骤和回归步骤";
    } else if (value == 119) {
        return "监控信息变更";
    }
});
//性能测试helper
Handlebars.registerHelper("getperformanceCaseType", function(value) {
    if (value == 1) {
        return "WebService接口";
    } else if (value == 2) {
        return "HTTP接口";
    } else if (value == 3) {
        return "WEB页面";
    } else if (value == 4) {
        return "CSF接口";
    }
});
Handlebars.registerHelper("getdefaultCaseEnvironment", function(value) {
    if (value == 1) {
        return "测试环境";
    } else if (value == 2) {
        return "准发布环境";
    } else if (value == 3) {
        return "生产环境";
    } else if (value == 4) {
        return "性能环境";
    }else if (value == 5) {
        return "验收环境"
    }
});
Handlebars.registerHelper("getInterfaceValid", function(value) {
    if (value == 0) {
        return "初始化,未进行测试";
    } else if (value == 1) {
        return "测试成功";
    } else if (value == 2) {
        return "测试异常";
    }
});
/**
 * 性能测试报告中测试结果渲染
 *
 */
Handlebars.registerHelper("transferTestResult", function(value) {
    if (value == 0) {
      return "测试成功";
    } else if (value > 0) {
      return "测试异常";
    } 
});
Handlebars.registerHelper("getContentType", function(value) {
    if (value == 1) {
        return "ESB";
    } else if (value == 2) {
        return "CBOSS";
    }
});
/**
 * 机器执行结果中用例状态
 *
 */
Handlebars.registerHelper("getMachineRunType", function(value) {
    if (value == 3) {
        return "执行完成";
    } else if (value == 1) {
        return "未执行";
    }
});
/**
 * [性能计划实例执行结果]
 * @param  {[type]} value) {               if (value [description]
 * @return {[type]}        [description]
 */
Handlebars.registerHelper("trsExecuteResult", function(value) {
    if (value == 0) {
        return "初始化";
    } else if (value == 1) {
        return "运行中";
    } else if (value == 2) {
        return "成功";
    } else if (value == 3) {
        return "失败";
    } else if (value == 4) {
        return "中止";
    }
});
/**
 * 非功能验收任务，子任务状态
 *
 */
Handlebars.registerHelper('getDistributeDealState', function(value, fn) {

    if (value == "1") {
        return "未处理";
    }
    if (value == "2") {
        return "处理中";
    }
    if (value == "3") {
        return "完成";
    }
    if (value == "4") {
        return "不需分派";
    }
});

Handlebars.registerHelper('getResultState', function(value, fn) {

    if(value=="0"){
       return "未处理";  
    }
    if (value == "1") {
        return "处理中";
    }
    if (value == "2") {
        return "处理完成";
    }
});


// 压力机
Handlebars.registerHelper("getStatus", function(value) {
    if (value == 0) {
        return "离线";
    } else if (value == 1) {
        return "在线";
    }
});

Handlebars.registerHelper("getOccupyStatus", function(value) {
    if (value == 0) {
        return "未占用";
    } else if (value == 1) {
        return "占用中";
    }
});
/**
 * [description]计划废弃状态
 * @param  {[type]} value) 0为可用，1为废弃]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transIsAbandoned", function(value) {
    if (value == 0) {
        return "否";
    } else if (value == 1) {
        return "是";
    }
});
/**
 * [description]性能测试查看报告-详细结果-测试结论
 * @param  {[type]} value) 0为不通过，1为通过]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transCompareResult", function(value) {
    if (value == 0) {
        return "不通过";
    } else if (value == 1) {
        return "通过";
    }
});
/**
 * [description]非功能任务用例-用例-测试结果
 * @param  {[type]} value) 0为不通过，1为通过]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("testPreResult", function(value) {
    if (value === "0") {
        return "不通过";
    } else if (value === "1") {
        return "通过";
    }
});
/**
 * [description]测试号码管理-属性名称
 * @param  {[type]} value) 1为常驻号码，2为测试号码]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transPropertyName", function(value) {
    if (value == 1) {
        return "常驻号码";
    } else if (value == 2) {
        return "测试号码";
    }
});
/**
 * [description]测试号码管理-属性类型
 * @param  {[type]} value) 1为GSM，2为E55，3为固话，4为宽带]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transPropertyType", function(value) {
    if (value == 1) {
        return "GSM";
    } else if (value == 2) {
        return "E55";
    } else if (value == 3) {
        return "固话";
    } else if (value == 4) {
        return "宽带";
    }
});
/**
 * [description]测试号码管理-使用方
 * @param  {[type]} value) 1为入网验收(新炬)，2为开发测试(亚信)，3为地市测试，4为集团考核，5为故障总控，6为投诉处理｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transUserNumber", function(value) {
    if (value == 1) {
        return "入网验收(新炬)";
    } else if (value == 2) {
        return "开发测试(亚信)";
    } else if (value == 3) {
        return "地市测试";
    } else if (value == 4) {
        return "集团考核";
    } else if (value == 5) {
        return "故障总控";
    } else if (value == 6) {
        return "投诉处理";
    }
});
/**
 * [description]实物资源管理-资源类型
 * @param  {[type]} value) resourceType]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transformatResourceType", function(value) {
    if (value == 1) {
        return "SIM卡资源";
    } else if (value == 2) {
        return "IC卡读写机";
    } else if (value == 3) {
        return "身份证阅读器";
    } else if (value == 4) {
        return "台式居民身份证阅读机";
    } else if (value == 5) {
        return "立思辰电子签名板";
    } else if (value == 6) {
        return "汉王签笔";
    } else if (value == 7) {
        return "iPhone";
    } else if (value == 8) {
        return "华为";
    } else if (value == 9) {
        return "三星";
    }
});
/**
 * [description]实物资源管理-文件类型
 * @param  {[type]} value) resourceType]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transResourceFileType", function(value) {
    if (value == 1) {
        return "设备驱动";
    } else if (value == 2) {
        return "操作文档";
    } else if (value == 3) {
        return "实物图片";
    }
});
/**
 * [description]实物资源管理-文件类型
 * @param  {[type]} value) resourceType]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getPictureInfo", function(value) {
    if (value) {
        return "查看图片";
    } else {
        return "";
    }
});
/**
 * [description]非功能指标--指标类型
 * @param  {[type]} value) resourceType]｝
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getTargetType", function(value) {
    if (value == 1) {
        return "性能";
    } else if (value == 2) {
        return "可靠性";
    } else if (value == 3) {
        return "可维护性";
    } else if (value == 4) {
        return "可监控性";
    } else if (value == 5) {
        return "安全性";
    }
});
/**
 * [description]非功能指标参数-指标类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("tansTargetType", function(value) {
    if (value == 1) {
        return "性能";
    } else if (value == 2) {
        return "可靠性";
    } else if (value == 3) {
        return "可维护性";
    } else if (value == 4) {
        return "可监控性";
    } else if (value == 5) {
        return "安全性";
    }
});
/**
 * [description]非功能指标参数-指标状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transTargetStatu", function(value) {
    if (value == 1) {
        return "待实现";
    } else if (value == 2) {
        return "失效";
    } else if (value == 3) {
        return "未完成";
    } else if (value == 4) {
        return "完成";
    }
});
/**
 * [description]非功能性能用例保存-文件类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transFileTypeForPer", function(value) {
    if (value == 1) {
        return "手工上传";
    } else if (value == 2) {
        return "文件上传";
    }
});
/**
 * [description]非功能性能用例参数保存-遍历类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("paramTraversalMethod", function(value) {
    if (value == 1) {
        return "顺序遍历";
    } else if (value == 2) {
        return "随机遍历";
    } else if (value == 3) {
        return "顺序循环遍历";
    }
});
/**
 * [description]非功能用例库-用例类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("tansNonfunctionCaseType", function(value) {
    if (value == 1) {
        return "UI类";
    } else if (value == 2) {
        return "接口类";
    } else if (value == 3) {
        return "后台进程类";
    }
});
/**
 * [description]非功能用例库-环境类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("tansNonfunctionEnvironmentType", function(value) {
    if (value == 1) {
        return "测试环境";
    } else if (value == 2) {
        return "生产环境";
    }
});
/**
 * [description]非功能用例库-重要等级
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("tansNonfunctionImportantGrade", function(value) {
    if (value == 1) {
        return "一级";
    } else if (value == 2) {
        return "二级";
    } else if (value == 3) {
        return "三级";
    } else if (value == 4) {
        return "四级";
    }
});
/**
 * [description]非功能用例库-用例类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("tansNonfunctionStatus", function(value) {
    if (value == 0) {
        return "不可用";
    } else if (value == 1) {
        return "可用";
    }
});
/**
 * [description]流程-缺陷管理-类别
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("typeDefect", function(value) {
    if (value == 1) {
        return "功能验收";
    } else if (value == 2) {
        return "非功能验收";
    }
});
/**
 * [description]流程-缺陷管理-缺陷类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("faultTypeDefect", function(value) {
    if (value == 1) {
        return "新增缺陷";
    } else if (value == 2) {
        return "历史缺陷";
    }
});
/**
 * [description]性能-基准值维护-基准项
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("referenceItemTransfer", function(value) {
    if (value == 11) {
        return "日常高峰调用量";
    }else if (value == 12) {
        return "压测高峰调用量";
    }else if (value == 1) {
        return "调用量";
    }else if (value == 2) {
        return "接口成功率";
    } else if (value == 3) {
        return "资源使用情况";
    }else if (value == 4) {
        return "容灾数";
    } else if (value == 5) {
        return "CBOSS失败量";
    }else if (value == 6) {
        return "CBOSS业务成功率";
    }else if (value == 7) {
        return "接口响应时长";
    }
});
/**
 * [description]功能-自动化执行任务-运行模式
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getTaskExecMode", function(value) {
    if (value == 0) {
        return "普通";
    } else if (value == 1) {
        return "高效";
    }
});
/**
 * [description]非功能-非功能指标参数-实现阶段
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getNonImplementStage", function(value) {
    if (value == 'DEV') {
        return "日常开发";
    } else if (value == 'QA') {
        return "日常测试";
    } else if (value == 'OPS') {
        return "日常上线后评估";
    } else if (value == 'P_DEV') {
        return "项目建设";
    } else if (value == 'P_QA') {
        return "项目测试";
    } else if (value == 'P_OPS') {
        return "项目上线后评估";
    } else if (value == 'ALL') {
        return "全流程";
    }
});
/**
 * [description]非功能-非功能指标参数-改造范围
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getNonRemouldScope", function(value) {
    if (value == 'HW_NET') {
        return "网络层硬件";
    } else if (value == 'HW_CSF') {
        return "csf层硬件";
    } else if (value == 'HW_ESB') {
        return "esb层硬件";
    } else if (value == 'HW_MQ') {
        return "消息总线硬件";
    } else if (value == 'HW_DCOS_APP') {
        return "dcos app层硬件";
    } else if (value == 'HW_DCOS_PROC') {
        return "dcos procedure层硬件";
    } else if (value == 'HW_DB') {
        return "db层硬件";
    } else if (value == 'SW_NET') {
        return "网络组软件";
    } else if (value == 'SW_CSF') {
        return "csf层软件";
    } else if (value == 'SW_ESB') {
        return "esb层软件";
    } else if (value == 'SW_MQ') {
        return "消息总线软件";
    } else if (value == 'SW_DCOS_APP') {
        return "dcos app层软件";
    } else if (value == 'SW_DCOS_PROC') {
        return "dcos procedure层软件";
    } else if (value == 'SW_DB') {
        return "db层软件";
    }
}); 



/**
 * [description]  改造范围  渲染
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
 Handlebars.registerHelper("getRemouldScope", function(value) {
    var info = "";
    if (value != null) {
        if (value.indexOf('HW_NET') >= 0) {
            info = info + "网络层硬件,";
        }
        if (value.indexOf("HW_CSF") >= 0) {
            info = info + "csf层硬件,";
        }
        if (value.indexOf("HW_OSB") >= 0) {
            info = info + "osb层硬件,";
        }
        if (value.indexOf("HW_ESB") >= 0) {
            info = info + "esb层硬件,";
        }
        if (value.indexOf("HW_MQ") >= 0) {
            info = info + "消息总线硬件,";
        }
        if (value.indexOf("HW_DCOS_APP") >= 0) {
            info = info + "dcos app层硬件,";
        }
        if (value.indexOf("HW_DCOS_PROC") >= 0) {
            info = info + "dcos procedure层硬件,";
        }
        if (value.indexOf("HW_DB") >= 0) {
            info = info + "db层硬件,";
        }
        if (value.indexOf("SW_NET") >= 0) {
            info = info + "网络层软件,";
        }
        if (value.indexOf("SW_CSF") >= 0) {
            info = info + "csf层软件,";
        }
        if (value.indexOf("SW_OSB") >= 0) {
            info = info + "osb层软件,";
        }
        if (value.indexOf("SW_ESB") >= 0) {
            info = info + "esb层软件,";
        }
        if (value.indexOf("SW_MQ") >= 0) {
            info = info + "消息总线软件,";
        }
        if (value.indexOf("SW_DCOS_APP") >= 0) {
            info = info + "dcos app层软件,";
        }
        if (value.indexOf("SW_DCOS_PROC") >= 0) {
            info = info + "dcos procedure层软件,";
        }
        if (value.indexOf("SW_DB") >= 0) {
            info = info + "db层软件,";
        }
        if (value.indexOf("HW_ORD_INTE") >= 0) {
            info = info + "交易流程集成层硬件,";
        }
        if (value.indexOf("SW_ORD_INTE") >= 0) {
            info = info + "交易流程集成层软件,";
        }
        if (value.indexOf("HW_HTTP") >= 0) {
            info = info + "http层硬件,";
        }
        if (value.indexOf("HW_DCOS_WEB") >= 0) {
            info = info + "dcos web层硬件,";
        }
        if (value.indexOf("HW_APP") >= 0) {
            info = info + "app层硬件,";
        }
        if (value.indexOf("HW_PROC") >= 0) {
            info = info + "procedure层硬件,";
        }
        if (value.indexOf("SW_HTTP") >= 0) {
            info = info + "http层软件,";
        }
        if (value.indexOf("SW_DCOS_WEB") >= 0) {
            info = info + "dcos web层软件,";
        }
        if (value.indexOf("SW_APP") >= 0) {
            info = info + "app层软件,";
        }
        if (value.indexOf("SW_PROC") >= 0) {
            info = info + "procedure层软件,";
        }
        if (value.indexOf("HW_CCS_WEB") >= 0) {
            info = info + "ccs web层硬件,";
        }
        if (value.indexOf("SW_CCS_WEB") >= 0) {
            info = info + "ccs web层软件,";
        }
        if (value.indexOf("HW_CCS_APP") >= 0) {
            info = info + "ccs app层硬件,";
        }
        if (value.indexOf("SW_CCS_APP") >= 0) {
            info = info + "ccs app层软件,";
        }
        if (value.indexOf("HW_CRMAPP") >= 0) {
            info = info + "crmapp层硬件,";
        }
        if (value.indexOf("SW_CRMAPP") >= 0) {
            info = info + "crmapp层软件,";
        }
        if (value.indexOf("HW_WEB") >= 0) {
            info = info + "web层硬件,";
        }
        if (value.indexOf("SW_WEB") >= 0) {
            info = info + "web层软件,";
        }
        if (value.indexOf("HW_EJB") >= 0) {
            info = info + "ejb层硬件,";
        }
        if (value.indexOf("SW_EJB") >= 0) {
            info = info + "ejb层软件,";
        }



        if (info != "") {
            info = info.substring(0, info.length - 1);
        }

        return info;
    }
});

 /**
 * [description]  每次发布出错责任方  渲染
 */
 //需求管理部、运行维护部、云计算中心、其他外部门、项目组、工具和平台、其他
Handlebars.registerHelper("transErrorResp", function(value) {
    var info = "";
    if (value != null) {
        if (value.indexOf('1') >= 0) {
            info = info + "需求管理部,";
        }
        if (value.indexOf('2') >= 0) {
            info = info + "运行维护部,";
        }
        if (value.indexOf('3') >= 0) {
            info = info + "云计算中心,";
        }
        if (value.indexOf('4') >= 0) {
            info = info + "其他外部门,";
        }
        if (value.indexOf('5') >= 0) {
            info = info + "项目组,";
        }
        if (value.indexOf('6') >= 0) {
            info = info + "工具和平台,";
        }
        if (value.indexOf('8') >= 0) {
            info = info + "开发管理部,";
        }
        if (value.indexOf('9') >= 0) {
            info = info + "系统规划部,";
        }
        if (value.indexOf('7') >= 0) {
            info = info + "其他,";
        }
        if (info != "") {
            info = info.substring(0, info.length - 1);
        }
    }
      return info;
});


/**
 * [description]  每次发布错误原因  渲染
 */
 //错误原因：代码缺陷、配置错误、网络异常、主机异常、进程异常、发布异常、其他
 Handlebars.registerHelper("transErrorReason", function(value) {
   var info = "";
    if (value != null) {
        if (value.indexOf('1') >= 0) {
            info = info + "代码缺陷,";
        }
        if (value.indexOf('2') >= 0) {
            info = info + "配置错误,";
        }
        if (value.indexOf('3') >= 0) {
            info = info + "网络异常,";
        }
        if (value.indexOf('4') >= 0) {
            info = info + "主机异常,";
        }
        if (value.indexOf('5') >= 0) {
            info = info + "进程异常,";
        }
        if (value.indexOf('6') >= 0) {
            info = info + "发布异常,";
        }
        if (value.indexOf('7') >= 0) {
            info = info + "其他,";
        }
        if (info != "") {
            info = info.substring(0, info.length - 1);
        }
    }
      return info;
});


/**
 * [description]非功能-非功能指标参数-改造范围
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getTargetProperty", function(value) {
    if (value == 1) {
        return "业务";
    } else if (value == 2) {
        return "应用";
    }else if (value ==3 ) {
        return "设备";
    }else if (value ==4 ) {
        return "其它";
    }
});
/**
 * [description]非功能-非功能指标参数-改造范围
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getPerStatus", function(value) {
    if (value == 0) {
        return "不可用";
    } else if (value == 2) {
        return "可用";
    }else if (value == 1) {
        return "调试中";
    }
});
/**
 * [description]非功能-非功能参数号码-参数状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getParameterState", function(value) {
    if (value == 0) {
        return "正常";
    } else if (value == 1) {
        return "停用";
    } else if (value == 2) {
        return "删除";
    }
});
/**
 * [description]非功能-非功能参数号码-处理状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getParameterHandleState", function(value) {
    if (value == "0") {
        return "";
    } else if (value == "1") {
        return "处理中";
    } else if (value == "2") {
        return "处理完成";
    }
});
/**
 * [description]非功能-非功能参数号码-处理状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getfileHandleState", function(value) {
    if (value == "0") {
        return "处理中";
    } else if (value == "1") {
        return "处理完成";
    }
});
/**
 * [description]性能-计划，用例界面-调试时间转变
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getStartTime", function(value) {
    return (new Date(value)).toLocaleDateString() + " " + (new Date(value)).toLocaleTimeString()
});
/**
 * [description]性能-计划，用例界面-调试时间转变
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getSuccess", function(value) {
    if (value == true) {
        return "成功";
    } else if (value == false) {
        return "失败";
    }
});

Handlebars.registerHelper("getError", function(value) {
    if (value == "true") {
        return "不通过";
    } else if (value == "false") {
        return "通过";
    }
});
/**
 * [description]性能-计划，方案界面-状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("excuteStatus", function(value) {
    if (value == "1") {
        return "执行中";
    } else if (value == "2") {
        return "执行完成";
    }
});

/**
 * [description]性能-告警配置-告警类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transferConfigType", function(value) {
    if (value == "1") {
        return "单项";
    } else if (value == "2") {
        return "多项一半";
    }else if (value == "3") {
        return "多项全部";
    }
});
/**
 * [description]性能-告警配置-告警类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("appendBaifenhao", function(value) {
    if(value)
    return value+"%";
});
/**
 * [description]性能-压测问题跟踪-问题类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("PerProblemType", function(value) {
    if (value == "1") {
        return "脚本异常";
    } else if (value == "2") {
        return "系统/环境异常";
    }else if (value == "3") {
        return "发布异常";
    }else if (value == "4") {
        return "代码异常";
    }else if (value == "5") {
        return "配置异常";
    }
});
/**
 * [description]性能-压测问题跟踪-问题原因
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("PerProblemReason", function(value) {
    if (value == "1") {
        return "脚本代码/设置问题";
    } else if (value == "2") {
        return "号码资源问题";
    }else if (value == "3") {
        return "错误量极少，无影响";
    }else if (value == "4") {
        return "系统配置问题";
    }else if (value == "5") {
        return "缓存刷新";
    }else if (value == "6") {
        return "未查明";
    }else if (value == "7") {
        return "发布异常";
    }else if (value == "8") {
        return "代码异常";
    }
});
/**
 * [description]流程类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
 Handlebars.registerHelper("transferFlowType", function(value) {
    if (value == "0") {
        return "系统";
    } else if (value == "1") {
        return "BOSS";
    }else if (value == "2") {
        return "开通";
    }else if (value == "3") {
        return "产品";
    }else if (value == "4") {
        return "下架";
    }

});

/**
 * [description]性能-压测问题跟踪-问题状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("PerProblemState", function(value) {
    if (value == "1") {
        return "未解决";
    } else if (value == "2") {
        return "已解决";
    }else if (value == "3") {
        return "无需解决";
    }
});
/**
 * [description]性能-压测问题跟踪-处理结果
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("PerDealResult", function(value) {
    if (value == "1") {
        return "未处理";
    } else if (value == "2") {
        return "处理中";
    }else if (value == "3") {
        return "处理完成";
    } else if (value == "4") {
        return "继续跟踪";
    }else if (value == "5") {
        return "无需处理";
    }
});
/**
 * [description]流程配置-计划-类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getFlowConfigPlanType", function(value) {
    if (value == "1") {
        return "BOSS验收";
    } else if (value == "2") {
        return "开通验收";
    }
});

/**
 * [description]流程配置-计划任务-类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("flowConfigPlanType", function(value) {
    if (value == 0) {
        return "未执行";
    } else if (value == 1) {
        return "执行中";
    } else if (value == 2) {
        return "执行结束";
    } else if (value == 3) {
        return "已删除";
    }else if (value == 4) {
        return "执行失败";
    } 
});

/**
 * [description]非功能-计划任务实时监控-健康指标-状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("imgStatus", function(value) {
    if (value == "green") {
        return new Handlebars.SafeString('<img src="style/images/clock-1.png">');
    } else if (value == "red") {
        return new Handlebars.SafeString('<img src="style/images/clock-3.png">');
    } else if (value == "yellow") {
        return new Handlebars.SafeString('<img src="style/images/clock-2.png">');
    }
});

/**
 * [description]性能-机器监控-Jmeter状态
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("jmeterStatus", function(value) {
    if (value == "1") {
        return new Handlebars.SafeString('<img src="style/images/jmeterStatus3.png">');
    } else if (value == "2") {
        return new Handlebars.SafeString('<img src="style/images/jmeterStatus1.png">');
    } else if (value == "3") {
        return new Handlebars.SafeString('<img src="style/images/jmeterStatus2.png">');
    }else{
       return new Handlebars.SafeString('<img src="" alt="图片无法显示" style="width:136px; height: 96px;">');  
    }
});


/**
 * [description]性能-方案类型
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transferSchemeType", function(value) {
    if (value == "1") {
        return "一键式发起方案";
    } else if (value == "2") {
        return "非一键式发起方案";
    } else{
        return "非一键式发起方案"; 
    }
});

/**
 * [description]定时提醒任务--发送方式
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transferSendMode", function(value) {
    if (value == "0") {
        return "短信";
    } else if (value == "1") {
        return "邮件";
    } else if (value == "2") {
        return "短信和邮件";
    }
});
/**
 * [description]定时提醒任务--发送标记
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("transferSendMark", function(value) {
    if (value == "0") {
        return "待发送";
    } else if (value == "1") {
        return "发送中";
    } else if (value == "2") {
        return "发送完成";
    }
});
/**
 * [description]健康监控--四舍五入取小数点2位
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getMonsuccessPer", function(value) {
    if (value) {
        return Math.round(value*100)/100 + '%';
    }else{
        return value;
    }
    
});

/**
 * [description]健康监控--向下取整
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getMonaverageTimesRight", function(value) {
    if (value) {
        return Math.floor(value);
    }else{
        return value;
    }
});
/**
 * [description]健康监控--添加下划线
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("getMonphName", function(value) {
    if (value) {
        return value+"_";
    }
    else{
        return "";
    }
    
});
/**
 * [description]dev--需求评估相关管理--是否重点关注需求
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("devFocusOnRequirements", function(value) {
    if (value == "0") {
        return "否";
    } else if (value == "1") {
        return "是";
    } else if (value == "2") {
        return "所有";
    }
});

/**
 * [description]dev--需求评估相关管理--生产是否可测
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("devExt2", function(value) {
    if (value == "0") {
        return "否";
    } else if (value == "1") {
        return "是";
    } else if (value == "2") {
        return "所有";
    }
});
/**
 * [description]dev--需求评估相关管理--归属团队
 * @param  {[type]} value)}
 * @return {[type]} [description]
 */
Handlebars.registerHelper("devOwnershipTeam", function(value) {
    if (value == "0") {
        return "个人业务";
    } else if (value == "2") {
        return "账管业务";
    } else if (value == "3") {
        return "个人业务";
    } else if (value == "4") {
        return "政企业务";
    } else if (value == "5") {
        return "家庭业务";
    } else if (value == "6") {
        return "携号转网项目";
    } else if (value == "7") {
        return "家庭融合项目";
    } else if (value == "8") {
        return "个人业务";
    } else if (value == "9") {
        return "业务大厅项目";
    } else if (value == "11"){
    	return "物联网业务";
    }else if (value ==  "12"){
    	return "个人业务";
    }else if(value == "14"){
    	return "业务轻载项目";
    }else if (value == "15"){
    	return "个人集团";
    }else if (value == "16"){
    	return "平台框架组";
    }else if (value == "10") {
        return "所有";
    }
});
/**
 * [description]dev--需求评估相关管理--测试状态
 * @param  {[type]} value)}
 * @return {[type]} [description] 
 */
Handlebars.registerHelper("devTestStatus", function(value) {
    if (value == "0") {
        return "代码未提交";
    } else if (value == "1") {
        return "代码已提交";
    } else if (value == "8"){
    	return "测试中（外围系统异常）";
    } else if (value == "2"){
    	return "测试中（环境异常）";
    } else if (value == "5"){
    	return "测试中（主线异常）";
    } else if (value == "6"){
    	return "测试中（遍历用例）";
    } else if (value == "7"){
    	return "待bug解决";
    } else if (value == "3") {
        return "测试完成";
    } else if (value == "4") {
        return "未完成";
    }
});


Handlebars.registerHelper("devWeek", function(value) {
    if (value == "1") {
        return "新业务";
    } else if (value == "2") {
        return "中心化群";
    } else if(value == "3"){
    	return "发票稽核酬金平台";
    } else if (value == "4") {
        return "政企";
    } else if (value == "5"){
    	return "客服";
    } else if (value == "6"){
    	return "物联网";
    } else if (value == "7"){
    	return "质控自动化";
    }
});

Handlebars.registerHelper("devTransStatus", function(value) {
    if (value == "0") {
        return "未开始";
    } else if (value == "1") {
        return "联调中";
    } else if(value == "2"){
    	return "联调完成";
    }
});

Handlebars.registerHelper("devOnlineStatus", function(value) {
    if (value == "1") {
        return "例行上线";
    } else if (value == "2") {
        return "项目上线";
    } else if(value == "3"){
    	return "新业务紧急上线";
    } else if(value == "4"){
    	return "上线时间待定";
    } else if(value == "5"){
    	return "质控平台上线专用"
    }
});

Handlebars.registerHelper("devOnlineDate", function(value) {
    if (value == "0") {
        return "失效";
    } else if (value == "1") {
        return "有效";
    } 
});

Handlebars.registerHelper("devBossLianTiao", function(value) {
    if (value == "0") {
        return "是";
    } else if (value == "1") {
        return "否";
    } 
});

Handlebars.registerHelper("devSystemBossDemand", function(value) {
    if (value == "0") {
        return "否";
    } else if (value == "1") {
        return "是";
    } 
});

Handlebars.registerHelper("devIfOnline", function(value) {
    if (value == "0") {
        return "未上线";
    } else if (value == "1") {
        return "已上线";
    } 
});

Handlebars.registerHelper("autoExt", function(value) {
    if (value == "0" || value == null) {
        return "未关联自动化";
    } else if (value == "1") {
        return "详情";
    } 
});

Handlebars.registerHelper("devOwnerTeam", function(value) {
    if (value == "0" || value == null) {
        return "个人集团组";
    } else if (value == "1") {
        return "客服";
    } else if (value == "2") {
    	return "账管";
    } else if (value == "3") {
    	return "家庭";
    }
});

Handlebars.registerHelper("transIsSolved", function(value) {
    if (value == "0") {
        return "未处理";
    } else if (value == "1") {
        return "已处理";
    } 
});

Handlebars.registerHelper("transSheetStyle", function(value) {
    if (value == "1") {
        return "环境问题类";
    } else if (value == "2") {
        return "需求工单";
    } else if (value == "3") {
    	return "自动化工单";
    } else if (value == "4") {
    	return "环境搭建工单";
    } else if (value == "5") {
    	return "其他";
    }
});


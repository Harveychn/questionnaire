/**
 * creator: xiaohui zheng
 * time: 2017/11/26  18:09
 * function: 结果分析页面
 */
require('../../js/jquery.min.js');
require('../../js/echarts.min');
require('../../js/infographic');
require('../../js/plugins/table-export/table-export');

function AnalyzeResult(echartAreaId) {
    var echartArea = document.getElementById(echartAreaId);
    var chart = echarts.init(echartArea, 'infographic');

};


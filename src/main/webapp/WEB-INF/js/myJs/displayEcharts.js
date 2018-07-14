/**
 * Created by 郑晓辉 on 2017/5/28.
 */
//获取展示表格区域的ID
var dom = document.getElementById("echartsArea");
var myChart = echarts.init(dom, 'infographic');

var $curQuestionContent = $('#curQuestionContent');
var $questionList = $('#questionList');
var $tableBody = $('#tableBody');
var $questionTableData = $('#questionTableData');

setExportDataAttr();
window.onload = function () {
    TableExport.init();
};

$(window).resize(function () {
    myChart.resize();
});

//当前问卷结果分析数据
var analyzeResultData;

$(function () {
    //初始化时样式
    //显示加载中样式
    myChart.showLoading();

    $.ajax({
        url: '/statisticalAnalysis/getQesPaperAnalyzeResult',
        type: 'get',
        async: false,
        dataType: 'json',
        success: function (responseData) {
            if (responseData.code === 200) {
                analyzeResultData = responseData.data;
            }
            if (responseData.code === 600) {
                layer.confirm(responseData.message,
                    {
                        icon: 7,
                        title: '提示',
                        btn: ['关闭界面']
                    }, function (index) {
                        layer.close(index);
                        window.close();
                    });
            }
        },
        error: function (data) {
            console.error(data);
        }
    });

    if ('undefined' !== typeof analyzeResultData) {
        for (var i = 0; i < analyzeResultData.length; i++) {
            if (i === 0) {
                $curQuestionContent.html('第' + (i + 1) + '题  ' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')');
                //设置表格的数据
                setTableBodyData(i);

                $questionList.append('<button class="list-group-item btn" ' +
                    'style="text-align: left; background-color: rgba(51,122,183,0.82);color: whitesmoke;" id="' + analyzeResultData[i].questionId +
                    '" onclick="clickQuestionListItem(this.id)">' +
                    (i + 1) + '、' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')</button>');
                continue;
            }
            $questionList.append('<button class="list-group-item btn" ' +
                'style="text-align: left;" id="' + analyzeResultData[i].questionId +
                '" onclick="clickQuestionListItem(this.id)">' +
                (i + 1) + '、' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')</button>');
        }
        initEchartData();
    }
});

var curQuestionAnalyzeData = {};

function clickQuestionListItem(qesId) {
    for (var i = 0; i < analyzeResultData.length; i++) {
        //设置问题栏背景色
        var $questionList = $('#questionList');
        $questionList.find('button[id=' + qesId + ']').attr('style', 'text-align: left;background-color: rgba(51,122,183,0.82);color: whitesmoke;"');
        $questionList.find('button[id!=' + qesId + ']').attr('style', 'text-align: left;');
        if (analyzeResultData[i].questionId == qesId) {
            curQuestionAnalyzeData.categories = analyzeResultData[i].optionList;
            curQuestionAnalyzeData.data = analyzeResultData[i].valueList;
            $curQuestionContent.html('第' +
                (i + 1) + '题  ' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')');
            //设置表格数据
            setTableBodyData(i);
        }
    }
    dynamicDataChange(curQuestionAnalyzeData);
}

function setTableBodyData(i) {
    // 表格数据初始化处理
    $tableBody.empty();
    var optionList = analyzeResultData[i].optionList;
    var valueList = analyzeResultData[i].valueList;
    //图片题设置
    if (analyzeResultData[i].questionType == '图片单选题' || analyzeResultData[i].questionType == '图片多选题') {

        console.log(optionList);
        console.log(valueList);
        for (var i = 0; i < optionList.length; i++) {

            $.ajax({
                url: '/fileIo/download/picture/qesPaper?picRelativePath=' + encodeURI(optionList[i]),
                method: 'get',
                dataType: 'text',
                success: function (data) {
                    console.log(data);
                    $tableBody.append(' <div class="col-xs-6 col-md-3">\n' +
                        '    <a href="#" class="thumbnail">\n' +
                        '      <img src="data:image/png;base64,' + data + '" alt="图片题">\n' +
                        '    </a>\n' +
                        '  </div>')
                    // $tableBody.append('<img src="data:image/png;base64,' + data + '" style="width: 20px;height: 20px;"/>');

                }
            });
            // $tableBody.append('<img style="width: inherit;height: inherit;" th:src="@{/fileIo/refPic(picRelativePath=' + optionList[i] + ')}"/>');
        }
        return;
    }
    var sumValue = 0;
    var rowDataArray = [];
    for (var index = 0; index < valueList.length; index++) {
        sumValue += valueList[index];
        var tableRowData = {};
        tableRowData.option = optionList[index];
        tableRowData.value = valueList[index];
        rowDataArray.push(tableRowData);
    }
    for (var rowindex = 0; rowindex < rowDataArray.length; rowindex++) {
        if (sumValue === 0) {
            $tableBody.append('<tr class="row-active">' +
                '<td>' + rowDataArray[rowindex].option + '</td>' +
                '<td>' + rowDataArray[rowindex].value + '</td>' +
                '<td>0%</td>' +
                '</tr>');
        } else {
            $tableBody.append('<tr class="row-active">' +
                '<td>' + rowDataArray[rowindex].option + '</td>' +
                '<td>' + rowDataArray[rowindex].value + '</td>' +
                '<td>' + (rowDataArray[rowindex].value / sumValue * 100).toFixed(2) + '%</td>' +
                '</tr>');
        }
    }
}

function dynamicDataChange(dynamicDataChange) {
    var dynamicOption = barOp({
        xData: dynamicDataChange.categories,
        seriesData: dynamicDataChange.data
    });
    setEchartOption(dynamicOption);
}

/**
 * 初始化展示Echarts视图
 */
function initEchartData() {
    var initData = analyzeResultData[0];
    curQuestionAnalyzeData.categories = initData.optionList;
    curQuestionAnalyzeData.data = initData.valueList;
    myChart.hideLoading();
    var initOption = barOp({
        xData: curQuestionAnalyzeData.categories,
        seriesData: curQuestionAnalyzeData.data
    });
    setEchartOption(initOption);
}

//点击表格按钮后样式
var _isShow = true;
$('#tableBtn').on('click', function () {
    _isShow ? $questionTableData.hide() : $questionTableData.show();
    _isShow = !_isShow;
});

//点击柱状图
$('#histogramBtn').on('click', function () {
    exStatus('#pieChartBtn', this);

    myChart.showLoading();
    //设置数据
    myChart.hideLoading();
    //柱状图 option
    var barOption = barOp({
        xData: curQuestionAnalyzeData.categories,
        seriesData: curQuestionAnalyzeData.data
    });
    setEchartOption(barOption);
});

//点击扇形图后样式
$('#pieChartBtn').on('click', function () {
    exStatus('#histogramBtn', this);
    myChart.showLoading();
    var resultData = [];
    var dataLength = curQuestionAnalyzeData.data.length;
    for (var i = 0; i < dataLength; i++) {
        var dataItem = {};
        dataItem.name = curQuestionAnalyzeData.categories[i];
        dataItem.value = curQuestionAnalyzeData.data[i];
        resultData.push(dataItem);
    }
    myChart.hideLoading();
    //扇形图 option
    var pieOption = pieOp({
        legends: curQuestionAnalyzeData.categories,
        seriesData: resultData
    });
    setEchartOption(pieOption);
});

/**
 * 柱状图选项
 * @param optionData
 */
function barOp(optionData) {
    return {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        toolbox: {
            show: true,
            orient: 'horizontal',
            feature: {
                saveAsImage: {
                    show: true
                },
                restore: {
                    show: true
                },
                dataZoom: {
                    show: true
                }
            }
        },
        grid: {
            left: '3%',
            right: '3%',
            bottom: '15%',
            containLabel: true,
            // height: '60%'
        },
        xAxis: {
            type: 'category',
            axisLabel: {
                rotate: 45,
                margin: 10,
                showMinLabel: true,
                showMaxLabel: true,
                interval: 0,
                textStyle: {
                    fontWeight: 'lighter',
                    fontSize: 12,
                    baseline: 'bottom'
                }
            },
            data: optionData.xData
        },
        yAxis: {
            position: 'top',
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        series: [
            //动态数据
            {
                type: 'bar',
                data: optionData.seriesData,
                itemStyle: {
                    normal: {
                        //设置随机颜色
                        color: function (params) {
                            // 颜色列表
                            var colorList = [
                                '#C1232B', '#B5C334', '#FCCE10', '#E87C25', '#27727B',
                                '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                            ];
                            return colorList[params.dataIndex]

                        },

                        //显示位置和显示格式的设置了
                        label: {
                            show: true,
                            position: 'top',
                            //显示数据在柱状图上
                            formatter: '{c}'
                        }
                    }
                },
                //柱条的宽度，不设时自适应。(数字的含义就是百分比,不需要填写％)
                barWidth: 50
            }
        ]
    }
};

/**
 * 扇形图选项
 * @param optionData
 */
function pieOp(optionData) {
    return {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        toolbox: {
            show: true,
            orient: 'horizontal',
            feature: {
                saveAsImage: {
                    show: true
                },
                restore: {
                    show: true
                },
                dataZoom: {
                    show: true
                }
            }
        },
        legend: {
            type: 'scroll',
            bottom: 10,
            data: optionData.legends
        },
        series: [{
            name: '比例',
            type: 'pie',
            stillShowZeroSum: true,
            radius: '40%',
            center: ['50%', '50%'],
            //动态数据
            data: optionData.seriesData,
            label: {
                normal: {
                    show: true,
                    position: 'outside',
                    formatter: '{b} - {d}%'
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    show: true
                }
            },
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }]
    }
}

function exStatus(btnId, element) {
    $(element).attr('class', 'btn btn-outline btn-clicked');
    $(btnId).attr('class', 'btn btn-outline btn-default');
}

/**
 *设置echarts样式
 * @param optionType
 */
function setEchartOption(optionType) {
    if (optionType && typeof optionType === "object") {
        myChart.setOption(optionType, true);
    }
}
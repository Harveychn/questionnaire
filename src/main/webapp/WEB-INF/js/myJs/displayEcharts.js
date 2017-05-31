/**
 * Created by 郑晓辉 on 2017/5/28.
 */
//获取展示表格区域的ID
var dom = document.getElementById("echartsArea");
var myChart = echarts.init(dom);
var app = {};

var $curQuestionContent = $('#curQuestionContent');
var $questionList = $('#questionList');
var $tableBody = $('#tableBody');
var $questionTableData = $('#questionTableData');

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
        success: function (data) {
            analyzeResultData = data;
        },
        error: function (data) {
            console.log(data);
        }
    });

    for (var i = 0; i < analyzeResultData.length; i++) {
        $questionList.append('<li class="list-group-item btn" style="text-align: left;" id="' + analyzeResultData[i].questionId
            + '" onclick="clickQuestionListItem(this.id)">第'
            + (i + 1) + '题  ' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')</li>');
        if (i === 0) {
            $curQuestionContent.html('第'
                + (i + 1) + '题  ' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')');
            //设置表格的数据
            setTableBodyData(i);
        }
    }

    initEchartData();
});

var curQuestionAnalyzeData = {};

/**
 * 初始化展示Echarts视图
 */
function initEchartData() {
    var initData = analyzeResultData[0];
    curQuestionAnalyzeData.categories = initData.optionList;
    curQuestionAnalyzeData.data = initData.valueList;
    myChart.hideLoading();
    var initOption = {
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
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            position: 'top',
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            //动态数据
            data: initData.optionList
        },
        series: [
            //动态数据
            {
                type: 'bar',
                data: initData.valueList
            }
        ]
    };
    setEchartOption(initOption);
}


function clickQuestionListItem(qesId) {
    for (var i = 0; i < analyzeResultData.length; i++) {
        if (analyzeResultData[i].questionId == qesId) {
            curQuestionAnalyzeData.categories = analyzeResultData[i].optionList;
            curQuestionAnalyzeData.data = analyzeResultData[i].valueList;
            $curQuestionContent.html('第'
                + (i + 1) + '题  ' + analyzeResultData[i].questionContent + '(' + analyzeResultData[i].questionType + ')');

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
            $tableBody.append('<tr>' +
                '<td>' + rowDataArray[rowindex].option + '</td>' +
                '<td>' + rowDataArray[rowindex].value + '</td>' +
                '<td>0%</td>' +
                '</tr>');
        } else {
            $tableBody.append('<tr>' +
                '<td>' + rowDataArray[rowindex].option + '</td>' +
                '<td>' + rowDataArray[rowindex].value + '</td>' +
                '<td>' + rowDataArray[rowindex].value / sumValue * 100 + '%</td>' +
                '</tr>');
        }
    }
}

function dynamicDataChange(dynamicDataChange) {
    var dynamicOption = {
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
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            position: 'top',
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            //动态数据
            data: dynamicDataChange.categories
        },
        series: [
            //动态数据
            {
                type: 'bar',
                data: dynamicDataChange.data
            }
        ]
    };
    setEchartOption(dynamicOption);
}

//点击表格按钮后样式
$('#tableBtn').on('click', function () {
    if($questionTableData.attr('hidden')===undefined){
        $questionTableData.attr('hidden','hidden');
    }else {
        $questionTableData.removeAttr('hidden');
    }
});
//点击柱状图
$('#histogramBtn').on('click', function () {
    myChart.showLoading();
    //设置数据
    myChart.hideLoading();
    //柱状图 option
    var histogramOption = {
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
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            //动态数据
            // data: []
            data: curQuestionAnalyzeData.categories
        },
        yAxis: {
            position: 'top',
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        series: [//动态数据
            {
                type: 'bar',
                data: curQuestionAnalyzeData.data
            }
        ]
    };
    setEchartOption(histogramOption);
});

//点击条形图后样式
$('#barGraphBtn').on('click', function () {
    myChart.showLoading();
    myChart.hideLoading();
    //条形图 option
    var barGraphOption = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        }, toolbox: {
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
            orient: 'vertical',
            left: 'left',
            //动态数据
            // data: []
            data: curQuestionAnalyzeData.categories
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            position: 'top',
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: {
            type: 'category',
            //动态数据
            // data: []
            data: curQuestionAnalyzeData.categories
        },
        series: [//动态数据
            {
                type: 'bar',
                data: curQuestionAnalyzeData.data
            }
        ]
    };
    setEchartOption(barGraphOption);
});

//点击扇形图后样式
$('#pieChartBtn').on('click', function () {
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
    var pieChartOption = {
        // title: {
        //     text: '某站点用户访问来源',
        //     subtext: '纯属虚构',
        //     x: 'center'
        // },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        }, toolbox: {
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
            orient: 'vertical',
            left: 'left',
            right: '20%',
            // itemWidth:'20',
            // itemHeight:'10',
            //动态数据
            // data: []
            data: curQuestionAnalyzeData.categories
        },
        series: [
            {
                name: '比例',
                // name: '访问来源',
                type: 'pie',
                radius: '40%',
                center: ['60%', '60%'],
                //动态数据
                data: resultData,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    setEchartOption(pieChartOption);
});


/**
 *设置echarts样式
 * @param optionType
 */
function setEchartOption(optionType) {
    if (optionType && typeof optionType === "object") {
        myChart.setOption(optionType, true);
    }
}
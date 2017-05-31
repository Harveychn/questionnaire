/**
 * Created by 郑晓辉 on 2017/5/28.
 */
//获取展示表格区域的ID
var dom = document.getElementById("echartsArea");
var myChart = echarts.init(dom);
var app = {};
// option = null;

function fetchData(cb) {
    // 通过 setTimeout 模拟异步加载
    setTimeout(function () {
        //后台数据格式
        cb({
            categories: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子", "测试"],
            data: [5, 20, 36, 10, 10, 20, 21]
        });
    }, 1000);

    $.ajax({
        url: '/statisticalAnalysis/getQesPaperAnalyzeResult',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

//初始化时样式
//显示加载中样式
myChart.showLoading();
//设置数据
fetchData(function (data) {
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
            data: data.categories
        },
        series: [
            //动态数据
            {
                type: 'bar',
                data: data.data
            }
        ]
    };
    myChart.setOption(initOption);
});

//点击表格按钮后样式
$('#tableBtn').on('click', function () {
});
//点击柱状图
$('#histogramBtn').on('click', function () {
    myChart.showLoading();
    //设置数据
    fetchData(function (data) {
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
                data: data.categories
            },
            yAxis: {
                position: 'top',
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            series: [//动态数据
                {
                    type: 'bar',
                    data: data.data
                }
            ]
        };
        setEchartOption(histogramOption);
    });
});

//点击条形图后样式
$('#barGraphBtn').on('click', function () {
    myChart.showLoading();
    fetchData(function (data) {
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
                data: data.categories
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
                data: data.categories
            },
            series: [//动态数据
                {
                    type: 'bar',
                    data: data.data
                }
            ]
        };
        setEchartOption(barGraphOption);
    });
});

//点击扇形图后样式
$('#pieChartBtn').on('click', function () {
    myChart.showLoading();
    fetchData(function (data) {
        var resultData = [];
        var dataLength = data.data.length;
        for (var i = 0; i < dataLength; i++) {
            var dataItem = {};
            dataItem.name = data.categories[i];
            dataItem.value = data.data[i];
            resultData.push(dataItem);
        }

        console.log(resultData);

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
                //动态数据
                // data: []
                data: data.categories
            },
            series: [
                {
                    name: '比例',
                    // name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    //动态数据
                    data: resultData,
                    // data: [
                    //     {value: 335, name: '直接访问'},
                    //     {value: 310, name: '邮件营销'},
                    //     {value: 234, name: '联盟广告'},
                    //     {value: 135, name: '视频广告'},
                    //     {value: 1548, name: '搜索引擎'}
                    // ],
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
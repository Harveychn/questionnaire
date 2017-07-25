/**
 * questionnaire
 * Created by 郑晓辉 on 2017/5/29.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#allMissionTable');

var tableDataUrl = '/researchManage/getReleasedMissions';
var analyzeResultUrl = '/statisticalAnalysis/getAnalyzeResultView';
$(function () {
    $table.bootstrapTable({
        url: tableDataUrl,
        method: 'get',
        dataType: 'json',
        cache: false,

        striped: true,
        undefinedText: '--',
        sortOrder: 'desc',
        height: getHeight(),

        pagination: true,
        paginationLoop: true,
        pageList: [10, 20, 30],
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        sidePagination: 'client',

        search: true,
        searchAlign: 'right',
        searchOnEnterKey: false,
        toolbar: '#tableToolbar',
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        showPaginationSwitch: true,

        minimumCountColumns: 3,
        detailView: true,
        detailFormatter: detailFormatter,
        columns: [{
            field: 'missionId',
            title: '调查编号',
            sortable: true
        }, {
            field: 'description',
            title: '调查描述',
            sortable: true
        }, {
            field: 'beginDate',
            title: '调查开始时间',
            sortable: true,
            formatter: fieldBeginDateFormatter
        }, {
            field: 'endDate',
            title: '调查结束时间',
            sortable: true,
            formatter: fieldEndDateFormatter
        }, {
            field: 'creator',
            title: '创建人',
            sortable: true
        }, {
            field: 'creatorUnit',
            title: '创建人所属单位',
            sortable: true
        }, {
            title: '操作',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});
//单份处理
window.operateEvents = {
    //统计分析
    'click .analyze': function (e, value, row, index) {
        window.open(analyzeResultUrl + '?missionId=' + row.missionId + '&missionPaperId=' + row.missionPaperDTOList[0].questionnaireId);
    }
};
//时间格式设置
function fieldBeginDateFormatter(value, row, index) {
    var htmlElement = [];
    htmlElement.push('<span> ' + formatDate(new Date(row.beginDate)) + '</span>');
    return htmlElement.join('');
}
function fieldEndDateFormatter(value, row, index) {
    var htmlElement = [];
    htmlElement.push('<span> ' + formatDate(new Date(row.endDate)) + '</span>');
    return htmlElement.join('');
}
//操作按钮格式设置
function operateFormatter(value, row, index) {
    var htmlElement = [];
    htmlElement.push('<a class="analyze btn btn-sm btn-link" href="javascript:void(0)" ' +
        'data-toggle="tooltip" title="统计分析调查结果">',
        '<i class="glyphicon glyphicon-stats"></i> 统计分析');
    return htmlElement.join('');
}

/*检查是否选中一种问卷*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作的问卷', {icon: 5});
        return false;
    }
    return true;
}
//单个操作确认弹窗
function layerConfirmSingle(confirmText, row, url) {
    layer.confirm(confirmText, {
            icon: 3,
            btn: ['确定', '取消']
        },
        function (index) {
            var ids = [];
            ids.push(row.questionnaireId);
            accessServer(ids, url);
            layer.close(index);
        },
        function () {
            layer.msg('取消成功！');
        }
    )
}
//批量操作确认弹窗
function layerConfirmMulti(confirmText, ids, url) {
    layer.confirm(confirmText, {
            icon: 3,
            btn: ['确定', '取消']
        },
        function (index) {
            accessServer(ids, url);
            layer.close(index);
        },
        function () {
            layer.msg('取消成功！');
        }
    )
}

/**
 * 异步加载服务器
 * @param questionnaireIds
 * @param url
 */
function accessServer(questionnaireIds, url) {
    $.ajax({
        url: url,
        type: 'post',
        data: {questionnaireIds: questionnaireIds},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            analyzeResponse(data, url, questionnaireIds);
        },
        error: function () {
            layer.msg('操作失败，出现点问题，刷新看看？', {icon: 2});
        }
    });
    return true;
}
/**
 * 查看问卷
 * @param urlWithId 查看问卷带有问卷ID的url
 */
function browseQesPaper(urlWithId) {
    window.open(urlWithId);
}

/**
 * 解析回复数据包
 * @param data
 * @param url
 * @param questionnaireIds
 */
function analyzeResponse(data, url, questionnaireIds) {
    var responsePkt = JSON.parse(data);
    if (responsePkt.code === 200) {
        switch (url) {
            case templateUrl://模板化问卷
                if (responsePkt.code === 200) {
                    // $table.bootstrapTable('remove', {
                    //     field: 'questionnaireId',
                    //     values: questionnaireIds
                    // });
                    layer.msg('问卷已经成功添加到个人模板库！', {icon: 1});
                }
                dealGlobalError(responsePkt);
                break;
            case delTemporaryUrl://删除(回收)问卷
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'questionnaireId',
                        values: questionnaireIds
                    });
                    layer.msg('问卷已经移除！（可以在“垃圾”问卷中恢复）', {icon: 1});
                }
                dealGlobalError(responsePkt);
                break;
            case shareUrl://共享问卷
                if (responsePkt.code === 200) {
                    // $table.bootstrapTable('remove', {
                    //     field: 'questionnaireId',
                    //     values: questionnaireIds
                    // });
                    layer.msg('问卷成功共享！', {icon: 1});
                }
                dealGlobalError(responsePkt);
                break;
            default:
                return;
        }
    }
}

//获取批量选中的id
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.questionnaireId;
    });
}

//详细信息格式
function detailFormatter(index, row) {
    var html = [];
    html.push('<i>任务执行单位:<br></i><ol>');
    var executeUnitList = row.executeUnitList;
    if (executeUnitList && 'object' === typeof executeUnitList) {
        for (var i = 0; i < executeUnitList.length; i++) {
            html.push('<li>' + executeUnitList[i] + '</li>');
        }
    } else {
        html.push('<span>--</span>');
    }
    html.push('</ol>');

    html.push('<i>调查问卷信息:<br/></i><table class="table table-bordered table-hover">' +
        '<thead><tr class="gray-bg">' +
        '<td>调查问卷标题</td>' +
        '<td>调查问卷描述</td>' +
        '<td>最少提交量</td>' +
        '<td>当前提交量</td>' +
        '</tr></thead><tbody>');
    var qesPapers = row.missionPaperDTOList;
    if (qesPapers && 'object' === typeof qesPapers) {
        for (var i = 0; i < qesPapers.length; i++) {
            html.push('<tr>' +
                '<td>' + qesPapers[i].questionnaireTitle + '</td>' +
                '<td>' + qesPapers[i].questionnaireDescription + '</td>' +
                '<td>' + qesPapers[i].minSubmit + '</td>' +
                '<td>' + qesPapers[i].submitNow + '</td>' +
                '</tr>');
        }
    } else {
        html.push('<span>--</span>');
    }
    html.push('</tbody></table>');
    return html.join('');
}
//日期格式处理
function formatDate(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    var minute = date.getMinutes();
    var second = date.getSeconds();
    h = h < 10 ? ('0' + h) : h;
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}
//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
/**
 * Created by Administrator on 2017/5/3.
 */
var $table = $('#missionManageTable');

$(function () {
    $table.bootstrapTable({
        url: '/researchManage/listMission',
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortName: ['missionId', 'questionnaireTitle', 'questionnaireCount', 'minSubmitCount', 'missionStartDate', 'missionFinalDate', 'missionStatus'],
        sortOrder: 'desc',
        height: getHeight(),

        pagination: true,
        paginationLoop: true,
        pageList: [10, 20, 30],
        paginationPreText: '上一页',
        paginationNextText: '下一页',
        sidePagination: 'client',

        search: true,

        toolbar: '#tableToolbar',

        // showColumns: true,
        showRefresh: true,
        showToggle: true,
        showPaginationSwitch: true,

        minimumCountColumns: 3,
        columns: [{
            field: 'missionId',
            title: '任务编号',
            align: 'center',
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '问卷标题',
            align: 'center',
            sortable: true
        }, {
            field: 'questionnaireCount',
            title: '完成量',
            align: 'center',
            sortable: true
        }, {
            field: 'minSubmitCount',
            title: '最低提交量',
            align: 'center',
            sortable: true
        }, {
            field: 'missionStartDate',
            title: '开始时间',
            align: 'center',
            sortable: true
        }, {
            field: 'missionFinalDate',
            title: '结束时间',
            align: 'center',
            sortable: true
        }, {
            field: 'missionStatus',
            title: '完成状态',
            align: 'center',
            sortable: true
        }, {
            title: '操作',
            width: 200,
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});

var checkDataUrl = '';
//删除url
var delMissionMagaUrl = '';
//单份处理
window.operateEvents = {
    //提醒
    'click .check': function (e, value, row, index) {
        // layer.alert(row.missionId + "||" + row.questionnaireId);
        checkDataUrl = '/notice/getCreateNoticeForMission?missionId=' + row.missionId + '&qesId=' + row.questionnaireId;
        layerMsg('提醒', row, checkDataUrl);
    },
    'click .pencil': function (e, value, row, index) {
        layer.open({
            type: 2,
            title: '结束时间修改',
            maxmin: true,
            content: '/researchManage/getMissionManageEditView?missionId=' + row.missionId,
            area: ['40%', '50%'],
            resize: true
        });
    },
    //删除问卷
    'click .remove': function (e, value, row, index) {
        delMissionMagaUrl = '/researchManage/deleteMission?missionId' + row.missionId + '&questionnaireId=' + row.questionnaireId,
            layerConfirmSingle('确认删除吗?', row, delMissionMagaUrl);
    }
};

function refreshTable() {
    $table.bootstrapTable('refresh', {});
}

/*弹窗层*/
function layerMsg(confirmText, ids, url) {
    var layerLevel = layer.open({
        type: 2,
        title: '任务提醒',
        maxmin: true,
        content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        area: ['80%', '90%'],
        resize: true
    });
    layer.full(layerLevel);
}

//删除
function layerConfirmSingle(confirmText, row, url) {
    layer.confirm(confirmText, {
            icon: 3,
            btn: ['确定', '取消']
        },
        function (index) {
            var missionIds = [];
            var questionnaireIds = [];
            missionIds.push(row.missionId);
            questionnaireIds.push(row.questionnaireId);
            accessServer(missionIds, questionnaireIds, url);
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
function accessServer(missionIds, questionnaireIds, url) {
    $.ajax({
        url: url,
        type: 'post',
        data: {missionId: missionIds, questionnaireId: questionnaireIds},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            analyzeResponse(data, url, missionIds, questionnaireIds);
        },
        error: function () {
            layer.msg('操作失败，出现点问题，刷新看看？', {icon: 2});
        }
    });
    return true;
}

/**
 * 解析回复数据包
 * @param data
 * @param url
 * @param missionIds
 * @param questionnaireIds
 */
function analyzeResponse(data, url, missionIds, questionnaireIds) {
    var responsePkt = JSON.parse(data);
    if (responsePkt.code === 200) {
        switch (url) {
            case delMissionMagaUrl://永久删除问卷
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'missionId',
                        values: missionIds
                    // }, {
                    //     field: 'questionnaireIds',
                    //     values: questionnaireIds
                    });
                    layer.msg('已经永久删除！', {icon: 1});
                }
                dealGlobalError(responsePkt);
                break;
            default:
                return;
        }
    }
}


/*检查是否选中一种问卷*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作的问卷', {icon: 5});
        return false;
    }
    return true;
}

//操作按钮格式设置
function operateFormatter(value, row, index) {
    var htmlElement = [];
    htmlElement.push('<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="提醒">',
        '<i class="glyphicon glyphicon-check"></i> 提醒',
        '</a>');
    if (row.missionStatus === "已截止") {
        htmlElement.push('<a class="pencil btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="修改">',
            '<i class="glyphicon glyphicon-pencil"></i> 修改',
            '</a>');
    }
    if (row.missionStatus === "未开始") {
        htmlElement.push('<a class="remove btn btn-sm btn-link" href="javascript:void(0)" ' +
            'data-toggle="tooltip" title="删除">',
            '<i class="glyphicon glyphicon-remove"></i> 删除',
            '</a>');
    }
    return htmlElement.join('');
}
//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
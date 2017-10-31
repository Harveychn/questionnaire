/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/29.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#recycleQesPaperTable');
//批量永久删除按钮
var $multiDelBtn = $('#multiDelBtn');
//批量还原
var $multiRestoreBtn = $('#multiRestoreBtn');
$(function () {
    $table.bootstrapTable({
        url: '/questionnaireManage/listTemporaryDeleteQesPaper',
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortName: ['questionnaireTitle', 'questionnaireSubtitle', 'originSource'],
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
        searchOnEnterKey: true,

        toolbar: '#tableToolbar',

        showColumns: true,
        showRefresh: true,
        showToggle: true,
        showPaginationSwitch: true,

        minimumCountColumns: 3,
        detailView: true,
        detailFormatter: detailFormatter,
        columns: [{
            checkbox: true,
            clickToSelect: true
        }, {
            field: 'questionnaireId',
            title: '问卷编号',
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '问卷标题',
            sortable: true
        }, {
            field: 'questionnaireSubtitle',
            title: '问卷副标题',
            sortable: true
        }, {
            field: 'originSource',
            title: '原始位置',
            sortable: true
        }, {
            title: '操作',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});

var restoreUrl = '/questionnaireManage/restoreMultiQuestionnaire';
var deleteForeverUrl = '/questionnaireManage/delForeverMultiQuestionnaire';
//单份处理
window.operateEvents = {
    //永久删除
    'click .remove': function (e, value, row, index) {
        layerConfirm('确认永久删除问卷吗?', row, deleteForeverUrl);
    },
    //恢复问卷
    'click .restore': function (e, value, row, index) {
        layerConfirm("确认恢复到【" + row.originSource + "】中吗?", row, restoreUrl);
    }
};

//批量永久删除处理
$multiDelBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerMsg("确认永久删除问卷吗?", ids, deleteForeverUrl);
});

//批量恢复处理
$multiRestoreBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerMsg("确认恢复到个人问卷库中吗?", ids, restoreUrl);
});

/*检查是否选中一种问卷*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作的问卷', {icon: 5});
        return false;
    }
    return true;
}

/*弹窗层*/
function layerMsg(confirmText, ids, url) {
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

function layerConfirm(confirmText, row, url) {
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
 * 解析回复数据包
 * @param data
 * @param url
 * @param questionnaireIds
 */
function analyzeResponse(data, url, questionnaireIds) {
    var responsePkt = JSON.parse(data);
    if (responsePkt.code === 200) {
        switch (url) {
            case restoreUrl: //恢复回收站问卷
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'questionnaireId',
                        values: questionnaireIds
                    });
                    layer.msg('问卷已经恢复到问卷库中！', {icon: 1});
                }
                dealGlobalError(responsePkt);
                break;
            case deleteForeverUrl://永久删除问卷
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'questionnaireId',
                        values: questionnaireIds
                    });
                    layer.msg('问卷已经永久删除！', {icon: 1});
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

//操作按钮格式设置
function operateFormatter(value, row, index) {
    return [
        '<a class="remove btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="永久删除问卷">',
        '<i class="glyphicon glyphicon-remove"></i> 删除',
        '</a>',
        '<a class="restore btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="恢复问卷到我的问卷中">',
        '<i class="glyphicon glyphicon-retweet"></i> 恢复',
        '</a>'
    ].join('');
}

//详细信息格式
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><i>问卷描述:</i> ' + row.questionnaireDescription + '</p>');
    return html.join('');
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
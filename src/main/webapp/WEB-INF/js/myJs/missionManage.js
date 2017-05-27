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
        sortName: ['missionId', 'questionnaireTitle', 'minSubmitCount','missionStartDate','missionFinalDate','createUser'],
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

        columns: [ {
            field: 'missionId',
            title: '任务ID',
            align: 'center',
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '问卷标题',
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
            field: 'createUser',
            title: '发布者',
            align: 'center',
            sortable: true
        }, {
            title: '操作',
            width: 100,
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});


var checkDataUrl = '';
//单份处理
window.operateEvents = {
    //提醒
    'click .check': function (e, value, row, index) {
        // layer.alert(row.missionId + "||" + row.questionnaireId);
        checkDataUrl = '/notice/getCreateNoticeForMission?missionId=' + row.missionId + '&qesId=' + row.questionnaireId;
        layerMsg('提醒', row, checkDataUrl);
    }
};
/*弹窗层*/
function layerMsg(confirmText, ids, url) {
    layer.open({
        type: 2,
        title: '任务提醒',
        maxmin: true,
        content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        area: ['80%', '90%'],
        resize: true
    });
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
/*检查是否选中一种问卷*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作的问卷', {icon: 5});
        return false;
    }
    return true;
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
        '<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="提醒">',
        '<i class="glyphicon glyphicon-check"></i> 提醒',
        '</a>'
    ].join('');
}
//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
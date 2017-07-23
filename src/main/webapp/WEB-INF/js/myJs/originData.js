/**
 * Created by Administrator on 2017/5/3.
 */
var $table = $('#primaryDataTable');

$(function () {
    $table.bootstrapTable({
        url: '/resultAnalysis/listPrimaryData',
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortable: true,
        sortName: ['missionId', 'missionDescription', 'questionnaireTitle', 'questionnaireCount'],
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
            title: '调查编号',
            align: 'center',
            width: 100,
            sortable: true
        }, {
            field: 'missionDescription',
            title: '调查描述',
            align: 'left',
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '问卷标题',
            align: 'left',
            sortable: true
        }, {
            field: 'questionnaireCount',
            title: '问卷完成量',
            align: 'center',
            width: 200,
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
//单份处理
window.operateEvents = {
    //查看
    'click .check': function (e, value, row, index) {
        checkDataUrl = '/resultAnalysis/getPrimaryDataTwo?missionId=' + row.missionId + '&qesId=' + row.questionnaireId;
        layerMsg('查看', row, checkDataUrl);
    },
    //导出
    'click .export': function (e, value, row, index) {
        var exportDataUrl = '/export/exportTxtData2Excel?missionId=' + row.missionId + '&qesId=' + row.questionnaireId;
        window.location.href = exportDataUrl;
    }
};

/*弹窗层*/
function layerMsg(confirmText, ids, url) {
    layer.open({
        type: 2,
        title: '详细内容',
        maxmin: true,
        content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        area: ['100%', '100%'],
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
        '<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="查看原始数据">',
        '<i class="glyphicon glyphicon-check"></i> 查看',
        '</a>',
        '<a class="export btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="导出原始数据">',
        '<i class="glyphicon glyphicon-export"></i>导出',
        '</a>'
    ].join('');
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
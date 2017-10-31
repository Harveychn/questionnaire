/**
 * Created by Administrator on 2017/5/3.
 */
var $table = $('#primaryDataTwoTable');

$(function () {
    $table.bootstrapTable({
        url: '/resultAnalysis/listAnswerPaper',
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortName: ['answerPaperId', 'submitUserTel', 'fillAnswerTime', 'submitTime'],
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
        // detailView: true,
        // detailFormatter: detailFormatter,
        columns: [{
            field: 'answerPaperId',
            title: '答卷编号',
            sortable: true
        }, {
            field: 'submitUserTel',
            title: '交卷人账户',
            sortable: true
        }, {
            field: 'fillAnswerTime',
            title: '最后填写时间',
            sortable: true
        }, {
            field: 'submitTime',
            title: '提交答卷时间',
            sortable: true
        }, {
            title: '操作',
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
        checkDataUrl = '/resultAnalysis/getAnswerPaperInfo?answerPaperId=' + row.answerPaperId;
        // layerMsg('查看', row, checkDataUrl);
        window.open(checkDataUrl);
    }
};
function layerMsg(confirmText, ids, url) {
    layer.open({
        type: 2,
        title: '详细内容',
        maxmin: true,
        content: url, //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        area: ['80%', '80%'],
        resize: true
    });
}

// //详细信息格式
// function detailFormatter(index, row) {
//     var html = [];
//     html.push('<p><i>答卷编号:</i> ' + row.answerPaperId + '</p>');
//     // html.push('<p><i>公告对象单位:</i></p>');
//     return html.join('');
// }

//操作按钮格式设置
function operateFormatter(value, row, checkDataUrl) {
    return [
        '<a class="check btn btn-sm btn-link" href="javascript:void(0)"  data-toggle="tooltip" title="查看">',
        '<i class="glyphicon glyphicon-check"></i> 查看',
        '</a>'
    ].join('');
}
//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.layui-collapse').outerHeight(true);
}
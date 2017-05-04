/**
 * Created by Administrator on 2017/5/3.
 */
var $table = $('#primaryDataTable');

$(function () {
    $table.bootstrapTable({
        //url: './test.json',
        method: 'get',
        //dataType: 'json',
        cache: false,
        data: data,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',

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

        showColumns: false,
        showRefresh: false,
        showToggle: false,
        showPaginationSwitch: false,

        minimumCountColumns: 3,

        columns: [{
            checkbox: true,
            clickToSelect: true
        }, {
            field: 'questionnaireId',
            title: '问卷ID',
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
            title: '操作',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});
var data = [
    {
	"questionnaireId":"1",
	"questionnaireTitle":"11",
	"questionnaireSubtitle":"111"


},{
	"questionnaireId":"2",
	"questionnaireTitle":"22",
	"questionnaireSubtitle":"222"


},
{
	"questionnaireId":"3",
	"questionnaireTitle":"33",
	"questionnaireSubtitle":"333"


}
];

var checkDataUrl = '';
//单份处理
window.operateEvents = {
    //查看
    'click .check': function (e, value, row, index) {
        layerMsg('查看', row, checkDataUrl);
    }
};
/*弹窗层*/
function layerMsg(confirmText, ids, url) {
    layer.open({
		  type: 2, 
		  title :'详细内容',
		  maxmin: true,
		  content: 'primaryData_two.html', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  area: ['800px', '600px'],
		  resize:true
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
        '<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="查看">',
        '<i class="glyphicon glyphicon-check"></i> 查看',
        '</a>'
    ].join('');
}
//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
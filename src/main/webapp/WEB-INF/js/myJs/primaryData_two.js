/**
 * Created by Administrator on 2017/5/3.
 */
var $table = $('#primaryData_twoTable');

$(function() {
	$table.bootstrapTable({
		//url: './test.json',
		method: 'get',
		//dataType: 'json',
		cache: false,
		data: data, //可去掉

		striped: true,

		height: getHeight(),

		pagination: true,
		paginationLoop: true,
		pageList: [10, 20, 30],
		paginationPreText: '上一页',
		paginationNextText: '下一页',
		sidePagination: 'client',

		search: false,

		minimumCountColumns: 3,

		columns: [{
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

//data可删除
var data = [{
		"questionnaireId": "1",
		"questionnaireTitle": "11",
		"questionnaireSubtitle": "111"

	}, {
		"questionnaireId": "2",
		"questionnaireTitle": "22",
		"questionnaireSubtitle": "222"

	},
	{
		"questionnaireId": "3",
		"questionnaireTitle": "33",
		"questionnaireSubtitle": "333"

	},
	{
		"questionnaireId": "1",
		"questionnaireTitle": "11",
		"questionnaireSubtitle": "111"

	}, {
		"questionnaireId": "2",
		"questionnaireTitle": "22",
		"questionnaireSubtitle": "222"

	},
	{
		"questionnaireId": "3",
		"questionnaireTitle": "33",
		"questionnaireSubtitle": "333"

	},
	{
		"questionnaireId": "3",
		"questionnaireTitle": "33",
		"questionnaireSubtitle": "333"

	},
	{
		"questionnaireId": "1",
		"questionnaireTitle": "11",
		"questionnaireSubtitle": "111"

	}
];

var checkDataUrl = '';
//单份处理
window.operateEvents = {
	//查看
	'click .check': function(e, value, row, index) {
		layerMsg('查看', row, checkDataUrl);
	}
};

//操作按钮格式设置
function operateFormatter(value, row, index) {
	return [
		'<a class="check btn btn-sm btn-link" href="../../view/resultAnalysis/primaryData_dataShow.html " target="_Blank" data-toggle="tooltip" title="查看">',
		'<i class="glyphicon glyphicon-check"></i> 查看',
		'</a>'
	].join('');
}
//获取屏幕高度
function getHeight() {
	return $(window).height() - $('.layui-collapse').outerHeight(true);
}
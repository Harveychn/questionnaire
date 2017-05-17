/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/29.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#peopleSearchTable');
//批量永久删除按钮
var $multiDelBtn = $('#multiDelBtn');

$(function () {
    $table.bootstrapTable({
        //url: '/questionnaireManage/listTemporaryDeleteQesPaper',
        method: 'post',
        dataType: 'json',
        cache: false,
		data:data,//可删除
        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortName: ['questionnaireTitle', 'questionnaireSubtitle'],
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
        showPaginationSwitch: false,

        minimumCountColumns: 3,
        //detailView: true,详细列表
        //detailFormatter: detailFormatter,
        columns: [{
            checkbox: true,
            clickToSelect: true
        }, {
            field: 'questionnaireId',
            title: '人员ID',
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '姓名',
            sortable: true
        }, {
            field: 'questionnaireSubtitle',
            title: '角色',
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

	}
];

var deleteForeverUrl = '';
var checkDataUrl = '';

//单份处理
window.operateEvents = {
    //永久删除
    'click .remove': function (e, value, row, index) {
        layerConfirm('确认永久删除吗?', row, deleteForeverUrl);
    },
    //查看
    'click .check': function (e, value, row, index) {
        layerMsg('编辑', row, checkDataUrl);
    }
    
};

//批量永久删除处理
$multiDelBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirm("确认永久删除吗?", ids, deleteForeverUrl);
});

/*弹窗层*/
function layerMsg(confirmText, ids, url) {
    layer.open({
		  type: 2, 
		  title :'编辑内容',
		  maxmin: true,
		  content: 'people_Search_two.html', //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
		  area: ['800px', '600px'],
		  resize:true
		});
}

/*检查是否选中一种问卷*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作', {icon: 5});
        return false;
    }
    return true;
}

/*弹窗层*/
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
            
            case deleteForeverUrl://永久删除问卷
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'questionnaireId',
                        values: questionnaireIds
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

//获取批量选中的id
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.questionnaireId;
    });
}

//操作按钮格式设置
function operateFormatter(value, row, index) {
    return [
        '<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="编辑">',
        '<i class="glyphicon glyphicon-check"></i> 编辑',
        '</a>',
        '<a class="remove btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="永久删除问卷">',
        '<i class="glyphicon glyphicon-remove"></i> 删除',
        '</a>',
        
    ].join('');
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
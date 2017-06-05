/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/30.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#noticeInfoTable');
//新建公告按钮
var $newNoticeBtn = $('#newNotice');
//新建公告跳转
$newNoticeBtn.on('click', function () {
    window.location.href = "/notice/getCreateNotice?newNotice";
});

$(function () {
    $table.bootstrapTable({
        url: '/notice/listMyNotice',
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
//            clickToSelect: true,
        undefinedText: '--',
        sortName: ['noticeTitle', 'noticeCreateTime', 'noticeLaunchDate'],
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
            field: 'noticeId',
            title: '公告编号',
            visible: false
        }, {
            field: 'noticeTitle',
            title: '公告标题',
            sortable: true
        }, {
            field: 'noticeCreateTime',
            title: '创建时间',
            sortable: true
        }, {
            field: 'noticeLaunchDate',
            title: '发布时间',
            sortable: true
        }, {
            title: '操作',
            align: 'center',
            events: operateEvents,
            formatter: operateFormatter
        }]
    });
});

var deleteNoticeUrl = '/notice/deleteNotice';
//单份处理
window.operateEvents = {
    //永久删除
    'click .remove': function (e, value, row, index) {
        layerConfirm('确认删除公告吗?', row, deleteNoticeUrl);
    },
    //查看公告
    'click .scanNotice': function (e, value, row, index) {
        //查看公告处理
        // layer.msg('未开放功能！', {icon: 4});
        var unitString = '';
        for (var i = 0; i < row.noticeUnitName.length; i++) {
            unitString += '[' + row.noticeUnitName[i] + ']';
        }
        layer.open({
            type: 1,
            title: false, //不显示标题栏
            closeBtn: false,
            area: ['75%', '60%'],
            shade: 0.8,
            id: 'noticeItemLayer', //设定一个id，防止重复弹出
            resize: false,
            btn: ['知道了'],
            btnAlign: 'c',
            moveType: 1, //拖拽模式，0或者1
            content: '<div style="padding: 50px; line-height: 22px; background-color: #fff; color: #000; font-weight: 300; font-size:18px">' +
            '<div style="font-size:24px; text-align:center;">' + row.noticeTitle + '</div>' + '<br>' +
            '<div class="row"><label class="col-sm-2 col-md-2">预计发布时间：</label>' + '<div class="col-sm-10 col-md-10">' + row.noticeLaunchDate + '</div>'+ '</div>'+'<br>'+
            '<div class="row"><label class="col-sm-2 col-md-2">发布对象单位：</label>' + '<div class="col-sm-10 col-md-10">' + unitString + '</div>'+ '</div>'+'<br>'+
            '<div class="row"><label class="col-sm-2 col-md-2">公告内容：</label>' + '<div class="col-sm-10 col-md-10">' + '<div style="text-indent:2em;">' + row.noticeContext + '</div>' + '</div>'+ '</div>'+'<br>'+
            '</div>'
        });


    }
};

function layerConfirm(confirmText, row, url) {
    layer.confirm(confirmText, {
            icon: 3,
            btn: ['确定', '取消']
        },
        function (index) {
            var ids = [];
            ids.push(row.noticeId);
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
 * @param noticeIds
 * @param url
 */
function accessServer(noticeIds, url) {
    $.ajax({
        url: url,
        type: 'post',
        data: {noticeIdArray: noticeIds},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            analyzeResponse(data, url, noticeIds);
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
 * @param noticeIds 必须为数组
 */
function analyzeResponse(data, url, noticeIds) {
    var responsePkt = JSON.parse(data);
    if (responsePkt.code === 200) {
        switch (url) {
            case deleteNoticeUrl://删除公告
                if (responsePkt.code === 200) {
                    $table.bootstrapTable('remove', {
                        field: 'noticeId',
                        values: noticeIds
                    });
                    layer.msg('公告已删除！', {icon: 1});
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
        return row.noticeId;
    });
}

//操作按钮格式设置
function operateFormatter(value, row, index) {
    return [
        '<a class="scanNotice btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="查看公告">',
        '<i class="glyphicon glyphicon-fullscreen"></i> 查看',
        '</a>',
        '<a class="remove btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="删除公告">',
        '<i class="glyphicon glyphicon-remove"></i> 删除',
        '</a>'
    ].join('');
}

//详细信息格式
function detailFormatter(index, row) {
    var html = [];
    html.push('<p><i>公告编号:</i> ' + row.noticeId + '</p>');
    html.push('<p><i>公告对象单位:</i></p><ol>');
    for (var index = 0; index < row.noticeUnitName.length; index++) {
        html.push('<li>' + row.noticeUnitName[index] + '</li>');
    }
    html.push('</ol>');
    return html.join('');
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
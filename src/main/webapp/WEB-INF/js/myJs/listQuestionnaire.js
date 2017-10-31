/**
 * questionnaire
 * Created by 郑晓辉 on 2017/5/1.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#myQesPaperTable');

var tableDataUrl = '/questionnaireManage/listMyQuestionnaire';

//批量模版按钮
var $multiTemplateBtn = $('#multiTemplateBtn');
//批量共享按钮
var $multiShareBtn = $('#multiShareBtn');
//批量移除按钮
var $multiDelBtn = $('#multiDelBtn');

//批量模板化url
var templateUrl = '/questionnaireManage/templateMultiQuestionnaire';
//批量暂时删除url
var delTemporaryUrl = '/questionnaireManage/delTemporaryMultiQuestionnaire';
//批量共享url
var shareUrl = '/questionnaireManage/shareMultiQuestionnaire';

$(function () {
    $table.bootstrapTable({
        url: tableDataUrl,
        method: 'post',
        dataType: 'json',
        cache: false,

        striped: true,
        undefinedText: '--',
        sortName: ['questionnaireTitle', 'questionnaireSubtitle', 'questionnaireCreateDate'],
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
            checkbox: true,
            clickToSelect: true
        }, {
            field: 'questionnaireId',
            title: '问卷编号',
            visible: false,
            sortable: true
        }, {
            field: 'questionnaireTitle',
            title: '问卷标题',
            sortable: true
        }, {
            field: 'questionnaireSubtitle',
            title: '问卷副标题',
            sortable: true,
            visible: false
        }, {
            field: 'questionnaireCreateDate',
            title: '问卷创建时间',
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
    //查看问卷
    'click .scanPaper': function (e, value, row, index) {
        //查看问卷
        var browseQesUrl = '/questionnaireManage/displayQuestionnaire';
        browseQesUrl += '/' + row.questionnaireId;
        browseQesPaper(browseQesUrl);
    },
    //删除问卷
    'click .remove': function (e, value, row, index) {
        layerConfirmSingle('确认移除问卷吗?', row, delTemporaryUrl);
    },
    //发布问卷
    'click .launchPaper': function (e, value, row, index) {
        window.location.href = '/researchManage/createResearchView?questionnaireSelect=' + row.questionnaireId;
    },
    //继续编辑问卷
    'click .continueEdit': function (e, value, row, index) {
        // layer.msg('模块未发布', {icon: 4});
        window.location.href = '/QesManageRest/getEditQesPaperView/' + row.questionnaireId;
    },
    //模板化问卷
    'click .templatePaper': function (e, value, row, index) {
        layerConfirmSingle('确认问卷到个人模板库？', row, templateUrl);
    },
    //分享问卷
    'click .sharePaper': function (e, value, row, index) {
        layerConfirmSingle('确认共享问卷到公共模板库？', row, shareUrl);
    }
};

//操作按钮格式设置
function operateFormatter(value, row, index) {
    var htmlElement = [];
    htmlElement.push('<a class="scanPaper btn btn-sm btn-link" href="javascript:void(0)" ' +
        'data-toggle="tooltip" title="查看问卷">',
        '<i class="glyphicon glyphicon-folder-open"></i> 查看');
    htmlElement.push('<a class="remove btn btn-sm btn-link" href="javascript:void(0)" ' +
        'data-toggle="tooltip" title="移除问卷">',
        '<i class="glyphicon glyphicon-remove"></i> 移除',
        '</a>');
    if (row.done === false) {
        htmlElement.push('<a class="continueEdit btn btn-sm btn-link" href="javascript:void(0)" ' +
            'data-toggle="tooltip" title="继续编辑">',
            '<i class="glyphicon glyphicon-edit"></i> 继续',
            '</a>');
    }
    if (row.done === true) {
        htmlElement.push('<a class="launchPaper btn btn-sm btn-link" href="javascript:void(0)" ' +
            'data-toggle="tooltip" title="发布问卷">',
            '<i class="glyphicon glyphicon-eject"></i> 发布',
            '</a>');
        if (row.template === false) {
            htmlElement.push('<a class="templatePaper btn btn-sm btn-link" href="javascript:void(0)" ' +
                'data-toggle="tooltip" title="模板化问卷">',
                '<i class="glyphicon glyphicon-briefcase"></i> 模板化',
                '</a>');
        }
        if (row.share === false) {
            htmlElement.push('<a class="sharePaper btn btn-sm btn-link" href="javascript:void(0)" ' +
                'data-toggle="tooltip" title="分享问卷">',
                '<i class="glyphicon glyphicon-share"></i> 分享',
                '</a>');
        }
    }
    return htmlElement.join('');
}
/**
 * 批量处理开始
 */
//批量模版化问卷
$multiTemplateBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti('确认批量添加问卷到个人模板库？', ids, templateUrl);
});
//批量共享问卷
$multiShareBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti('确实批量共享问卷？', ids, shareUrl);
});
//批量删除（回收）处理
$multiDelBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti('确认批量移除问卷吗（可以在“回收问卷”中恢复这些问卷）', ids, delTemporaryUrl);
});
/**
 * 批量处理结束
 */

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
    // layer.full(layer.open({
    //     title: '   ',
    //     type: 2,
    //     content: urlWithId,
    //     area: ['80%', '80%'],
    //     maxmin: true,
    //     closeBtn: 1
    // }));
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
                    layer.msg('问卷已经移除！（可以在“回收问卷”中恢复）', {icon: 1});
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
    html.push('<p><i>问卷编号：</i>' + row.questionnaireId + '</p>');
    html.push('<p><i>问卷副标题：</i>' + row.questionnaireSubtitle + '</p>');
    html.push('<p><i>问卷描述:</i> ' + row.questionnaireDescription + '</p>');
    return html.join('');
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
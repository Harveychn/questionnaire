/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/29.
 * Contact : zxh980278090@gmail.com
 */
var $table = $('#roleAuthorityTable');
var tableDataUrl = '/userManage/getRoleAuthority';
//批量永久删除按钮
var $multiDelBtn = $('#multiDelBtn');
//批量启用按钮
var $multiEnableBtn = $('#multiEnableBtn');
//批量禁用按钮
var $multiDisableBtn = $('#multiDisableBtn');

var deleteUserAccountUrl = '/userManage/multiDeleteUserAccount';
var enableUserAccountUrl = '/userManage/multiEnableUserAccount';
var disableUserAccountUrl = '/userManage/multiDisableUserAccount';

$(function () {
    $table.bootstrapTable({
        url: tableDataUrl,
        method: 'get',
        dataType: 'json',
        cache: false,
        striped: true,
        undefinedText: '--',
        sortName: ['account', 'realName', 'userRole', 'isAccountValid'],
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
        showPaginationSwitch: false,

        minimumCountColumns: 3,
        //detailView: true,详细列表
        //detailFormatter: detailFormatter,
        columns: [{
            checkbox: true,
            clickToSelect: true
        }, {
            field: 'account',
            title: '用户账户',
            sortable: true
        }, {
            field: 'realName',
            title: '姓名',
            sortable: true
        }, {
            field: 'userRole',
            title: '角色',
            sortable: true
        }, {
            field: 'isAccountValid',
            title: '账户状态'
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
    //永久删除
    'click .remove': function (e, value, row, index) {
        layerConfirmSingle('确认永久删除账户吗?', row, deleteUserAccountUrl);
    },
    //编辑用户角色
    'click .check': function (e, value, row, index) {
        layer.open({
            type: 2,
            title: '编辑内容',
            maxmin: true,
            content: '/userManage/getRoleAuthorityManageView' +
            '?userAccount=' + row.account
            + '&accountOwner=' + row.realName
            + '&userRole=' + row.userRole, //传递用户账户以及真实姓名
            area: ['80%', '80%'],
            resize: true
        });
    },
    //启用账户
    'click .enableAccount': function (e, value, row, index) {
        layerConfirmSingle('确认启用账户吗？', row, enableUserAccountUrl);
    },
    //禁用账户
    'click .disableAccount': function (e, value, row, index) {
        layerConfirmSingle('确认禁用账户吗？', row, disableUserAccountUrl);
    }
};

function refreshTable() {
    $table.bootstrapTable('refresh', {});
}

//操作按钮格式设置
function operateFormatter(value, row, index) {
    var htmlElement = [];
    if (row.isAccountValid === '有效') {
        htmlElement.push('<a class="check btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="编辑">',
            '<i class="glyphicon glyphicon-check"></i> 编辑',
            '</a>');
        htmlElement.push('<a class="disableAccount btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="永久删除问卷">',
            '<i class="glyphicon glyphicon-ban-circle"></i> 禁用',
            '</a>');
    }
    if (row.isAccountValid === '无效') {
        htmlElement.push('<a class="enableAccount btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="永久删除问卷">',
            '<i class="glyphicon glyphicon-ok-sign"></i> 启用',
            '</a>');
    }
    htmlElement.push('<a class="remove btn btn-sm btn-link" href="javascript:void(0)" data-toggle="tooltip" title="永久删除问卷">',
        '<i class="glyphicon glyphicon-remove"></i> 删除',
        '</a>');
    return htmlElement.join('');
}

//批量永久删除处理
$multiDelBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti("确认删除选中的账户吗?", ids, deleteUserAccountUrl);
});
//批量启用账户
$multiEnableBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti("确认启用选中的账户吗?", ids, enableUserAccountUrl);
});
//批量禁用账户
$multiDisableBtn.on('click', function () {
    var ids = getIdSelections();
    if (!checkIsSelectedOne(ids)) {
        return;
    }
    layerConfirmMulti("确认禁用选中的账户吗?", ids, disableUserAccountUrl);
});

//单个操作确认弹窗
function layerConfirmSingle(confirmText, row, url) {
    layer.confirm(confirmText, {
            icon: 3,
            btn: ['确定', '取消']
        },
        function (index) {
            var ids = [];
            ids.push(row.account);
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
    );
}

/*检查是否选中*/
function checkIsSelectedOne(ids) {
    if (0 === ids.length) {
        layer.msg('还未选择操作!', {icon: 5});
        return false;
    }
    return true;
}

/**
 * 异步加载服务器
 * @param userAccountArray
 * @param url
 */
function accessServer(userAccountArray, url) {
    $.ajax({
        url: url,
        type: 'post',
        data: {userAccountArray: userAccountArray},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            analyzeResponse(data, url, userAccountArray);
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
 * @param accounts
 */
function analyzeResponse(data, url, accounts) {
    var responsePkt = JSON.parse(data);
    if (responsePkt.code === 200) {
        switch (url) {
            case deleteUserAccountUrl://永久删除账户
                if (responsePkt.code === 200) {
                    var failDeleteAccounts = responsePkt.data;
                    if (failDeleteAccounts.length <= 0) {
                        $table.bootstrapTable('remove', {
                            field: 'account',
                            values: accounts
                        });
                        layer.msg('已经永久删除！', {icon: 1});
                    } else {
                        for (var i = 0; i < failDeleteAccounts.length; i++) {
                            removeByValue(accounts, failDeleteAccounts[i]);
                        }
                        $table.bootstrapTable('remove', {
                            field: 'account',
                            values: accounts
                        });
                        layer.msg('已经永久删除' + accounts.length + '个账户\n\n！'
                            + '其中账户：' + failDeleteAccounts + '删除存在系统隐患，可以试试禁用账户!',
                            {icon: 1});
                    }
                }
                dealGlobalError(responsePkt);
                break;
            case enableUserAccountUrl://启用用户账户
                if (responsePkt.code === 200) {
                    layer.msg('已经启用账户！', {icon: 1});
                    //刷新表格数据
                    $table.bootstrapTable('refresh', {});
                }
                break;
            case disableUserAccountUrl://禁用用户账户
                if (responsePkt.code === 200) {
                    layer.msg('已经禁用账户！', {icon: 1});
                    //刷新表格数据
                    $table.bootstrapTable('refresh', {});
                }
                break;
            default:
                return;
        }
    }
}
/**
 * 删除 arr 数组中 存在 val 值的元素
 * @param arr
 * @param val
 */
function removeByValue(arr, val) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] === val) {
            arr.splice(i, 1);
            break;
        }
    }
}

//获取批量选中的id
function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.account;
    });
}

//获取屏幕高度
function getHeight() {
    return $(window).height() - $('.panel-body').outerHeight(true);
}
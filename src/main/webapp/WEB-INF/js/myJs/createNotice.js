/**
 * questionnaire
 * Created by 郑晓辉 on 2017/7/10.
 * Contact : zxh980278090@gmail.com
 */

var isSubmitted = false;

$(function () {
    isSubmitted = false;
    if (window.location.search === '?newNotice') {
        var $buttonGroup = $('#btnGroup');
        $buttonGroup.attr('class', 'col-md-offset-4');
        $buttonGroup.prepend('<button class="btn btn-lg btn-link" onclick="window.history.back();">返回上一级</button>');
    }
    var unitOptions = '';
    var $noticeUnit = $('#noticeUnit');
    $.ajax({
        url: '/unit/listUnitInfo',
        type: 'post',
        dataType: 'json',
        traditional: true,
        success: function (data) {
            for (var index = 0; index < data.length; index++) {
                unitOptions += '<option title="' + data[index].unitName + '" ' +
                    'value="' + data[index].unitId + '">' + data[index].unitName +
                    '·························'
                    + data[index].unitProvince + ' - ' + data[index].unitCity + '</option>';
            }
            $($noticeUnit).append(unitOptions);
            $($noticeUnit).selectpicker({
                    multipleSeparator: ' || ',
                    actionsBox: true,
                    liveSearch: true,
                    noneSelectedText: '未选择单位信息',
                    selectAllText: '全部单位',
                    liveSearchPlaceholder: '查询单位',
                    deselectAllText: '重新选择',
                    selectedTextFormat: 'count > 5',
                    countSelectedText: function () {
                        return '选中了  {0}   个单位   (单位总数 {1})';
                    }
                },
                'refresh', 'render'
            );
        },
        error: function (data) {
            alert(data.message);
        }
    });
    layui.use(['laydate'], function () {
        var laydate = layui.laydate;

        var start = {
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-06-16 23:59:59',
            istime: true,
            isclear: true, //是否显示清空
            istoday: true, //是否显示今天
            issure: true, //是否显示确认
            festival: true, //是否显示节日
            fixed: false, //是否固定在可视区域
            zIndex: 99999999 //css z-index
        };

        document.getElementById('launchDate').onclick = function () {
            start.elem = this;
            laydate(start);
        };
    });
});
/*发布公告*/
$('#launchBtn').on('click', function () {
    if (isSubmitted) {
        return;
    }
    layui.use(['layer', 'laydate'], function () {
        var layer = layui.layer;
        var laydate = layui.laydate;

        var noticeTitle = $('#noticeTitle').val();
        if (noticeTitle.match(/^\s*$/)) {
            layer.msg('请填写公告标题！', function () {
            });
            return;
        }

        //获取选中的单位ID
        var unitIds = [];
        $('#noticeUnit').find('option:selected').each(function () {
            unitIds.push($(this).val());
        });
        if (unitIds.length <= 0) {
            layer.msg('未选择公告单位！', function () {
            });
            return;
        }

        var noticeContent = $('#noticeContent').val();

        if (noticeContent.match(/^\s*$/)) {
            layer.msg('请填写公告内容！', function () {
            });
            return;
        }

        var launchDate = $('#launchDate').val();
        if (launchDate.match(/^\s*$/)) {
            layer.confirm('您未设置发布时间，立即发布吗！', {
                icon: 3,
                btn: ['确定发布', '返回修改']
            }, function (index) {
                layer.close(index);
                submitJsonData(laydate.now(), unitIds);
            }, function (index) {
                layer.close(index);
            });
        } else {
            submitJsonData(launchDate, unitIds);
        }
    });
});
/*提交json格式的数据*/
function submitJsonData(launchDate, unitIds) {
    var noticeInfo = {};
    noticeInfo.noticeTitle = $('#noticeTitle').val();
    noticeInfo.noticeContent = $('#noticeContent').val();
    noticeInfo.launchDate = new Date(launchDate);
    noticeInfo.unitObjectIds = unitIds;
    var url = '/notice/createNotice';
    if (isSubmitted) {
        return;
    }
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        type: 'post',
        dataType: 'json',
        async: false,
        data: JSON.stringify(noticeInfo),
        success: function (data) {
            if (200 === data.code) {
                isSubmitted = true;
                layer.msg("创建公告成功!", {
                    icon: 1,
                    time: 2000,
                    shade: 0.5,
                    closeBtn: 1
                }, function () {
                    window.location.href = "/notice/getMyNoticeView";
                });
            }
            dealGlobalError(data);
        },
        error: function (data) {
            alert(data);
        }
    });
}
/*重置按钮*/
$('#resetBtn').on('click', function () {
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.confirm('确认重置？', {
                icon: 3,
                btn: ['确定', '取消']
            },
            function (index) {
                $('#noticeTitle').val(null);
                $('#noticeUnit').selectpicker('deselectAll');
                $('#noticeContent').val(null);
                $('#launchDate').val(null);
                layer.close(index);
            },
            function () {
                layer.msg('取消成功！');
            }
        )
    });
});
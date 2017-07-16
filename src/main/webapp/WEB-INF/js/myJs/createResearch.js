/**
 * questionnaire
 * Created by 郑晓辉 on 2017/7/10.
 * Contact : zxh980278090@gmail.com
 */

var isSubmitted = false;
$(function () {
    isSubmitted = false;

    var qesItemOptions = '';
    var $questionnaireItem = $('select[name="questionnaireSelect"]');
    $.ajax({
        url: '/researchManage/listQuestionnaireInfo',
        type: 'post',
        dataType: 'json',
        async: false,
        traditional: true,
        success: function (data) {
            for (var index = 0; index < data.length; index++) {
                qesItemOptions += '<option title="' + data[index].qesTitle + '" value="' + data[index].qesId + '">' +
                    data[index].qesTitle + '························' + data[index].createDate +
                    '[' + data[index].isTemplate + ']</option>';
            }
            $questionnaireItem.append(qesItemOptions);
            $($questionnaireItem).selectpicker({
                    liveSearch: true,
                    noneSelectedText: '未选择发布问卷',
                    liveSearchPlaceholder: '查询问卷'
                },
                'refresh', 'render'
            );
        },
        error: function () {
        }
    });

    var unitOptions = '';
    var $researchUnit = $('#researchUnit');
    $.ajax({
        url: '/unit/listUnitInfo',
        type: 'post',
        dataType: 'json',
        async: false,
        traditional: true,
        success: function (data) {
            for (var index = 0; index < data.length; index++) {
                unitOptions += '<option title="' + data[index].unitName + '" ' +
                    'value="' + data[index].unitId + '">' + data[index].unitName +
                    '·························' +
                    data[index].unitProvince + ' - ' + data[index].unitCity + '</option>';
            }
            $($researchUnit).append(unitOptions);
            $($researchUnit).selectpicker({
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
            console.error(data);
        }
    });

    var selectedQesId = window.location.search.split('=').pop();
    if ('' !== selectedQesId) {
        $questionnaireItem.find('option[value=' + selectedQesId + ']').attr("selected", true);
        $questionnaireItem.selectpicker('render');
        var $buttonGroup = $('#buttonGroup');
        $buttonGroup.attr('class', 'col-md-offset-4');
        $buttonGroup.prepend('<button class="btn btn-lg btn-link" onclick="window.history.back();">返回上一级</button>');
    }

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
            zIndex: 99999999, //css z-index
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            format: 'YYYY-MM-DD hh:mm:ss',
            max: '2099-06-16 23:59:59',
            istime: true,
            isclear: true, //是否显示清空
            istoday: true, //是否显示今天
            issure: true, //是否显示确认
            festival: true, //是否显示节日
            fixed: false, //是否固定在可视区域
            zIndex: 99999999, //css z-index
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        document.getElementById('begindate').onclick = function () {
            start.elem = this;
            laydate(start);
        };
        document.getElementById('enddate').onclick = function () {
            end.elem = this;
            laydate(end);
        };
    });
    if ('' !== window.location.search) {
        $('#pageName').html('发布问卷');
    }
});
/*提交数据*/
$('#launchMissionBtn').on('click', function () {
    if (isSubmitted) {
        return;
    }
    //获取选中的单位ID
    var unitIds = [];
    $('#researchUnit').find('option:selected').each(function () {
        unitIds.push($(this).val());
    });
    var missionDescription = $('#missionDescription').val();
    var startDate = $('#begindate').val();
    var endDate = $('#enddate').val();
    //获取选中的问卷ID
    var qesItemInfoArray = [];
    var qesItem = {};
    $('select[name="questionnaireSelect"]').find('option:selected').each(function () {
        qesItem.questionnaireId = ($(this).val());
        qesItem.minSubmit = ($(this).closest('div[class="col-md-offset-0 col-md-12"]').find('input[name="minSubmit"]').val());
        qesItemInfoArray.push(qesItem);
    });
    layui.use('layer', function () {
        var layer = layui.layer;
        //校验调查单位数量不能少于一个
        if (unitIds.length <= 0) {
            layer.msg('调查单位还未选择！', function () {
            });
            return;
        }
        //开始时间、结束时间校验
        if (startDate.match(/^\s*$/)) {
            layer.msg('开始时间未设置！', function () {
            });
            return;
        }
        if (endDate.match(/^\s*$/)) {
            layer.msg('结束时间未设置！', function () {
            });
            return;
        }
        //调查问卷选择校验
        if (qesItemInfoArray.length <= 0) {
            layer.msg('还未选择问卷！', function () {
            });
            return;
        }
        for (var i = 0; i < qesItemInfoArray.length; i++) {
            if (qesItemInfoArray[i].questionnaireId === undefined || qesItemInfoArray[i].questionnaireId.match(/^\s*$/)) {
                layer.msg('未选择要发布的问卷', function () {
                });
                return;
            }
            if (qesItemInfoArray[i].minSubmit === undefined
                || qesItemInfoArray[i].minSubmit === 0
                || qesItemInfoArray[i].minSubmit.match(/^\s*$/)) {
                layer.msg('未设置问卷的最少提交量', function () {
                });
                return;
            }
        }
        if (missionDescription.match(/^\s*$/)) {
            layer.confirm('不填写调查描述可以吗，默认将设置为“无描述”', {
                icon: 3,
                btn: ['确认发布', '返回修改']
            }, function (index) {
                layer.close(index);
                submitJsonData('无描述', unitIds, qesItemInfoArray);
            }, function (index) {
                layer.close(index);
            });
        } else {
            submitJsonData(missionDescription, unitIds, qesItemInfoArray);
        }
    });
});
function submitJsonData(missionDescription, unitIds, qesItemInfoArray) {
    var createResearchMissionVO = {};
    createResearchMissionVO.missionDescription = missionDescription;
    createResearchMissionVO.missionStartDate = new Date($('#begindate').val());
    createResearchMissionVO.missionDeadLine = new Date($('#enddate').val());
    createResearchMissionVO.missionObjectUnitId = unitIds;
    createResearchMissionVO.missionQuestionnaireInfo = qesItemInfoArray;

    if (isSubmitted) {
        return;
    }
    var url = '/researchManage/createResearchMission';
    accessServerByJson(url, createResearchMissionVO);
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
                $('#missionDescription').val(null);
                $('#researchUnit').selectpicker('deselectAll');
                $('#begindate').val(null);
                $('#enddate').val(null);
                $('select[name="questionnaireSelect"]').selectpicker('val', null);
                $('input[name="minSubmit"]').val(null);
                layer.close(index);
            },
            function () {
                layer.msg('取消成功！');
            }
        )
    });
});

function accessServerByJson(url, jsonData) {
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        type: 'post',
        async: false,
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            if (200 === data.code) {
                isSubmitted = true;
                successResultLayer("操作成功！", '/researchManage/getMissionManageView');
            }
            dealGlobalError(data);
        },
        error: function (data) {
            alert(data);
        }
    });
}

function successResultLayer(successMsg, redirectUrl) {
    layer.msg(successMsg, {
        icon: 1,
        time: 2000,
        shade: 0.5,
        closeBtn: 1
    }, function () {
        window.location.href = redirectUrl;
    });
}
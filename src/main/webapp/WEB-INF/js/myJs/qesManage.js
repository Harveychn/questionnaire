/**
 * Created by 郑晓辉 on 2017/3/30.
 */
var templateUrl = '/questionnaireManage/templateMultiQuestionnaire';
var delTemporaryUrl = '/questionnaireManage/delTemporaryMultiQuestionnaire';
var shareUrl = '/questionnaireManage/shareMultiQuestionnaire';


$(function () {
    $('#multiShareBtn').click(function () {
        var questionnaireIds = getMultiQuestionnaireIds();
        if (questionnaireIds.length > 0) {
            layer.confirm('确定批量共享问卷', {
                icon: 3,
                btn: ['确定', '取消']
            }, function () {
                accessServerByQesIds(questionnaireIds, shareUrl);
            }, function () {
                cancelLayer();
            });

        }
        else {
            chooseNonLayer();
        }
    });

    $('#multiDelBtn').click(function () {
        var questionnaireIds = getMultiQuestionnaireIds();
        if (questionnaireIds.length > 0) {
            layer.confirm('确定批量删除', {
                icon: 3,
                btn: ['确定', '取消'] //按钮
            }, function () {//确定删除
                accessServerByQesIds(questionnaireIds, delTemporaryUrl);
            }, function () { //取消删除
                cancelLayer();
            });
        }
        else {
            chooseNonLayer();
        }

    });

    $('#multiTemplateBtn').click(function () {
        var questionnaireIds = getMultiQuestionnaireIds();
        if (questionnaireIds.length > 0) {
            layer.confirm('确定批量模板化', {
                icon: 3,
                btn: ['确定', '取消'] //按钮
            }, function () {
                accessServerByQesIds(questionnaireIds, templateUrl);
            }, function () {
                cancelLayer();
            });
        }
        else {
            chooseNonLayer();
        }
    });
});

/**
 * 删除单张问卷
 * @param questionnaireId
 */
function delQesPaper(questionnaireId) {
    var questionnaireIds = [];
    questionnaireIds.push(questionnaireId);
    accessServerByQesIds(questionnaireIds, delTemporaryUrl);
}
/**
 * 分享问卷到公共模板库
 * @param questionnaireId
 */
function shareQesPaper(questionnaireId) {
    var questionnaireIds = [];
    questionnaireIds.push(questionnaireId);
    accessServerByQesIds(questionnaireIds, shareUrl);
}
/**
 * 添加到我的模板库
 * @param questionnaireId
 */
function add2TemplateLib(questionnaireId) {
    var questionnaireIds = [];
    questionnaireIds.push(questionnaireId);
    accessServerByQesIds(questionnaireIds, templateUrl);
}

/**
 * 获取用户checked的checkbox值
 * @returns {Array}
 */
function getMultiQuestionnaireIds() {
    var questionnaireIds = [];
    $('input[name="questionnaireId"]:checked').each(function () {
        questionnaireIds.push($(this).val());
    });
    return questionnaireIds;
}
/**
 * 取消layer方式提示
 */
function cancelLayer() {
    layer.msg('取消成功', {
        icon: 7,
        btn: ['返回']
    });
}
/**
 * 未选择layer提示
 */
function chooseNonLayer() {
    layer.alert('你还没有选择', {icon: 5});
}

function accessServerById(questionnaire, url) {

}
/**
 * 异步加载服务器
 * @param questionnaireIds
 * @param url
 */
function accessServerByQesIds(questionnaireIds, url) {
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
            layer.alert('操作失败', {icon: 2});
        }
    });
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
        var successMsg = '';
        switch (url) {
            case delTemporaryUrl://暂时删除问卷
                if (responsePkt.code === 200) {
                    successMsg = "成功添加 【" + questionnaireIds.length + "】 张问卷到个人模板库";
                    successResultLayer(successMsg);
                    location.reload(true);
                }
                dealGlobalError(responsePkt);
                break;
            case templateUrl: //模板化问卷
                if (responsePkt.code === 200) {
                    successMsg = "成功删除 【" + questionnaireIds.length + "】 张问卷,你可以在垃圾站中恢复这些问卷!";
                    successResultLayer(successMsg);
                    location.reload(true);
                }
                dealGlobalError(responsePkt);
                break;
            case shareUrl: //分享问卷
                if (responsePkt.code === 200) {
                    successMsg = "成功共享 【" + questionnaireIds.length + "】 张问卷到公共模版库";
                    successResultLayer(successMsg);
                    location.reload(true);
                }
                dealGlobalError(responsePkt);
                break;
            default:
                return;
        }

    }
}

function successResultLayer(successMsg) {
    layer.msg(successMsg, {
        icon: 1,
        time: 2000,
        shade: 0.5,
        closeBtn: 1
    });
}



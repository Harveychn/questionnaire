/**
 * Created by 郑晓辉 on 2017/3/30.
 */
$(document).ready(function () {
    $('#multiShareBtn').click(function () {
        var questionnaireIds = [];
        $('input[name="questionnaireId"]:checked').each(function () {
            questionnaireIds.push($(this).val());
        });
        console.log(questionnaireIds);
        if (questionnaireIds.length > 0) {
            $.ajax({
                url: '/questionnaireManage/shareMultiQuestionnaire',
                type: 'post',
                data: {questionnaireIds: questionnaireIds},
                dataType: 'text',
                traditional: true,
                success: function (data) {
                    var responsePkt = JSON.parse(data);
                    console.log(responsePkt);
                    if (responsePkt.code == 200) {
                        alert("成功共享 " + questionnaireIds.length + " 张问卷!");
                        location.reload(true);
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }
        else {
            alert("您还未选择任何一张问卷");
        }
    });
    $('#multiDeleteBtn').click(function () {
        var questionnaireIds = [];
        $('input[name="questionnaireId"]:checked').each(function () {
            questionnaireIds.push($(this).val());
        });
        console.log(questionnaireIds);
        if (questionnaireIds.length > 0) {
            $.ajax({
                url: '/questionnaireManage/delTemporaryMultiQuestionnaire',
                type: 'post',
                data: {questionnaireIds: questionnaireIds},
                dataType: 'text',
                traditional: true,
                success: function (data) {
                    var responsePkt = JSON.parse(data);
                    console.log(responsePkt);
                    if (responsePkt.code == 200) {
                        alert("成功删除 " + questionnaireIds.length + " 张问卷,你可以在垃圾站中恢复这些问卷!");
                        location.reload(true);
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }
        else {
            alert("您还未选择任何一张问卷");
        }
    });
    $('#multiTemplateBtn').click(function () {
        var questionnaireIds = [];
        $('input[name="questionnaireId"]:checked').each(function () {
            questionnaireIds.push($(this).val());
        });
        console.log(questionnaireIds);
        if (questionnaireIds.length > 0) {
            $.ajax({
                url: '/questionnaireManage/templateMultiQuestionnaire',
                type: 'post',
                data: {questionnaireIds: questionnaireIds},
                dataType: 'text',
                traditional: true,
                success: function (data) {
                    var responsePkt = JSON.parse(data);
                    console.log(responsePkt);
                    if (responsePkt.code == 200) {
                        alert("成功模板化 " + questionnaireIds.length + " 张问卷!");
                        location.reload(true);
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }
        else {
            alert("您还未选择任何一张问卷");
        }
    });
});
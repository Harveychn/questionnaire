/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/3.
 * Contract : zxh980278090@gmail.com
 */
$(document).ready(function () {
    $('#Add2MyLibBtn').click(function () {
        var templateIds = [];
        $('input[name="templateId"]:checked').each(function () {
            templateIds.push($(this).val());
        });
        console.log(templateIds);
        if (templateIds.length > 0) {
            $.ajax({
                url: '/qesTemplateManage/add2MyTemplateLib',
                type: 'post',
                data: {templateIds: templateIds},
                dataType: 'text',
                traditional: true,
                success: function (data) {
                    var responsePkt = JSON.parse(data);
                    console.log(responsePkt);
                    if (responsePkt.code == 200) {
                        alert("成功添加 " + templateIds.length + " 张模板到我的模板库!");
                        location.reload(true);
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }
        else {
            alert("您还未选择任何一张公共模板");
        }
    });
});

function addOne2MyLib(templateId) {
    var templateIds = [];
    templateIds.push(templateId);
    $.ajax({
        url: '/qesTemplateManage/add2MyTemplateLib',
        type: 'post',
        data: {templateIds: templateIds},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            var responsePkt = JSON.parse(data);
            console.log(responsePkt);
            if (responsePkt.code == 200) {
                alert("成功添加 " + templateIds.length + " 张模板到我的模板库!");
                location.reload(true);
            }
        },
        error: function () {
            alert("error");
        }
    });
}
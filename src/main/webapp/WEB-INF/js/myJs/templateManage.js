/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/3.
 * Contact : zxh980278090@gmail.com
 */
$(document).ready(function () {
    $('#Add2MyLibBtn').click(function () {
        var templateIds = [];
        $('input[name="templateId"]:checked').each(function () {
            templateIds.push($(this).val());
        });
        if (templateIds.length > 0) {
            accessServer(templateIds);
        }
        else {
            alert("您还未选择任何一张公共模板");
        }
    });
});

function addOne2MyLib(templateId) {
    var templateIds = [];
    templateIds.push(templateId);
    accessServer(templateIds);
}
/**
 * 异步加载访问服务器代码
 * @param templateIds
 */
function accessServer(templateIds) {
    $.ajax({
        url: '/qesTemplateManage/add2MyTemplateLib',
        type: 'post',
        data: {templateIds: templateIds},
        dataType: 'text',
        traditional: true,
        success: function (data) {
            responsePkt(data);
        },
        error: function () {
            alert("error");
        }
    });
}

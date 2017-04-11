/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/11.
 * Contract : zxh980278090@gmail.com
 */

function accessServerByJson(url, jsonData) {
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        type: 'post',
        dataType: 'json',
        data: JSON.stringify(jsonData),
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            alert(data);
        }
    });
}
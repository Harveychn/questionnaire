/**
 * questionnaire
 * Created by 郑晓辉 on 2017/4/9.
 * Contract : zxh980278090@gmail.com
 */
/**
 * code === 200 layer层展示
 * @param successMsg
 */
function successResultLayer(successMsg) {
    layer.msg(successMsg, {
        icon: 1,
        time: 3000
    }, function () {
        location.reload(true);
    });
}
/**
 * code === 400
 * @param msg
 * @constructor
 */
function REQUEST_ERROR(msg) {
    layer.msg(msg, {
        icon: 5
    });
}
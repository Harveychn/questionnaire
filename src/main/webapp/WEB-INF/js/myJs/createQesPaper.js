/**
 * Created by 郑晓辉 on 2017/5/5.
 */
var $saveAsQesBtn = $('#saveAsQesBtn');
var $storeQesBtn = $('#storeQesBtn');
var $templateQesBtn = $('#templateQesBtn');

var isSubmitted = false;
$(function () {
    isSubmitted = false;
    exam.init();
    $("select").dcselect();
    $saveAsQesBtn.click(function () {
        if (isSubmitted) {
            return;
        }
        getQesVOData(true, false);
    });

    $storeQesBtn.click(function () {
        if (isSubmitted) {
            return;
        }
        getQesVOData(false, false);
    });

    $templateQesBtn.click(function () {
        if (isSubmitted) {
            return;
        }
        getQesVOData(true, true);
    });
});

/**
 * 获取用户输入的数据信息，并提交服务器处理
 * @param isDone
 * @param isTemplate
 */
function getQesVOData(isDone, isTemplate) {
    var isNoError = true;
    var dataBase = {}, questionItems = [];
    dataBase.done = isDone;
    dataBase.template = isTemplate;

    var qesTitleStr = $('#questionnaireTitle').val();
    if (qesTitleStr.match(/^\s*$/)) {
        layer.alert('问卷标题不得为空！', {icon: 7});
        return;
    }

    dataBase.questionnaireTitle = qesTitleStr;

    dataBase.questionnaireSubtitle = $('#questionnaireSubtitle').val();
    dataBase.questionnaireDescription = $('#questionnaireDescription').val();

    var $uiQuestionContentList = $('.ui-questions-content-list');
    if ($uiQuestionContentList.find('li').size() <= 0) {
        layer.alert('问卷未添加一道题目，请先添加题目信息！', {icon: 7});
        return;
    }
    //封装所有题列表，遍历提取值analysis（答案）、题列表（数组对象）；
    $uiQuestionContentList.children('li').each(function (questionIndex) {
        var questionData = {}, options = [];
        /*问题是否必做*/
        if ('16px' === $(this).find('.module-menu > h4').css('font-size')) {
            questionData.must = true;
        }
        else {
            questionData.must = false;
        }
        var questionType;
        questionType = $(this).find('.ui-drag-area div').attr('data-questiontype');
        questionData.questionType = questionType;
        var $questionInput = $(this).find('.ui-drag-area input');
        var questionContext = $questionInput.val();
        if (questionContext.match(/^\s*$/)) {
            $questionInput.css('box-shadow', '4px 1px 15px #DE1709');
            isNoError = false;
            return;
        }
        questionData.questionContext = questionContext;
        //根据不同的问题类型包装组织不同的选项信息
        switch (questionType) {
            case '单选题':
            case '多选题':
                $(this).find('.form-horizontal').children('.form-group').each(function (optionIndex, elItem) {
                    var $optionInput = $(this).find('.col-sm-10 input');
                    var optionStr = $optionInput.val();
                    if (optionStr.match(/^\s*$/)) {
                        $optionInput.css('box-shadow', '4px 1px 15px #DE1709');
                        isNoError = false;
                        return;
                    }
                    var listItems = {};
                    listItems.optionOrder = optionIndex;
                    listItems.option = optionStr;
                    //获取问题后跟设置值
                    getOpFollow(listItems, elItem);
                    options.push(listItems);
                });
                break;
            case '单项填空题'://没有问题选项信息
            case '简答题':
                break;
            case '多项填空题'://问题选项信息需要切割分开
                var qesStrFormatted = $(this).find('.cq-items-content').find('textarea[name="multiCompletionResult"]').val();
                var qesStrArray = qesStrFormatted.split(/______/mi);
                for (var i = 0, j = 0; i < qesStrArray.length; i++) {
                    //移除切割后的空串
                    if (!qesStrArray[i].match(/^\s*$/)) {
                        var listItems = {};
                        listItems.optionOrder = j;
                        j++;
                        listItems.option = qesStrArray[i];
                        options.push(listItems);
                    }
                }
                break;
            case '图片单选题':
            case '图片多选题':
                $(this).find('.form-horizontal').children('.form-group').each(function (optionIndex, formItem) {
                    var listItem = {};
                    listItem.optionOrder = optionIndex;
                    listItem.option = $(formItem).find('form').attr('data-serv-url');
                    if (listItem.option === '') {
                        layer.alert('图片题出现问题！', {icon: 7});
                        listItem = null;
                        isNoError = false;
                        return;
                    }
                    //获取问题后跟设置值
                    getOpFollow(listItem, formItem);
                    options.push(listItem);
                });
                break;
            case '时间题':
                $(this).find('.form-horizontal').children('.form-group').each(function (optionIndex, formItem) {
                    var $optionInput = $(this).find('.col-sm-10 input');
                    var optionStr = $optionInput.val();
                    // if (optionStr.match(/^\s*$/)) {
                    //     $optionInput.css('box-shadow', '4px 1px 15px #DE1709');
                    //     isNoError = false;
                    //     return;
                    // }
                    var listItems = {};
                    listItems.optionOrder = optionIndex;
                    listItems.option = optionStr;
                    //获取问题后跟设置值
                    getOpFollow(listItems, formItem);
                    options.push(listItems);
                });
                break;
            default:
        }
        questionData.questionDescription = $(this).find('.analysis_contx').val() || 0;
        questionData.options = options;
        questionItems.push(questionData);
    });
    dataBase.questions = questionItems;
    //有校验不成功的地方,则不提交数据，直接返回
    if (!isNoError) {
        return;
    }

    // console.log(JSON.stringify(dataBase));

    //访问服务器
    //FIXME 恢复访问服务器
    if (isSubmitted) {
        return;
    }
    var url = '/questionnaireManage/create';
    submitQesDataByJson(url, dataBase);
}

function submitQesDataByJson(url, jsonData) {
    $.ajax({
        url: url,
        contentType: "application/json;charset=utf-8",
        type: 'post',
        dataType: 'json',
        async: false,
        data: JSON.stringify(jsonData),
        success: function (data) {
            if (200 === data.code) {
                isSubmitted = true;
                layer.msg("操作成功！", {
                    icon: 1,
                    time: 2000,
                    shade: 0.5,
                    closeBtn: 1
                }, function () {
                    window.location.href = "/questionnaireManage/getListMyQesPaperView";
                });
            }
            dealGlobalError(data);
        },
        error: function (data) {
            alert(data);
        }
    });
}

//编辑标题是输入框状态
function editingTitleFunc(element) {
    $(element).css(
        {
            'color': 'black',
            'box-shadow': '-1px 1px 20px #66afe9',
            'border-radius': '0px'
        }
    );
    $(element).on('blur', function () {
        $(element).css(
            {
                'background': 'white',
                'color': 'grey',
                'box-shadow': 'none'
            }
        )
        ;
    });
}

function editingOptionFunc(element) {
    $(element).css(
        {
            'background': 'white',
            'color': 'black',
            'box-shadow': '-1px 1px 11px #59514B',
            'border-radius': '0px'
        }
    );
    $(element).on('blur', function () {
        $(element).css(
            {
                'background': 'white',
                'color': 'grey',
                'box-shadow': 'none'
            }
        )
        ;
    });
}

function keyDownFunc(element) {
    var textStr = $(element).val();
    var pattern = /__*/im;
    var optionStrArray = textStr.split(pattern);

    var resultText = '';
    for (var index = 0; index < optionStrArray.length; index++) {
        if (trim(optionStrArray[index].toString()) === "") {
            continue;
        }
        resultText += "______" + trim(optionStrArray[index].toString());
    }
    $(element).parent('.row').find('textarea[name="multiCompletionResult"]').val(resultText);
    $(element).on('blur', function () {
        $(element).val('');
        $(element).parent('.row').find('textarea[name="multiCompletionResult"]').val(resultText);
    });
    $(element).on('focus', function () {
        var textVal = $(element).parent('.row').find('textarea[name="multiCompletionResult"]').val();
        $(element).val(textVal);
    });
}

function trim(str) { //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
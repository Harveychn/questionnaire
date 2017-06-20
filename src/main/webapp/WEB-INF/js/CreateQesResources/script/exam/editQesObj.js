var exam = {
    editingQesId: 0,
    //初始化
    init: function () {
        //加载数据
        // this.loadDataFn();
        //拖拽
        this.dragFn();
        //拖拽排序
        this.sortFn();
        //题目菜单滚动固定顶部
        this.fixFn();
        //题目菜单折叠/展开
        this.menuFn();
        //标题编辑
        this.titleEditFn();
        //题操作事件初始化
        this.listAllCtrlFn('.ui-questions-content-list', '.ui-up-btn', '.ui-down-btn', '.ui-clone-btn', '.ui-del-btn');
        //批量添加事件初始化
        this.topicACtrlFn('.ui-questions-content-list', '.ui-add-item-btn',
            '.ui-batch-item-btn', '.ui-add-answer-btn',
            '.ui-set-must-done', '.ui-remove-item-btn');
        //
        this.moveTispFn('.ui-up-btn,.ui-down-btn,.ui-clone-btn,.ui-del-btn');
        this.moveTispFn('.ui-add-item-btn,.ui-batch-item-btn,.ui-add-answer-btn,.ui-set-must-done,.ui-remove-item-btn');
    },
    //拖拽
    dragFn: function () {
        var _this = this;
        var data = {}, addname = 0;
        this.editingQesId = loadDataFn();

        $("#ui_sortable_exam").find("li").draggable({
            /* containment:'#pageContentId',*/
            connectToSortable: '.ui-questions-content-list',
            cursorAt: {top: 18, left: 20},
            helper: function (event) {
                addname++;
                data = {
                    type: $(this).children('a').attr('data-checkType'),//1为单选，2为多选
                    questionType: $(this).children('a').attr('data-questionType'),
                    name: 'q' + $(this).attr('data-uid') + '_' + addname,
                    //生成1000以内的随机数
                    itmetid: addname + parseInt(1000 * Math.random()),
                    items: [{
                        value: '0',
                        //生成1000以内的随机数
                        tid: addname + parseInt(1000 * Math.random())
                    }, {
                        value: '0',
                        //生成1000以内的随机数
                        tid: addname + parseInt(1000 * Math.random())
                    }]
                };
                return template($(this).attr('data-tempId'), data);
            },
            revert: 'invalid',
            start: function (event) {
                _this.titleDelFn();
            },
            drag: function (event) {

            },
            stop: function (event) {
                _this.orderFn($('.ui-questions-content-list'));
            }
        }).on('click', function (e) {
            addname++;
            data = {
                type: $(this).children('a').attr('data-checkType'),//1为单项选择题，2为多项选择题,3为单项填空题,4为多项填空题,5为图片单选题，6为图片多选题
                questionType: $(this).children('a').attr('data-questionType'),
                //name命名规则，q代表前缀+父级li的题型id
                name: 'q' + $(this).attr('data-uid') + '_' + addname,
                //生成1000以内的随机数
                itmetid: addname + parseInt(1000 * Math.random()),
                items: [{
                    value: '0',
                    //生成1000以内的随机数
                    tid: addname + parseInt(1000 * Math.random())
                }, {
                    value: '0',
                    //生成1000以内的随机数
                    tid: addname + parseInt(1000 * Math.random())
                }]
            };
            $('.ui-questions-content-list').append(template($(this).attr('data-tempId'), data));
            _this.orderFn($('.ui-questions-content-list'));
            _this.sortFn();
        }).disableSelection();
    },
    //拖拽排序
    sortFn: function () {
        var _this = this;
        $('.ui-questions-content-list').sortable({
            handle: '.ui-drag-area',
            items: '>li',
            containment: '#pageContentId',
            opacity: 0.7,
            placeholder: 'ui-state-highlight',
            start: function (event) {
                exam.titleDelFn();
            },
            stop: function () {
                _this.orderFn($(this));
            },
            revert: 'invalid'
        });
    },
    //标题序列号
    orderFn: function (obj) {
        obj.find('li.items-questions').each(function (i) {
            $(this).find('.module-menu h4').html((i + 1));
            // $(this).find('.module-menu h4').html("Q"+(i+1));
        });
    },
    //题目菜单滚动固定顶部
    fixFn: function () {
        var _this = this;
        $('#desktop_scroll').scroll(function () {
            _this.titleDelFn();
            var parentLeft = $('.exam-nav').parent().offset().left;
            if ($('.exam-nav').offset().top + 20 + $('.conditionItems').outerHeight() + $('.title').outerHeight() <= $(this).scrollTop()) {
                $('.exam-nav').css({'position': 'fixed', 'top': 0 + 'px', 'left': parentLeft + 'px'});
                $('.exam-nav').addClass('scrollCurr');
            } else {
                $('.exam-nav').removeAttr('style');
                $('.exam-nav').removeClass('scrollCurr');
            }

        });
    },
    //题目菜单折叠/展开
    menuFn: function () {
        $('.exam-item-title').on('click', function () {
            if ($(this).hasClass('curr')) {
                $(this).removeClass('curr');
                $(this).find('i').removeClass('icon-collapse').addClass('icon-expand');
                $(this).next('ul.exam-nav-list').stop().slideDown();
            } else {
                $(this).addClass('curr');
                $(this).find('i').removeClass('icon-expand').addClass('icon-collapse');
                $(this).next('ul.exam-nav-list').stop().slideUp();
            }
        });
    },
    //标题编辑
    titleEditFn: function () {
        $(document).on('click', '.T_edit', function (event) {
            $('.cq-into-edit').remove();
            var data = {
                title: ''
            };
            if (!$('.cq-into-edit').size()) {
                $('body').append(template('drag_T_edit', data));
                $('.cq-into-edit').attr('data-gettid', $(this).attr('data-tid'));
            }
            //标题编辑工具
            // if ($(this).hasClass('T_plugins')) {
            //     $('.cq-into-edit').append(template('T_edit_plugins', {}));
            // }
            $('.cq-into-edit').css({
                'top': ($(this).offset().top - 1) + 'px',
                'left': ($(this).offset().left) + 'px',
                'width': $(this).outerWidth() + 'px',
            });
            if ($(this).hasClass('T-center')) {
                $('.cq-into-edit .cq-edit-title').css({
                    'text-align': 'center'
                });
            } else {
                $('.cq-into-edit .cq-edit-title').css({
                    'text-align': 'left'
                });
            }
            if ($(this).attr('data-font')) {
                $('.cq-into-edit .cq-edit-title').css({
                    'font-size': $(this).attr('data-font') + 'px'
                });
            } else {
                $('.cq-into-edit .cq-edit-title').css({
                    'font-size': ''
                });
            }

            $(this).html('<span style="font-size:18px;"></span>');

            $('.cq-into-edit .cq-edit-title').css({
                'min-height': $(this).height() + 'px',
                'padding-top': ($(this).outerHeight() - $(this).height()) / 2 + 'px',
                'padding-bottom': ($(this).outerHeight() - $(this).height()) / 2 + 'px'
            }).html($(this).html()).focus();

            $(document).one('click', function () {
                $('.cq-into-edit').remove();
            });
            $(document).on('click', '.cq-into-edit', function (e) {
                e.stopPropagation();
            });
            event.stopPropagation();
        });

        $(document).on('blur', '.cq-into-edit .cq-edit-title', function () {
            $('.T_edit[data-tid=' + $('.cq-into-edit').attr('data-gettid') + ']').html($('.cq-into-edit .cq-edit-title').html());
            // var $itemInput = $('.T_edit[data-tid=' + $('.cq-into-edit').attr('data-gettid') + ']').closest('li').find('.input-check').find('input');
            // if ($itemInput.size()) {
            //     $itemInput.val($('.cq-into-edit .cq-edit-title').html());
            // }
            var questionnaireTitle;
            var $qesPaperTitle = $('#questionnaireTitle');
            try {
                questionnaireTitle = $qesPaperTitle.find('span').html().toString();
            } catch (error) {
                layer.msg('请正确编辑问卷标题!', function () {
                });
                $qesPaperTitle.html('<span style="font-size:18px;">这里填写问卷标题</span>');
                $qesPaperTitle.addClass('bg-danger');
                $(this).focus();
                return;
            }
            if (questionnaireTitle.match(/^\s*$/)) {
                layer.msg('问卷标题不能为空串', function () {
                });
                $qesPaperTitle.addClass('bg-danger');
                $(this).focus();
            }
            if (questionnaireTitle === '这里填写问卷标题') {
                layer.msg('问卷标题必须编辑！', function () {
                });
                $qesPaperTitle.addClass('bg-danger');
                $(this).focus();
            }
            if ($qesPaperTitle.hasClass('bg-danger')) {
                $qesPaperTitle.removeClass('bg-danger');
            }
        });
    },
    //关闭标题编辑
    titleDelFn: function () {
        if ($('.cq-into-edit').size()) {
            $('.T_edit[data-tid=' + $('.cq-into-edit').attr('data-gettid') + ']').html($('.cq-into-edit .cq-edit-title').html());
            $('.cq-into-edit').hide();
        }
    },
    //鼠标移动上显示
    moveTispFn: function (obj) {
        $(document).on('mousemove', obj, function (e) {
            var strTx = $(this).attr('data-tisp');
            var str = $('<div class="move-tisp-box"></div>');
            str.html(strTx);
            if (!$('.move-tisp-box').size()) {
                str.appendTo('body');
            }
            $('.move-tisp-box').css({top: (e.pageY + 15) + 'px', left: (e.pageX + 15) + 'px'});
        });
        $(document).on('mouseout', obj, function (e) {
            $('.move-tisp-box').remove();
        });
    },
    //控制操作：上移，下移，复制，删除
    listAllCtrlFn: function (parentObj, upObj, downObj, cloneObj, delObj) {
        var _this = this;
        //上移
        $(document).on('click', parentObj + ' ' + upObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module');
            if ($parentItems.prev('li.ui-module').size()) {
                $parentItems.insertBefore($parentItems.prev('li.ui-module'));
                _this.orderFn($(parentObj));
                _this.titleDelFn();
            } else {
                layer.msg('已经是第一个了！');
            }
        });
        //下移
        $(document).on('click', parentObj + ' ' + downObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module');
            if ($parentItems.next('li.ui-module').size()) {
                $parentItems.insertAfter($parentItems.next('li.ui-module'));
                _this.orderFn($(parentObj));
                _this.titleDelFn();
            } else {
                layer.msg('已经是最后一个了！');
            }
        });
        //复制/克隆
        $(document).on('click', parentObj + ' ' + cloneObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module');
            $parentItems.clone(true).insertAfter($parentItems);
            _this.orderFn($(parentObj));
            _this.titleDelFn();
        });
        //删除
        $(document).on('click', parentObj + ' ' + delObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module');
            $parentItems.remove();
            layer.msg('已删除！');
            $('.move-tisp-box').remove();
            _this.orderFn($(parentObj));
            _this.titleDelFn();
        });
    },
    //单题添加，批量添加
    topicACtrlFn: function (parentObj, addObj, batchAddObj, addAnswerObj, setMustObj, removeObj) {
        //添加选项栏
        var $tid = 100 + parseInt(1000 * Math.random());
        $(document).on('click', parentObj + ' ' + addObj, function (e) {
            // var $parentItems = $(this).closest('li.ui-module').find('.cq-unset-list');
            var $parentItems = $(this).closest('li.ui-module').find('.form-horizontal');
            var $name = $.trim($parentItems.attr('data-nameStr'));
            $tid++;
            var data = {
                type: parseInt($parentItems.attr('data-checktype')),
                name: $name,
                // index: $parentItems.children('li:last').index() + 1,
                index: $parentItems.children('.form-group:last').index() + 1,
                items: [{value: '0', tid: $tid}]
            };
            $parentItems.append(template('ui_additem_content', data));
        });
        //移除选项
        $(document).on('click', parentObj + ' ' + removeObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module').find('.form-horizontal');
            var $lastOptionItem = $parentItems.find('.form-group').last();
            $lastOptionItem.remove();
        });
        //批量添加选项栏
        $(document).on('click', parentObj + ' ' + batchAddObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module').find('.cq-unset-list');
            layer.msg('此功能暂未开放！');
        });
        //添加答案解析
        $(document).on('click', parentObj + ' ' + addAnswerObj, function (e) {
            $(this).closest('li').css({'height': 'auto'});
            // var $parentItems = $(this).closest('li.ui-module').find('.cq-unset-list');
            var $parentItems = $(this).closest('li.ui-module').find('.form-horizontal');
            if (!$(this).closest('li.ui-module').find('.analysis_contx').size()) {
                $parentItems.after(template('analysis_tmp', {}));
            } else {
                $(this).closest('li.ui-module').find('.analysis_contx').remove();
            }
        });
        //设置问题为必做题
        $(document).on('click', parentObj + ' ' + setMustObj, function (e) {
            var $parentItems = $(this).closest('li.ui-module').find('.module-menu > h4');
            var currentFontSize = $parentItems.css('font-size');
            if (currentFontSize !== '14px') {
                $parentItems.css({
                    'background': '#FFFFFF',
                    'color': '#6a6a6a',
                    'font-size': '14px',
                    'border-color': '#FFFFFF'
                });
            } else {
                $parentItems.css({
                    'background': '#ED9E2C',
                    'color': '#6a6a6a',
                    'font-size': '16px'
                });
            }
        });
    }
};

/**
 * 加载问卷数据
 * @returns {number} 返回正在编辑的问卷ID
 */
function loadDataFn() {
    var editingQesPaperId = 0;

    var questionArray = [];
    $.ajaxSettings.async = false;
    $.getJSON('/QesManageRest/getEditQesPaperData', function (responseData) {
        editingQesPaperId = responseData.questionnaireId;
        $('#questionnaireTitle').val(responseData.questionnaireTitle);
        $('#questionnaireSubtitle').val(responseData.questionnaireSubtitle);
        $('#questionnaireDescription').val(responseData.questionnaireDescription);

        questionArray = responseData.questions;
        var questionData = {};
        for (var i = 0; i < questionArray.length; i++) {
            var questionTypeStr = questionArray[i].questionType;
            var questionOptionArray = questionArray[i].options;
            var optionItemArray = [];
            var optionItem = {};
            switch (questionTypeStr) {
                case '单选题':
                case '多选题':
                case '简答题':
                case '单项填空题':
                    optionItemArray = [];
                    for (var j = 0; j < questionOptionArray.length; j++) {
                        optionItem = {};
                        optionItem.value = 0;
                        optionItem.tid = i + parseInt(1000 * Math.random());
                        optionItem.option = questionOptionArray[j].option;
                        optionItemArray.push(optionItem);
                    }

                    questionData = {
                        type: excQuestionCode(questionTypeStr),
                        must: questionArray[i].must,
                        questionType: questionArray[i].questionType,
                        questionContext: questionArray[i].questionContext,
                        name: 'q' + excQuestionCode(questionTypeStr) + '_' + i,
                        //生成1000以内的随机数
                        itmetid: i + parseInt(1000 * Math.random()),
                        items: optionItemArray
                    };
                    break;
                case '多项填空题':
                    optionItemArray = [];
                    optionItem = {};
                    optionItem.tid = i + parseInt(1000 * Math.random());
                    var optionItemStr = '';
                    for (var j = 0; j < questionOptionArray.length; j++) {
                        optionItemStr += '______' + questionOptionArray[j].option;
                    }
                    optionItem.option = optionItemStr;
                    optionItemArray.push(optionItem);

                    questionData = {
                        type: excQuestionCode(questionTypeStr),
                        must: questionArray[i].must,
                        questionType: questionArray[i].questionType,
                        questionContext: questionArray[i].questionContext,
                        name: 'q' + excQuestionCode(questionTypeStr) + '_' + i,
                        //生成1000以内的随机数
                        itmetid: i + parseInt(1000 * Math.random()),
                        items: optionItemArray
                    };
                default:
                    break;
            }
            $('.ui-questions-content-list').append(template(getDataTempId(questionTypeStr), questionData));
        }
        //设置问题序号
        $('.ui-questions-content-list').find('li.items-questions').each(function (i) {
            $(this).find('.module-menu h4').html((i + 1));
        });
    });
    return editingQesPaperId;
}
/**
 * 把文字类型的问题类型转为模板数据类型
 * @param typeStr
 * @returns {number}
 */
function excQuestionCode(typeStr) {
    switch (typeStr) {
        case '单选题':
            return 10;
        case '多选题':
            return 20;
        case '简答题':
            return 30;
        case '单项填空题':
            return 40;
        case '多项填空题':
            return 50;
        default:
            return -1;
    }
}
/**
 * 根据问题类型字符串获取模板名称
 * @param typeStr
 * @returns {String}
 */
function getDataTempId(typeStr) {
    switch (typeStr) {
        case '单选题':
        case '多选题':
            return 'reload_choice_template';
        case '简答题':
        case '单项填空题':
            return 'reload_single_completion_template';
        case '多项填空题':
            return 'reload_multi_completion_template';
        default:
    }
}

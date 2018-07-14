/**
 * creator: xiaohui zheng
 * time: 2017/10/29  20:48
 * function: 工具类
 */
function bindEl(itemUid) {
    var $itemEL = $("#" + itemUid);
    $itemEL.dropzone({
        url: "/fileIo/upload/picture/qesPaper",
        addRemoveLinks: false,
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: 'image/*',
        init: function () {
            this.on('addedfile', function () {
                var existed = this.getAcceptedFiles();
                while (existed.length >= 1) {
                    this.removeFile(existed[0]);
                    existed.splice(0, 1);
                }
            });
            this.on('sending', function () {
            });
            this.on('success', function (file, response) {
                if (response.code === 200) {
                    $itemEL.attr('data-serv-url', response.data);
                    return;
                }
                $itemEL.attr('data-serv-url', '');
            });
            this.on("maxfilesexceeded", function () {
                this.removeFile(this.getAcceptedFiles());
            });
        },
        dictDefaultMessage: '点击/拖拽添加图片',
        dictInvalidInputType: '文件类型不支持',
        dictMaxFilesExceeded: '超过最大文件数量',
        dictFileTooBig: '图片文件不予许超过10M'
    });
}

function initDropzone(itemUid) {
    var $itemEL = $("#" + itemUid);
    var myDropzone = new Dropzone("#" + itemUid, {
        url: "/fileIo/upload/picture/qesPaper",
        addRemoveLinks: false,
        maxFiles: 1,
        maxFilesize: 10,
        acceptedFiles: 'image/*',
        init: function () {
            if ($itemEL.find('img').length >= 1) {
                $itemEL.find('.dz-message').remove();
            }
            this.on('addedfile', function () {
                var existed = this.getAcceptedFiles();
                while (existed.length >= 1) {
                    this.removeFile(existed[0]);
                    existed.splice(0, 1);
                }
            });
            this.on('sending', function () {
            });
            this.on('success', function (file, response) {
                if (response.code === 200) {
                    $itemEL.attr('data-serv-url', response.data);
                    return;
                }
                $itemEL.attr('data-serv-url', '');
            });
            this.on("maxfilesexceeded", function () {
                this.removeFile(this.getAcceptedFiles());
            });
        },
        dictDefaultMessage: '点击/拖拽添加图片',
        dictInvalidInputType: '文件类型不支持',
        dictMaxFilesExceeded: '超过最大文件数量',
        dictFileTooBig: '图片文件不予许超过10M'
    });
}


var LayerUtil = {
    errorMsg: function (msgStr) {
        layer.msg(msgStr, {
            icon: 2,
            shade: 0.3,
            shadeClose: true
        });
    },
    htmlLayer: function (obj) {
        layer.open({
            id: 'logicEditUI',
            type: 1,
            closeBtn: 2,
            skin: 'layer-ui-html', //自定义边框样式
            area: ['500px', '400px'], //宽高
            title: obj.hasOwnProperty('title') ? obj.title : ' ',
            content: obj.hasOwnProperty('content') ? obj.content : ' '
        });
    }
};

var ArrayUtil = {
    initArray: function (argObj) {
        var relt = [];
        var initVal = 0;
        if (argObj.hasOwnProperty('initVal')) {
            initVal = argObj.initVal;
        }
        if (argObj.hasOwnProperty('capacity')) {
            for (var i = initVal; i < argObj.capacity; i++) {
                relt.push(i);
            }
        }
        return relt;
    }
};

//上传答卷时，获取html data set中的后跟逻辑数据
function getOpFollow(listItems, el) {
    //获取问题后跟设置值
    if ($(el).attr('data-option-follow') < 0 || $(el).attr('data-option-follow') == undefined) {
        listItems.optionFollow = -1;
    } else {
        listItems.optionFollow = parseInt($(el).attr('data-option-follow'));
    }
}
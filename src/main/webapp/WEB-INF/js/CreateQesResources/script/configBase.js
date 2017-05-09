var vars = {
    // jsPath: 'CreateQesResources/script/',
    // jsTmp: 'CreateQesResources/script/artTemplate/',
    // cssPath: 'CreateQesResources/skin/',
    // isLayer: 'CreateQesResources/script/layer/'
    jsPath: '../js/CreateQesResources/script/',
    jsTmp: '../js/CreateQesResources/script/artTemplate/',
    cssPath: '../js/CreateQesResources/skin/',
    isLayer: '../js/CreateQesResources/script/layer/'
};

//扩展
var fnExtend = {
    includeFile: function (g, c) {
        for (var f = "string" === typeof c ? [c] : c, d = 0; d < f.length; d++) {
            var b = f[d].replace(/^\s|\s$/g, ""),
                a = b.split("."),
                e = "css" == a[a.length - 1].toLowerCase(),
                a = e ? "link" : "script",
                h = e ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ",
                b = (e ? "href" : "src") + "='" + g + b + "'";
            0 == $(a + "[" + b + "]").length && document.write("<" + a + h + b + "></" + a + ">")
        }
    }
};

//插入css文件
fnExtend.includeFile(vars.cssPath, ['base.css', 'content.css', 'blue.css', 'bootstrap.min.css']);
//插入js文件
fnExtend.includeFile(vars.jsPath, ['jquery-ui.min.js', 'dcselect.js']);
fnExtend.includeFile(vars.isLayer, ['layer.js']);
fnExtend.includeFile(vars.jsTmp, ['template.js']);
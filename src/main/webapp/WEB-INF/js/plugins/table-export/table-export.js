//为导出功能设置数据源
function setExportDataAttr() {
    $(".export-png").attr("data-table", "#questionTableData");
    $(".export-xls").attr("data-table", "#questionTableData");
    $(".export-xlsx").attr("data-table", "#questionTableData");

    $(".export-png").attr("data-filename", "图片数据");
    $(".export-xls").attr("data-filename", "Excel数据");
    $(".export-xlsx").attr("data-filename", "Excel数据");
};

var TableExport = function () {
    "use strict";
    //function to initiate HTML Table Export
    var runTableExportTools = function () {
        $(".export-png").on("click", function (e) {
            e.preventDefault();
            var exportTable = $(this).data("table");
            var filename = $(this).data("filename");
            var ignoreColumn = $(this).data("ignorecolumn");
            $(exportTable).tableExport({
                fileName: filename,
                type: 'png'
            });
        });
        $(".export-xls").on("click", function (e) {
            e.preventDefault();
            var exportTable = $(this).data("table");
            var filename = $(this).data("filename");
            var ignoreColumn = $(this).data("ignorecolumn");
            $(exportTable).tableExport({
                fileName: filename,
                type: 'excel',
                escape: 'false',
                excelstyles: ['border-bottom', 'border-top', 'border-left', 'border-right'],
                ignoreColumn: '[' + ignoreColumn + ']'
            });
        });
        $(".export-xlsx").on("click", function (e) {
            e.preventDefault();
            var exportTable = $(this).data("table");
            var filename = $(this).data("filename");
            var ignoreColumn = $(this).data("ignorecolumn");
            $(exportTable).tableExport({
                fileName: filename,
                type: 'xlsx',
                escape: 'false',
                ignoreColumn: '[' + ignoreColumn + ']'
            });
        });
    };
    return {
        init: function () {
            runTableExportTools();
        }
    };
}();

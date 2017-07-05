//为导出功能设置数据源
function setExportDataAttr() {
    $(".export-png").attr("data-table","#questionTableData");

    $(".export-png").attr("data-filename","导出数据");
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
    };
    return {
        init: function () {
            runTableExportTools();
        }
    };
}();

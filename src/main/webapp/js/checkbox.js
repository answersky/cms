var ids = [];//存在多选框的值
$(document).ready(function () {
    $("#checkBox").click(function () {
        $("input.checkBox").each(function () {
            if ($("#checkBox").is(':checked')) {
                $(this).prop("checked", true);
                ids.push($(this).val());
            } else {
                $(this).prop("checked", false);
                //清空
                ids = [];
            }
        })
    });

    $("input.checkBox").each(function () {
        $(this).click(function () {
            if ($(this).is(':checked')) {
                ids.push($(this).val());
            } else {
                ids.splice($.inArray($(this).val(), ids), 1);
            }
        });
    });
});

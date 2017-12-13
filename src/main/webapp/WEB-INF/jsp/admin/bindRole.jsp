<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/hdw.css">
<%--绑定角色--%>
<div class="modal fade" id="bindRoleModal" tabindex="-1" role="dialog" aria-labelledby="bindModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">X
                </button>
                <h4 class="modal-title" id="bindModalLabel">
                    绑定用户
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="rid">
                    <div class="content" style="margin-left: 100px;">
                        <label>未绑定的用户</label><br>
                        <select multiple="multiple" id="select1">

                        </select>
                        <span id="add">选中右移>></span></div>
                    <div class="content">
                        <label>已绑定的用户</label><br>
                        <select multiple="multiple" id="select2">

                        </select>
                        <span id="remove">选中左移>></span>
                    </div>

                    <div class="modal-footer"></div>
                </form>

            </div>

        </div>
    </div>
</div>

<script>
    $(document).ready(function () {

        $("#add").click(function () {
            //绑定角色
            var roleId = $("#rid").val();
            var userId = $("#select1 option:selected").val();
            $("#select1 option:selected").appendTo("#select2");
            $.ajax({
                url: "${pageContext.request.contextPath}/bindUser",
                method: "post",
                dataType: "text",
                data: {
                    roleId: roleId,
                    userId: userId
                },
                success: function (result) {
                    alert("绑定成功");
                }
            })

        });

        $("#remove").click(function () {
            //解绑角色
            var roleId = $("#rid").val();
            var userId = $("#select2 option:selected").val();
            $("#select2 option:selected").appendTo("#select1");
            $.ajax({
                url: "${pageContext.request.contextPath}/unbindUser",
                method: "post",
                dataType: "text",
                data: {
                    roleId: roleId,
                    userId: userId
                },
                success: function (result) {
                    alert("解绑成功");
                }
            })

        });


    });


</script>
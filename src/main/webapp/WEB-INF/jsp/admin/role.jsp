<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Xenon Boostrap Admin Panel"/>
    <meta name="author" content=""/>

    <title>角色管理</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);


            //新增
            $("#addRole").click(function () {
                var role = $.trim($("#roleName").val());
                var remark = $.trim($("#remark").val());
                if (role == null || role == '') {
                    alert("请输入角色");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveRole",
                    method: "post",
                    dataType: "text",
                    data: {
                        role: role,
                        remark: remark
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改
            $("#updateRole").click(function () {
                var id = $("#roleId").val();
                var role = $.trim($("#role").val());
                var remark = $.trim($("#rRemark").val());
                if (role == null || role == '') {
                    alert("请输入角色");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/updateRole",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        role: role,
                        remark: remark
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

        });

        //删除
        function deleteRole(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteRole",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    window.location.reload();
                }
            });
        }

        //传递参数
        function transferParameter(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/findRoleById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#roleId").val(id);
                    var role = JSON.parse(result).role;
                    $("#role").val(role);
                    var rRemark = JSON.parse(result).remark;
                    $("#rRemark").val(rRemark);
                }
            });
        }

        function findBindUserData(roleId) {
            $("#select2").html("");
            $("#select1").html("");
            $("#rid").val(roleId);
            $.ajax({
                url: "${pageContext.request.contextPath}/findBindUserData",
                method: "post",
                dataType: "text",
                data: {
                    roleId: roleId
                },
                success: function (result) {
                    var map = JSON.parse(result);
                    var bind = map['bind'];
                    var unbind = map['unbind'];
                    for (var i = 0; i < bind.length; i++) {
                        var html = "<option value='" + bind[i].id + "'>" + bind[i].username + "(实名：" + bind[i].realName + ")</option>";
                        $("#select2").append(html);
                    }

                    for (var i = 0; i < unbind.length; i++) {
                        var html = "<option value='" + unbind[i].id + "'>" + unbind[i].username + "(实名：" + unbind[i].realName + ")</option>";
                        $("#select1").append(html);
                    }

                }
            });
        }

    </script>
</head>
<body class="page-body">

<div class="page-container">

    <%--左侧菜单--%>
    <%@include file="../common/left_menu.jsp" %>

    <div class="main-content">

        <%--头部--%>
        <%@include file="../common/header.jsp" %>

        <!-- Table Styles -->
        <div class="panel panel-default">
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"
                    style="width: 100px;height: 42px;">添加角色
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>角色</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${roles}" var="role" varStatus="roleIndex">
                        <tr>
                            <td>${roleIndex.index+1}</td>
                            <td>${role.role}</td>
                            <td>${role.remark}</td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${role.id})">
                                <input type="button" value="删除" onclick="deleteRole(${role.id})">
                                <input type="button" value="绑定用户" data-toggle="modal" data-target="#bindRoleModal"
                                       onclick="findBindUserData(${role.id})">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
        <!-- Main Footer -->

        <%--底部--%>
        <%@include file="../common/footer.jsp" %>
    </div>

    <%--新增模板--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">X
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        新增角色
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="roleName" class="col-sm-2 control-label">角色名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="roleName" placeholder="请输入角色名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="remark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="remark" placeholder="请输入备注信息">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addRole">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%--修改模板--%>
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">X
                    </button>
                    <h4 class="modal-title" id="updateModalLabel">
                        修改角色
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="roleId">
                        <div class="form-group">
                            <label for="role" class="col-sm-2 control-label">角色名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="role" placeholder="请输入角色名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="rRemark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="rRemark" placeholder="请输入备注信息">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateRole">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%--绑定角色--%>
    <%@ include file="bindRole.jsp" %>

</div>

</body>
</html>
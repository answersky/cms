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

    <title>菜单管理</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);

            //新增
            $("#addAccount").click(function () {
                var username = $.trim($("#username").val());
                var password = $.trim($("#password").val());
                var surePassword = $.trim($("#surePassword").val());
                var realName = $.trim($("#realName").val());
                if (username == null || username == '') {
                    alert("请输入用户名");
                    return;
                }
                if ((password == null || password == '') || (surePassword == null || surePassword == '')) {
                    alert("请输入密码");
                    return;
                }
                if (password != surePassword) {
                    alert("两次输入的密码不一致");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/validateUsername",
                    method: "post",
                    dataType: "text",
                    data: {
                        username: username
                    },
                    success: function (result) {
                        if (result == 1) {
                            registerUsername(username, password, realName);
                        } else {
                            alert("用户名已存在");
                        }
                    }
                });

            });

            //修改
            $("#updateAccount").click(function () {
                var id = $("#accountId").val();
                var username = $.trim($("#uName").val());
                var realName = $.trim($("#rName").val());
                if (username == null || username == '') {
                    alert("请输入用户名");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateAccount",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        username: username,
                        realName: realName
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改密码
            $("#updatePassword").click(function () {
                var password = $.trim($("#newPassword").val());
                var accountId = $("#aid").val();
                if (password == null || password == '') {
                    alert("请输入新密码");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/updatePassword",
                    method: "post",
                    dataType: "text",
                    data: {
                        accountId: accountId,
                        password: password
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });

            });


        });

        function registerUsername(username, password, realName) {
            $.ajax({
                url: "${pageContext.request.contextPath}/registerAccount",
                method: "post",
                dataType: "text",
                data: {
                    username: username,
                    realName: realName,
                    password: password
                },
                success: function (result) {
                    window.location.reload();
                }
            });
        }

        //删除
        function deleteAccount(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteAccount",
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
                url: "${pageContext.request.contextPath}/findAccountById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#accountId").val(id);
                    var username = JSON.parse(result).username;
                    $("#uName").val(username);
                    var realName = JSON.parse(result).realName;
                    $("#rName").val(realName);
                }
            });
        }

        function updatePassword(id) {
            $("#aid").val(id);
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
                    style="width: 100px;height: 42px;">新增账户
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>账户</th>
                        <th>真实姓名</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${accounts}" var="account" varStatus="accountIndex">
                        <tr>
                            <td>${accountIndex.index+1}</td>
                            <td>${account.username}</td>
                            <td>${account.realName}</td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${account.id})">
                                <input type="button" value="修改密码" data-toggle="modal" data-target="#updatePasswordModal"
                                       onclick="updatePassword(${account.id})">
                                <input type="button" value="删除" onclick="deleteAccount(${account.id})">
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
                        新增账户
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">账户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="username" placeholder="请输入账户名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="password" placeholder="请输入密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="surePassword" class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="surePassword" placeholder="请输入密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="realName" class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="realName" placeholder="输入真实姓名">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addAccount">
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
                        修改账户
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="accountId">
                        <div class="form-group">
                            <label for="uName" class="col-sm-2 control-label">账户名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="uName" placeholder="请输入账户名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="realName" class="col-sm-2 control-label">真实姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="rName" placeholder="输入真实姓名">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateAccount">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%--修改密码--%>
    <div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog"
         aria-labelledby="updatePasswordModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">X
                    </button>
                    <h4 class="modal-title" id="updatePasswordModalLabel">
                        修改密码
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="aid">
                        <div class="form-group">
                            <label for="newPassword" class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" id="newPassword" placeholder="请输入新密码">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updatePassword">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
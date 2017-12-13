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
            $("#addMenu").click(function () {
                var name = $.trim($("#menuName").val());
                var url = $.trim($("#menuUrl").val());
                var pid = $("#pid").val();
                if (name == null || name == '') {
                    alert("请输入菜单名");
                    return;
                }
                if (url == '') {
                    url = null;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveMenu",
                    method: "post",
                    dataType: "text",
                    data: {
                        pid: pid,
                        name: name,
                        url: url
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改
            $("#updateMenu").click(function () {
                var id = $("#menuId").val();
                var name = $.trim($("#mName").val());
                var url = $.trim($("#mUrl").val());
                var pid = $("#parentId").val();
                if (name == null || name == '') {
                    alert("请输入菜单名");
                    return;
                }
                if (url == '') {
                    url = null;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateMenu",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        parentId: pid,
                        name: name,
                        url: url
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

        });

        //删除
        function deleteMenu(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteMenu",
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
                url: "${pageContext.request.contextPath}/findMenuById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#menuId").val(id);
                    var name = JSON.parse(result).name;
                    $("#mName").val(name);
                    var url = JSON.parse(result).url;
                    $("#mUrl").val(url);
                    var parentId = JSON.parse(result).parentId;
                    $("#parentId").val(parentId);
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
                    style="width: 100px;height: 42px;">添加菜单
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                        <thead>
                        <tr class="replace-inputs">
                            <th>序号</th>
                            <th>菜单id</th>
                            <th>菜单名</th>
                            <th>菜单链接</th>
                            <th>父级菜单</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${menuList}" var="menu" varStatus="menuIndex">
                            <tr>
                                <td>${menuIndex.index+1}</td>
                                <td>${menu.id}</td>
                                <td>${menu.name}</td>
                                <td>${menu.url}</td>
                                <td class="center">${menu.pName}</td>
                                <td class="center">
                                    <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                           onclick="transferParameter(${menu.id})">
                                    <input type="button" value="删除" onclick="deleteMenu(${menu.id})">
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
                        新增菜单
                    </h4>
                    </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="menuName" class="col-sm-2 control-label">菜单名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="menuName" placeholder="请输入菜单名称">
                                </div>
                        </div>

                        <div class="form-group">
                            <label for="menuUrl" class="col-sm-2 control-label">菜单链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="menuUrl" placeholder="请输入菜单链接">
                                </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">父级菜单</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="pid">
                                    <option value="0">--暂无父级菜单--</option>
                                    <c:forEach items="${parentMenus}" var="menu">
                                        <option value="${menu.id}">${menu.name}</option>
                                    </c:forEach>
                                </select>
                                </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addMenu">
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
                        修改菜单
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="menuId">
                        <div class="form-group">
                            <label for="mName" class="col-sm-2 control-label">菜单名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="mName" placeholder="请输入菜单名称">
                                </div>
                        </div>

                        <div class="form-group">
                            <label for="mUrl" class="col-sm-2 control-label">菜单链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="mUrl" placeholder="请输入菜单链接">
                                </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">父级菜单</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="parentId">
                                    <option value="0">--暂无父级菜单--</option>
                                    <c:forEach items="${parentMenus}" var="menu">
                                        <option value="${menu.id}">${menu.name}</option>
                                    </c:forEach>
                                </select>
                                </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateMenu">
                        确定
                    </button>
                </div>
                </div>
            </div>
        </div>
</div>

</body>
</html>
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

    <title>部门管理</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);

            //新增
            $("#addDepartment").click(function () {
                var department = $.trim($("#department").val());
                var remark = $.trim($("#remark").val());
                if (department == null || department == '') {
                    alert("请输入部门");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/saveDepartment",
                    method: "post",
                    dataType: "text",
                    data: {
                        department: department,
                        remark: remark
                    },
                    success: function (result) {
                        if (result == 1) {
                            window.location.reload();
                        } else {
                            alert("部门已存在");
                        }
                    }
                });

            });

            //修改
            $("#updateDepartment").click(function () {
                var id = $("#depId").val();
                var department = $.trim($("#depName").val());
                var remark = $.trim($("#depRemark").val());
                if (department == null || department == '') {
                    alert("请输入部门");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateDepartment",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        department: department,
                        remark: remark
                    },
                    success: function (result) {
                        if (result == 0) {
                            alert("部门重复，请重新输入");
                        } else {
                            window.location.reload();
                        }
                    }
                });
            });

        });

        //删除
        function deleteDepartment(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteDepartment",
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
                url: "${pageContext.request.contextPath}/findDepartmentById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#depId").val(id);
                    var department = JSON.parse(result).department;
                    $("#depName").val(department);
                    var remark = JSON.parse(result).remark;
                    $("#depRemark").val(remark);
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
                    style="width: 100px;height: 42px;">新增部门
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>部门</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${departments}" var="department" varStatus="depIndex">
                        <tr>
                            <td>${depIndex.index+1}</td>
                            <td>${department.department}</td>
                            <td>${department.remark}</td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${department.id})">
                                <input type="button" value="删除" onclick="deleteDepartment(${department.id})">
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
                        新增部门
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="department" class="col-sm-2 control-label">部门</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="department" placeholder="请输入部门">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="remark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="remark">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addDepartment">
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
                        修改部门
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="depId">
                        <div class="form-group">
                            <label for="depName" class="col-sm-2 control-label">部门</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="depName" placeholder="请输入部门">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="depRemark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="depRemark">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateDepartment">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
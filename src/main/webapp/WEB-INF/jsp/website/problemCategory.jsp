<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Xenon Boostrap Admin Panel"/>
    <meta name="author" content=""/>

    <title>百问百答</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);


            //新增
            $("#addProblemCategory").click(function () {
                var category = $.trim($("#category").val());
                var descript = $.trim($("#descript").val());
                if (category == null || category == '') {
                    alert("请输入分类名");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveProblemCategory",
                    method: "post",
                    dataType: "text",
                    data: {
                        category: category,
                        descript: descript
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改
            $("#updateProblemCategory").click(function () {
                var id = $("#pcId").val();
                var category = $.trim($("#pCategory").val());
                var descript = $.trim($("#pDesc").val());
                if (category == null || category == '') {
                    alert("请输入分类名");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateProblemCategory",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        category: category,
                        descript: descript
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

        });

        //删除
        function deleteProblemCategory(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteProblemCategory",
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
                url: "${pageContext.request.contextPath}/findProblemCategoryById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#pcId").val(id);
                    var category = JSON.parse(result).category;
                    $("#pCategory").val(category);
                    var descript = JSON.parse(result).descript;
                    $("#pDesc").val(descript);
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
                    style="width: 135px;height: 42px;">添加问题分类
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>分类名</th>
                        <th>描述</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${problemCategorys}" var="problemCategory" varStatus="pcIndex">
                        <tr>
                            <td>${pcIndex.index+1}</td>
                            <td>${problemCategory.category}</td>
                            <td>${problemCategory.descript}</td>
                            <td><fmt:formatDate value="${problemCategory.createTime }"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${problemCategory.id})">
                                <input type="button" value="删除" onclick="deleteProblemCategory(${problemCategory.id})">
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
                        新增问题分类
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="category" class="col-sm-2 control-label">分类名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="category" placeholder="请输入分类名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="descript" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="descript" placeholder="请输入描述">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addProblemCategory">
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
                        修改问题分类
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="pcId">
                        <div class="form-group">
                            <label for="pCategory" class="col-sm-2 control-label">分类名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="pCategory" placeholder="请输入分类名">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pDesc" class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="pDesc" placeholder="请输入描述">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateProblemCategory">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
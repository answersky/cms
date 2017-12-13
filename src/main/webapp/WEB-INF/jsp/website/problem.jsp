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
    <script src="${pageContext.request.contextPath}/js/checkbox.js"></script>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);


            //新增
            $("#addProblem").click(function () {
                var problem = $.trim($("#problem").val());
                var answer = $.trim($("#answer").val());
                var recommend = $("#recommend").val();
                var categoryId = $("#categoryId").val();
                if (problem == null || problem == '') {
                    alert("请输入问题");
                    return;
                }
                if (answer == null || answer == '') {
                    alert("请输入回答");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveProblem",
                    method: "post",
                    dataType: "text",
                    data: {
                        categoryId: categoryId,
                        problem: problem,
                        answer: answer,
                        recommend: recommend
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改
            $("#updateProblem").click(function () {
                var id = $("#problemId").val();
                var problem = $.trim($("#pro").val());
                var answer = $.trim($("#pAnswer").val());
                var categoryId = $("#cId").val();
                var recommend = $("#reco").val();
                if (problem == null || problem == '') {
                    alert("请输入问题");
                    return;
                }
                if (answer == null || answer == '') {
                    alert("请输入回答");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateProblem",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        categoryId: categoryId,
                        problem: problem,
                        answer: answer,
                        recommend: recommend
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

        });

        //删除
        function deleteProblem(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteProblem",
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
                url: "${pageContext.request.contextPath}/findProblemById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#problemId").val(id);
                    var problem = JSON.parse(result).problem;
                    $("#pro").val(problem);
                    var answer = JSON.parse(result).answer;
                    $("#pAnswer").val(answer);
                    var categoryId = JSON.parse(result).categoryId;
                    $("#cId").val(categoryId);
                    var recommend = JSON.parse(result).recommend;
                    $("#reco").val(recommend);
                }
            });
        }

        function publish() {
            console.log("ids==>" + ids);
            if (ids.length < 1) {
                alert("至少选择一项");
                return
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/publishProblem",
                method: "post",
                dataType: "text",
                data: {
                    ids: JSON.stringify(ids)
                },
                success: function (result) {
                    window.location.reload();
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
                    style="width: 135px;height: 42px;">添加问题
            </button>
            <a href="${pageContext.request.contextPath}/problemCategory">
                <button class="btn btn-primary btn-lg" style="width: 135px;height: 42px;">
                    问题分类
                </button>
            </a>
            <button class="btn btn-primary btn-lg" onclick="publish()"
                    style="width: 135px;height: 42px;">发布
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>
                            <input type="checkbox" id="checkBox">
                        </th>
                        <th>序号</th>
                        <th>所属分类</th>
                        <th>问题</th>
                        <th style="width: 500px;">回答</th>
                        <th>是否推荐</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${problems}" var="problem" varStatus="fsIndex">
                        <tr>
                            <td>
                                <input type="checkbox" class="checkBox" value="${problem.id}">
                            </td>
                            <td>${fsIndex.index+1}</td>
                            <td>${problem.category}</td>
                            <td>${problem.problem}</td>
                            <td>
                                    ${problem.answer}
                            </td>
                            <td>${problem.recommend=="1"?"推荐":"不推荐"}</td>
                            <td>${problem.status=="1"?"草稿":"已发布"}</td>
                            <td><fmt:formatDate value="${problem.createTime }"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${problem.id})">
                                <input type="button" value="删除" onclick="deleteProblem(${problem.id})">
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
                        新增问题
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="categoryId">
                                    <c:forEach items="${problemCategorys}" var="problemCategory">
                                        <option value="${problemCategory.id}">${problemCategory.category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="problem" class="col-sm-2 control-label">问题</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="problem" placeholder="请输入问题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="answer" class="col-sm-2 control-label">回答</label>
                            <div class="col-sm-10">
                                <textarea style="resize: none;text-align: left;" class="form-control" id="answer">

                                </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否推荐</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="recommend">
                                    <option value="0">不推荐</option>
                                    <option value="1">推荐</option>
                                </select>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addProblem">
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
                        修改问题
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="problemId">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="cId">
                                    <c:forEach items="${problemCategorys}" var="problemCategory">
                                        <option value="${problemCategory.id}">${problemCategory.category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pro" class="col-sm-2 control-label">问题</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="pro" placeholder="请输入问题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pAnswer" class="col-sm-2 control-label">回答</label>
                            <div class="col-sm-10">
                                <textarea style="resize: none;text-align: left;" class="form-control" id="pAnswer">

                                </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否推荐</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="reco">
                                    <option value="0">不推荐</option>
                                    <option value="1">推荐</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateProblem">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
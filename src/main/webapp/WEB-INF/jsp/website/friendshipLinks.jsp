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

    <title>友情链接</title>
    <%@include file="../common/lib.jsp" %>
    <script src="${pageContext.request.contextPath}/js/checkbox.js"></script>
    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);


            //新增
            $("#addFriendshipLink").click(function () {
                var word = $.trim($("#word").val());
                var url = $.trim($("#friendLink").val());
                if (word == null || word == '') {
                    alert("请输入文本");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveFriendshipLink",
                    method: "post",
                    dataType: "text",
                    data: {
                        word: word,
                        url: url
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

            //修改
            $("#updateFriendshipLink").click(function () {
                var id = $("#linkId").val();
                var word = $.trim($("#friendWord").val());
                var url = $.trim($("#friendUrl").val());
                if (word == null || word == '') {
                    alert("请输入文本");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateFriendshipLink",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        word: word,
                        url: url
                    },
                    success: function (result) {
                        window.location.reload();
                    }
                });
            });

        });

        //删除
        function deleteFriendshipLink(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteFriendshipLink",
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
                url: "${pageContext.request.contextPath}/findFriendshipLinkById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#linkId").val(id);
                    var word = JSON.parse(result).word;
                    $("#friendWord").val(word);
                    var url = JSON.parse(result).url;
                    $("#friendUrl").val(url);
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
                url: "${pageContext.request.contextPath}/publishFriendshipLink",
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
                    style="width: 135px;height: 42px;">添加友情链接
            </button>
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
                        <th>文本</th>
                        <th>友情链接</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${friendshipLinks}" var="friendshipLink" varStatus="fsIndex">
                        <tr>
                            <td>
                                <input type="checkbox" class="checkBox" value="${friendshipLink.id}">
                            </td>
                            <td>${fsIndex.index+1}</td>
                            <td>${friendshipLink.word}</td>
                            <td>${friendshipLink.url}</td>
                            <td>${friendshipLink.status=='1'?"草稿":"已发布"}</td>
                            <td><fmt:formatDate value="${friendshipLink.createTime }"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${friendshipLink.id})">
                                <input type="button" value="删除" onclick="deleteFriendshipLink(${friendshipLink.id})">
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
                        新增友情链接
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="word" class="col-sm-2 control-label">文本</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="word" placeholder="请输入文本">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="friendLink" class="col-sm-2 control-label">友情链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="friendLink" placeholder="请输入友情链接">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addFriendshipLink">
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
                        <input type="hidden" id="linkId">
                        <div class="form-group">
                            <label for="friendWord" class="col-sm-2 control-label">文本</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="friendWord" placeholder="请输入文本">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="friendUrl" class="col-sm-2 control-label">友情链接</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="friendUrl" placeholder="请输入友情链接">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateFriendshipLink">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
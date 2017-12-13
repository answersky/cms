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

    <title>职位管理</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);

            //新增
            $("#addPosition").click(function () {
                var position = $.trim($("#position").val());
                var remark = $.trim($("#remark").val());
                if (position == null || position == '') {
                    alert("请输入职位");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/savePosition",
                    method: "post",
                    dataType: "text",
                    data: {
                        position: position,
                        remark: remark
                    },
                    success: function (result) {
                        if (result == 1) {
                            window.location.reload();
                        } else {
                            alert("职位已存在");
                        }
                    }
                });

            });

            //修改
            $("#updatePosition").click(function () {
                var id = $("#posId").val();
                var position = $.trim($("#posName").val());
                var remark = $.trim($("#posRemark").val());
                if (position == null || position == '') {
                    alert("请输入职位");
                    return;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/updatePosition",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        position: position,
                        remark: remark
                    },
                    success: function (result) {
                        if (result == 0) {
                            alert("职位重复，请重新输入");
                        } else {
                            window.location.reload();
                        }
                    }
                });
            });

        });

        //删除
        function deletePosition(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deletePosition",
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
                url: "${pageContext.request.contextPath}/findPositionById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#posId").val(id);
                    var position = JSON.parse(result).position;
                    $("#posName").val(position);
                    var remark = JSON.parse(result).remark;
                    $("#posRemark").val(remark);
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
                    style="width: 100px;height: 42px;">新增职位
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>职位</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${positions}" var="position" varStatus="posIndex">
                        <tr>
                            <td>${posIndex.index+1}</td>
                            <td>${position.position}</td>
                            <td>${position.remark}</td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${position.id})">
                                <input type="button" value="删除" onclick="deletePosition(${position.id})">
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
                        新增职位
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="position" class="col-sm-2 control-label">职位</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="position" placeholder="请输入职位">
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
                    <button type="button" class="btn btn-primary" id="addPosition">
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
                        修改职位
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="posId">
                        <div class="form-group">
                            <label for="posName" class="col-sm-2 control-label">职位</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="posName" placeholder="请输入职位">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="posRemark" class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="posRemark">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updatePosition">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
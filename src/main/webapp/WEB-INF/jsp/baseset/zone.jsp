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

    <title>区域管理</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);

            //新增
            $("#addZone").click(function () {
                var zone = $.trim($("#zone").val());
                if (zone == null || zone == '') {
                    alert("请输入区域");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/saveZone",
                    method: "post",
                    dataType: "text",
                    data: {
                        zone: zone
                    },
                    success: function (result) {
                        if (result == 1) {
                            window.location.reload();
                        } else {
                            alert("区域已存在");
                        }
                    }
                });

            });

        });

        //删除
        function deleteZone(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteZone",
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
                    style="width: 100px;height: 42px;">新增区域
            </button>
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>区域</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${zones}" var="zone" varStatus="zIndex">
                        <tr>
                            <td>${zIndex.index+1}</td>
                            <td>${zone.zone}</td>
                            <td class="center">
                                <input type="button" value="删除" onclick="deleteZone(${zone.id})">
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
                        新增区域
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="zone" class="col-sm-2 control-label">区域</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="zone" placeholder="请输入区域">
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addZone">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
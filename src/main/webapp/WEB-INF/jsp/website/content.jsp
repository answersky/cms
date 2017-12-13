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

    <title>网站内容设置</title>
    <%@include file="../common/lib.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/jq22.css">
    <script src="${pageContext.request.contextPath}/js/jq22.js"></script>
    <style>
        form label {
            float: left;
            line-height: 30px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/checkbox.js"></script>
    <script>
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);
        });

        function publish() {
            console.log("ids==>" + ids);
            if (ids.length < 1) {
                alert("至少选择一项");
                return
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/publishContent",
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

            <%--导航菜单--%>
            <div id='cssmenu'>
                <c:import url="nav.jsp"/>
            </div>
            <a href="${pageContext.request.contextPath}/addContent?categoryId=${categoryId}&client=${client}">
                <button class="btn btn-primary btn-lg"
                        style="width: 135px;height: 42px;float: right;">添加内容
                </button>
            </a>

            <button class="btn btn-primary btn-lg" onclick="publish()"
                    style="width: 135px;height: 42px;float: right;margin-right: 10px;">发布
            </button>
            <div class="panel-body">
                <%@include file="content_table.jsp" %>
            </div>
        </div>
        <!-- Main Footer -->

        <%--底部--%>
        <%@include file="../common/footer.jsp" %>
    </div>


</div>

</body>
</html>
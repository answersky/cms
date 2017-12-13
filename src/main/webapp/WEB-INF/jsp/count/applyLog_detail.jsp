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

    <title>贷款申请统计</title>
    <%@include file="../common/lib.jsp" %>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);
        })
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
            <div class="panel-body">
                <table class="table table-striped table-bordered" id="example-3">
                    <thead>
                    <tr class="replace-inputs">
                        <th>序号</th>
                        <th>来源页面</th>
                        <th>申请人姓名</th>
                        <th>联系方式</th>
                        <th>渠道</th>
                        <th>申请时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${applyLogs}" var="applyLog" varStatus="fbIndex">
                        <tr>
                            <td>${fbIndex.index+1}</td>
                            <td>${applyLog.resource}</td>
                            <td>${applyLog.username}</td>
                            <td>${applyLog.tel}</td>
                            <td>${applyLog.channel}</td>
                            <td><fmt:formatDate value="${applyLog.createTime }"
                                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
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

</div>

</body>
</html>
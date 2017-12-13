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

    <title>权限管理</title>
    <%@include file="../common/lib.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/easyui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/icon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/easyui/demo.css">

    <script type="text/javascript">
        $(document).ready(function () {
            $('#tt').tree({
                onCheck: function (node) {
                    var menuId = node.id;
                    var roleId = '${roleId}';
                    var checked = node.checked;
                    var children = node.children;
                    var menuIds = [];
                    if (typeof(children) == "undefined") {
                        menuIds.push(menuId);
                    } else {
                        //批量操作
                        for (var i = 0; i < children.length; i++) {
                            var id = children[i].id;
                            console.log(id);
                            menuIds.push(id);
                        }
                    }
                    if (checked) {
                        bindPermission(roleId, menuIds)
                    } else {
                        unbindPermission(roleId, menuIds);
                    }
                }
            });
        });

        function bindPermission(roleId, menuId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/savePermission",
                method: "post",
                dataType: "text",
                data: {
                    roleId: roleId,
                    menuIds: JSON.stringify(menuId)
                },
                success: function (result) {
                    if (result == 1) {
                        alert("分配权限成功");
                    }
                }
            });
        }

        function unbindPermission(roleId, menuId) {
            $.ajax({
                url: "${pageContext.request.contextPath}/removePermission",
                method: "post",
                dataType: "text",
                data: {
                    roleId: roleId,
                    menuIds: JSON.stringify(menuId)
                },
                success: function (result) {
                    if (result == 1) {
                        alert("解绑权限成功");
                    }
                }
            });
        }

        function changeTree(roleId) {
            window.location.href = "${pageContext.request.contextPath}/permission?roleId=" + roleId;
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
            <div class="panel-body">
                <div>
                    <label>角色</label>
                    <ul>
                        <c:forEach items="${roles}" var="role">
                            <li onclick="changeTree(${role.id})" style="cursor: pointer;margin-top:5px;">
                                <input type="button" value="${role.role}">
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <div class="easyui-panel" style="padding:5px">
                    <label>菜单权限</label>
                    <c:if test="${roleId!=null}">
                        <ul id="tt" class="easyui-tree"
                            data-options="url:'${pageContext.request.contextPath}/tree?roleId=${roleId}',method:'get',animate:true,checkbox:true"></ul>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Main Footer -->

        <%--底部--%>
        <%@include file="../common/footer.jsp" %>
    </div>

</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="table table-striped table-bordered" id="example-3">
    <thead>
    <tr class="replace-inputs">
        <th>
            <input type="checkbox" id="checkBox">
        </th>
        <th>序号</th>
        <th>ID</th>
        <th>标题</th>
        <th>类型</th>
        <th>发布者</th>
        <th>点击量</th>
        <th>发布时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${contents}" var="content" varStatus="contentIndex">
        <tr>
            <td>
                <input type="checkbox" class="checkBox" value="${content.id}">
            </td>
            <td>${contentIndex.index+1}</td>
            <td>${content.id}</td>
            <td>
                【${content.category}】
                    ${content.title}</td>
            <td>${content.contentType.type}</td>
            <td>${content.editor}</td>
            <td>0</td>
            <td><fmt:formatDate value="${content.createTime }"
                                pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>${content.status=='1'?"草稿":"已发布"}</td>
            <td class="center">
                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                       onclick="toUpatePage(${content.id})">
                <input type="button" value="删除" onclick="deleteContent(${content.id})">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    function toUpatePage(id) {
        var client = '${client}';
        window.location.href = "${pageContext.request.contextPath}/updatePage?id=" + id + "&client=" + client;
    }

    function deleteContent(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/deleteContent",
            method: "post",
            dataType: "text",
            data: {
                id: id
            },
            success: function (result) {
                if (result == "1") {
                    window.location.reload();
                }
            }
        })
    }
</script>
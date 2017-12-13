<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var basePath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/index.js"></script>
<script type="text/javascript">
    var editor = new UE.ui.Editor({initialFrameWidth: 910, initialFrameHeight: 200, zIndex: 1000});
    editor.render("editor");
</script>
<div>
    <textarea id="editor"></textarea>
</div>

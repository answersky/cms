<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

            <div class="panel-body">
                <form class="form-horizontal" role="form" id="picUpload" enctype="multipart/form-data">
                    <input type="hidden" id="contentId" value="${content.id}">
                    <input type="hidden" id="textId" value="${content.text.id}">
                    <div class="form-group">
                        <label>栏目</label>
                        <div class="col-sm-3" style="margin-left: 25px;">
                            <input type="text" class="form-control" placeholder="${menuNav}" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>标题</label>
                        <div class="col-sm-3" style="margin-left: 25px;">
                            <input type="text" id="title" class="form-control" placeholder="请输入标题"
                                   value="${content.title}">
                        </div>
                        <span style="color: red;font-size: 25px;">*</span>
                    </div>
                    <div class="form-group">
                        <label>短标题</label>
                        <div class="col-sm-3" style="margin-left: 12px;">
                            <input type="text" id="shortTitle" class="form-control" placeholder="请输入标题"
                                   value="${content.shortTitle}">
                        </div>
                        <span style="color: grey;font-size: 15px;line-height: 32px;">(在列表中显示，留空则显示完整标题)</span>
                    </div>
                    <div class="form-group">
                        <label>外部链接</label>
                        <div class="col-sm-3">
                            <input type="text" id="sourceUrl" class="form-control" placeholder="请输入文章来源链接"
                                   value="${content.text.sourceUrl}">
                        </div>
                        <span style="color: grey;font-size: 15px;line-height: 32px;">(链接格式以http或https开头)</span>
                    </div>
                    <div class="form-group">
                        <label>tag标签</label>
                        <div class="col-sm-3" style="margin-left: 8px;">
                            <input type="text" id="tags" class="form-control" placeholder="请输入tag标签以,号隔开"
                                   value="${content.tags}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>城市区域</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="zoneId">
                                <option value="">所有城市</option>
                                <c:forEach items="${zones}" var="zone">
                                    <option value="${zone.id}">${zone.zone}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>内容类型</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="typeId">
                                <c:forEach items="${types}" var="type">
                                    <option value="${type.id}">${type.type}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>图片上传</label>
                        <div class="col-sm-3">
                            <input type="file" name="file" id="pic" value="上传图片">
                        </div>
                        <input type="hidden" id="picId" value="${content.picture.id}">
                    </div>

                    <div class="form-group">
                        <label>图片预览</label>
                        <div class="col-sm-3" id="yulan"
                             style=" border:1px solid grey;margin-left: 15px;padding: 0;width: 250px;">
                            <img src="${pageContext.request.contextPath}/img/nopic.png"
                                 style="height: 150px;width: 100%;"
                                 onerror="javascript:this.src='${pageContext.request.contextPath}/img/nopic.png'">
                        </div>
                    </div>

                    <div class="form-group">
                        <label>图片链接</label>
                        <div class="col-sm-3">
                            <input type="text" id="picUrl" class="form-control" value="${content.picture.url}">
                        </div>
                        <span style="color: grey;font-size: 15px;line-height: 32px;">(链接格式以http或https开头)</span>
                    </div>
                    <div class="form-group">
                        <label>是否推荐</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="recommend">
                                <option value="0">不推荐</option>
                                <option value="1">推荐</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>文章摘要</label>
                        <div class="col-sm-3">
                            <textarea class="form-control" id="remark"
                                      style="resize: none;width: 100%;height: 50px;"><c:if
                                    test="${content.typeId==3}"><%--图片的描述--%>${fn:trim(content.picture.description)}</c:if><c:if
                                    test="${content.typeId!=3}">${fn:trim(content.text.remark)}</c:if></textarea>
                        </div>
                    </div>
                    <div class="form-group" style="float: left">
                        <label>贷款金额</label>
                        <div class="col-sm-3">
                            <input type="text" id="decorateMoney" placeholder="输入金额如：15万"
                                   value="${content.decorateMoney}">
                        </div>
                    </div>
                    <div class="form-group" style="float: left;margin-left: 20px;">
                        <label>期限</label>
                        <div class="col-sm-3">
                            <input type="text" id="decorateTime" placeholder="输入还款期限" value="${content.decorateTime}">
                        </div>
                    </div>
                    <div class="form-group" style="float: left;margin-left: 20px;">
                        <label>月还款金额</label>
                        <div class="col-sm-3">
                            <input type="text" id="monthRepayment" value="${content.monthRepayment}">
                        </div>
                    </div>
                    <div style="clear: both"></div>
                    <div class="form-group">
                        <label>活动时间</label>
                        <div class="col-sm-3" style="margin-left: 25px;">
                            <input type="text" id="activeTime" class="form-control" value="${content.activeTime}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>合作商家</label>
                        <div class="col-sm-3" style="margin-left: 25px;">
                            <input type="text" id="cooperativeBusiness" class="form-control"
                                   value="${content.cooperativeBusiness}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label>文章内容</label>
                        <div class="col-sm-10">
                            <%@include file="../ueditor/index.jsp" %>
                        </div>

                    </div>
                    <div class="modal-footer" style="text-align: left;">
                        <button type="button" class="btn btn-primary" id="updateContent">
                            确定
                        </button>
                        <button type="reset" id="cancelContent" class="btn btn-default"
                                style="color: #ffffff;background-color: #131415;border-color: #020202;margin-bottom: 10px;"
                                data-dismiss="modal">重置
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- Main Footer -->

        <%--底部--%>
        <%@include file="../common/footer.jsp" %>
    </div>


</div>

</body>
</html>
<script>
    $(document).ready(function () {
        $("#pic").change(function () {
            console.log("上传图片");

            var filepath = $(this).val();
            var extStart = filepath.lastIndexOf(".");
            var ext = filepath.substring(extStart, filepath.length).toLowerCase();
            if (ext != ".bmp" && ext != ".png" && ext != ".gif" && ext != ".jpg" && ext != ".jpeg") {
                alert("请上传正确的图片格式！");
                return;
            }

            $("#picUpload").ajaxSubmit({
                type: 'post',
                url: "${pageContext.request.contextPath}/upload/picUpload",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (data) {
                    var obj = JSON.parse(data);
                    console.log(obj);
                    $("#yulan").find("img").attr("src", obj['picUrl']);
                    $("#picId").val(obj['picId']);
                },
                error: function (data)//服务器响应失败处理函数
                {
                    alert("出错");
                }
            })
        });

        $("#cancelContent").click(function () {
            UE.getEditor('editor').execCommand("cleardoc");
        });


        //初始化下拉框数据
        var zoneId = '${content.zoneId}';
        $("#zoneId").val(zoneId);
        var typeId = '${content.typeId}';
        $("#typeId").val(typeId);
        var recommend = '${content.recommend}';
        $("#recommend").val(recommend);
        var picUrl = '${content.picture.path}';
        $("#yulan").find("img").attr("src", picUrl);

        //给富文本编辑器追加内容
        // 实例化编辑器
        var value = '${content.text.content}';
        var ue = UE.getEditor('editor');

        //异步回调
        ue.ready(function () {
            ue.execCommand('inserthtml', value);
        });


        $("#updateContent").click(function () {
            var client = '${client}';
            var categoryId = "${categoryId}";
            var id = $("#contentId").val();
            var textId = $("#textId").val();
            var title = $.trim($("#title").val());
            var shortTitle = $.trim($("#shortTitle").val());
            var sourceUrl = $.trim($("#sourceUrl").val());
            var tags = $.trim($("#tags").val());
            var zoneId = $("#zoneId").val();
            var typeId = $("#typeId").val();
            var picId = $("#picId").val();
            var picUrl = $.trim($("#picUrl").val());
            var recommend = $("#recommend").val();
            var remark = $.trim($("#remark").val());
            var decorateMoney = $.trim($("#decorateMoney").val());
            var decorateTime = $.trim($("#decorateTime").val());
            var monthRepayment = $.trim($("#monthRepayment").val());
            var activeTime = $.trim($("#activeTime").val());
            var cooperativeBusiness = $.trim($("#cooperativeBusiness").val());
            var content = $.trim(UE.getEditor('editor').getContent());
            if (title == null || title == '') {
                alert("请填写title");
                return;
            }
            if (sourceUrl != null && sourceUrl != '') {
                if (sourceUrl.indexOf("http://") < 0 && sourceUrl.indexOf("https://") < 0) {
                    alert("请填写正确的网址格式");
                    return;
                }
            }
            if (picUrl != null && picUrl != '') {
                if (picUrl.indexOf("http://") < 0 && picUrl.indexOf("https://") < 0) {
                    alert("请填写正确的网址格式");
                    return;
                }
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/updateContent",
                method: "post",
                dataType: "text",
                data: {
                    id: id,
                    textId: textId,
                    categoryId: categoryId,
                    title: title,
                    shortTitle: shortTitle,
                    sourceUrl: sourceUrl,
                    tag: tags,
                    zoneId: zoneId,
                    typeId: typeId,
                    picId: picId,
                    picUrl: picUrl,
                    recommend: recommend,
                    remark: remark,
                    decorateMoney: decorateMoney,
                    decorateTime: decorateTime,
                    monthRepayment: monthRepayment,
                    content: content,
                    client: client,
                    activeTime: activeTime,
                    cooperativeBusiness: cooperativeBusiness
                },
                success: function (result) {
                    if (result == "1") {
                        window.location.href = "${pageContext.request.contextPath}/contentList?categoryId=" + categoryId + "&client=" + client;
                    }
                }
            })

        });
    });
</script>
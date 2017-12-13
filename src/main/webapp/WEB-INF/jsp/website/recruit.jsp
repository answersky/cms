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

    <title>招聘信息</title>
    <%@include file="../common/lib.jsp" %>
    <script src="${pageContext.request.contextPath}/js/checkbox.js"></script>

    <script type="text/javascript">
        $(document).ready(function ($) {
            //初始化表格
            $("#example-3").dataTable().yadcf([]);

            //新增
            $("#addRecruit").click(function () {
                var type = $("#type").val();
                var departmentId = $("#departmentId").val();
                var positionId = $("#positionId").val();
                var zoneId = $("#zoneId").val();
                var title = $.trim($("#title").val());
                var duty = $.trim($("#duty").val());
                var jobRequire = $.trim($("#jobRequire").val());
                if (title == null || title == '') {
                    alert("请输入标题");
                    return;
                }
                if (duty == null || duty == '') {
                    alert("请输入岗位职责");
                    return;
                }
                if (jobRequire == null || jobRequire == '') {
                    alert("请输入任职要求");
                    return;
                }
                var reg = new RegExp("\n", "g");
                duty = duty.replace(reg, "<br>");
                jobRequire = jobRequire.replace(reg, "<br>");
                $.ajax({
                    url: "${pageContext.request.contextPath}/saveRecruit",
                    method: "post",
                    dataType: "text",
                    data: {
                        departmentId: departmentId,
                        positionId: positionId,
                        zoneId: zoneId,
                        title: title,
                        duty: duty,
                        jobRequire: jobRequire,
                        type: type
                    },
                    success: function (result) {
                        if (result == 1) {
                            window.location.reload();
                        } else {
                            alert("在当前区域该职位招聘信息已存在");
                        }
                    }
                });

            });

            //修改
            $("#updateRecruit").click(function () {
                var id = $("#recruitId").val();
                var type = $("#reType").val();
                var departmentId = $("#depId").val();
                var positionId = $("#posId").val();
                var zoneId = $("#zId").val();
                var title = $.trim($("#reTitle").val());
                var duty = $.trim($("#reDuty").val());
                var jobRequire = $.trim($("#reJobRequire").val());
                if (title == null || title == '') {
                    alert("请输入标题");
                    return;
                }
                if (duty == null || duty == '') {
                    alert("请输入岗位职责");
                    return;
                }
                if (jobRequire == null || jobRequire == '') {
                    alert("请输入任职要求");
                    return;
                }
                var reg = new RegExp("\n", "g");
                duty = duty.replace(reg, "<br>");
                jobRequire = jobRequire.replace(reg, "<br>");
                $.ajax({
                    url: "${pageContext.request.contextPath}/updateRecruit",
                    method: "post",
                    dataType: "text",
                    data: {
                        id: id,
                        departmentId: departmentId,
                        positionId: positionId,
                        zoneId: zoneId,
                        title: title,
                        duty: duty,
                        jobRequire: jobRequire,
                        type: type
                    },
                    success: function (result) {
                        if (result == 0) {
                            alert("重复，请重新输入");
                        } else {
                            window.location.reload();
                        }
                    }
                });
            });

        });

        //删除
        function deleteRecruit(id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/deleteRecruit",
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
                url: "${pageContext.request.contextPath}/findRecruitById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    var id = JSON.parse(result).id;
                    $("#recruitId").val(id);
                    var departmentId = JSON.parse(result).departmentId;
                    $("#depId").val(departmentId);
                    var positionId = JSON.parse(result).positionId;
                    $("#posId").val(positionId);
                    var zoneId = JSON.parse(result).zoneId;
                    $("#zId").val(zoneId);
                    var title = JSON.parse(result).title;
                    $("#reTitle").val(title);
                    var duty = JSON.parse(result).duty;
                    $("#reDuty").val(duty);
                    var jobRequire = JSON.parse(result).jobRequirements;
                    $("#reJobRequire").val(jobRequire);
                    var type = JSON.parse(result).type;
                    $("#reType").val(type);
                }
            });
        }

        function showInfo(id, type) {
            $.ajax({
                url: "${pageContext.request.contextPath}/findDetailInfoById",
                method: "post",
                dataType: "text",
                data: {
                    id: id
                },
                success: function (result) {
                    if (type == 1) {
                        var duty = JSON.parse(result).duty;
                        $("#detail").html(duty);
                    } else {
                        var jobRequirement = JSON.parse(result).jobRequirements;
                        $("#detail").html(jobRequirement);
                    }
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
                url: "${pageContext.request.contextPath}/publishRecruit",
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
                    style="width: 125px;height: 42px;">新增招聘信息
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
                        <th>类型</th>
                        <th>职位</th>
                        <th>部门</th>
                        <th>地区</th>
                        <th>标题</th>
                        <th>岗位职责</th>
                        <th>任职要求</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${recruits}" var="recruit" varStatus="reIndex">
                        <tr>
                            <td>
                                <input type="checkbox" class="checkBox" value="${recruit.id}">
                            </td>
                            <td>${reIndex.index+1}</td>
                            <td>
                                <c:if test="${recruit.type=='1'}">社招</c:if>
                                <c:if test="${recruit.type=='0'}">校招</c:if>
                            </td>
                            <td>${recruit.position}</td>
                            <td>${recruit.department}</td>
                            <td>${recruit.zone}</td>
                            <td>${recruit.title}</td>
                            <td>
                                <a href="#" data-toggle="modal" data-target="#detailModal"
                                   onclick="showInfo(${recruit.id},1)">岗位职责详情
                                </a>
                            </td>
                            <td>
                                <a href="#" data-toggle="modal" data-target="#detailModal"
                                   onclick="showInfo(${recruit.id},2)">任职要求详情
                                </a>
                            </td>
                            <td>
                                <c:if test="${recruit.status==1}">草稿</c:if>
                                <c:if test="${recruit.status==2}">已发布</c:if>
                            </td>
                            <td class="center">
                                <input type="button" value="修改" data-toggle="modal" data-target="#updateModal"
                                       onclick="transferParameter(${recruit.id})">
                                <input type="button" value="删除" onclick="deleteRecruit(${recruit.id})">
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
                        新增招聘信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">招聘类型</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="type">
                                    <option value="1">社招</option>
                                    <option value="0">校招</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">部门</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="departmentId">
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.id}">${department.department}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">职位</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="positionId">
                                    <c:forEach items="${positions}" var="position">
                                        <option value="${position.id}">${position.position}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">城市区域</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="zoneId">
                                    <c:forEach items="${zones}" var="zone">
                                        <option value="${zone.id}">${zone.zone}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">标题</label>
                            <div class="col-sm-8">
                                <input type="text" id="title" class="form-control" placeholder="请输入标题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">岗位职责</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" id="duty"
                                      style="resize: none;width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">任职要求</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" id="jobRequire"
                                      style="resize: none;width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addRecruit">
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
                        修改招聘信息
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <input type="hidden" id="recruitId">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">招聘类型</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="reType" readonly>
                                    <option value="1">社招</option>
                                    <option value="0">校招</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">部门</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="depId" readonly>
                                    <c:forEach items="${departments}" var="department">
                                        <option value="${department.id}">${department.department}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">职位</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="posId" readonly>
                                    <c:forEach items="${positions}" var="position">
                                        <option value="${position.id}">${position.position}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">城市区域</label>
                            <div class="col-sm-5">
                                <select class="form-control" id="zId" readonly>
                                    <c:forEach items="${zones}" var="zone">
                                        <option value="${zone.id}">${zone.zone}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">标题</label>
                            <div class="col-sm-8">
                                <input type="text" id="reTitle" class="form-control" placeholder="请输入标题">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">岗位职责</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" id="reDuty"
                                      style="resize: none;width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">任职要求</label>
                            <div class="col-sm-8">
                            <textarea class="form-control" id="reJobRequire"
                                      style="resize: none;width: 100%;height: 100px;"></textarea>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateRecruit">
                        确定
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%--岗位职责详情弹框--%>
    <div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">X
                    </button>
                    <h4 class="modal-title" id="detailModalLabel">
                        详情
                    </h4>
                </div>
                <div class="modal-body" id="detail">

                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>
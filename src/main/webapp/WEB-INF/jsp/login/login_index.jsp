<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Xenon Boostrap Admin Panel"/>
    <meta name="author" content=""/>

    <title>cms后台</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/assets/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/xenon-core.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/xenon-forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/xenon-components.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/xenon-skins.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/assets/css/custom.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/xw.ico" type="image/x-icon">

    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/assets/jquery.validate.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            // Set Form focus
            $("form#login .form-group:has(.form-control):first .form-control").focus();


            //登陆校验
            $("#loginButton").click(function () {
                var username = $.trim($("#username").val());
                var password = $.trim($("#passwd").val());
                if (username == null || username == '') {
                    alert("请输入用户名");
                    return false;
                }

                if (password == null || password == '') {
                    alert("请输入密码");
                    return false;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/login",
                    method: "post",
                    dataType: "text",
                    data: {
                        username: username,
                        password: password
                    },
                    success: function (result) {
                        console.log("=======" + result);
                        if ("success" == result) {
                            //todo 临时页面
                            window.location.href = "${pageContext.request.contextPath}/menu";
                        } else {
                            alert(result);
                        }
                    }
                });

            })


        });
    </script>

</head>
<body class="page-body login-page">
<div class="login-container">

    <div class="row">

        <div class="col-sm-6">

            <!-- Errors container -->
            <div class="errors-container">


            </div>

            <!-- Add class "fade-in-effect" for login form effect -->
            <form method="post" role="form" id="login" class="login-form fade-in-effect in">

                <div class="login-header">
                    <p>欢迎登陆cms后台!</p>
                </div>

                <div class="form-group">
                    <input type="text" placeholder="Username" class="form-control input-dark" name="username"
                           id="username" autocomplete="off"/>
                </div>

                <div class="form-group">
                    <input type="password" placeholder="password" class="form-control input-dark" name="password"
                           id="passwd" autocomplete="off"/>
                </div>

                <div class="form-group">
                    <button type="button" id="loginButton" class="btn btn-dark  btn-block text-left">
                        <i class="fa-lock"></i>
                        Log In
                    </button>
                </div>

            </form>
        </div>

    </div>

</div>
</body>
</html>
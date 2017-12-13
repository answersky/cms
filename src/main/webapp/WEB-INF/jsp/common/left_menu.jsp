<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="sidebar-menu toggle-others fixed">

    <div class="sidebar-menu-inner">
        <header class="logo-env">

            <!-- logo -->
            <div class="logo">
                <img src="${pageContext.request.contextPath}/css/img/user-4.png" alt="user-image"
                     class="img-circle img-inline userpic-32" width="35"/>
                <span style="color: #ffffff;margin-left:10px;">${sessionScope.username}</span>
            </div>
        </header>


        <ul id="main-menu" class="main-menu">
            <!-- add class "multiple-expanded" to allow multiple submenus to open -->
            <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
            <c:forEach items="${sessionScope.menuTree}" var="menu">
                <li class="active opened">
                    <c:if test="${menu.url!=null && menu.url!=''}">
                        <a href="${menu.url}">
                            <i class="linecons-cog"></i>
                            <span class="title">${menu.name}</span>
                        </a>
                    </c:if>
                    <c:if test="${menu.url==null || menu.url==''}">
                        <a href="javascript:void(0)">
                            <i class="linecons-cog"></i>
                            <span class="title">${menu.name}</span>
                        </a>
                    </c:if>

                    <c:if test="${menu.subMenu!=null}">
                        <ul>
                            <c:forEach items="${menu.subMenu}" var="subMenu">
                                <li class="active">
                                    <c:if test="${subMenu.url!=null && subMenu.url!=''}">
                                        <a href="${subMenu.url}">
                                            <span class="title">${subMenu.name}</span>
                                        </a>
                                    </c:if>
                                    <c:if test="${subMenu.url==null || subMenu.url==''}">
                                        <a href="javascript:void(0)">
                                            <span class="title">${subMenu.name}</span>
                                        </a>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                </li>
            </c:forEach>

        </ul>

    </div>

</div>
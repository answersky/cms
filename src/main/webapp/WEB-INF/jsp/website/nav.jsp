<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<ul>
    <li><a href='#'>首页</a></li>
    <li class='has-sub'><a href='#'>网页特效</a>
        <ul>
            <li class='has-sub'><a href='#'>jQuery特效</a>
                <ul>
                    <li><a href='#'>HTML5</a></li>
                    <li><a href='#'>CSS3</a></li>
                </ul>
            </li>
            <li class='has-sub'>
                <a href='#'>JS代码</a>
                <ul>
                    <li class='has-sub'>
                        <a href='#'>Nav2</a>
                        <ul>
                            <li><a href='#'>HTML5</a></li>
                            <li><a href='#'>CSS3</a></li>
                        </ul>
                    </li>
                    <li><a href='#'>Nav3</a></li>
                </ul>
            </li>
        </ul>
    </li>
    <li><a href='#'>jQuery</a></li>
    <li><a href='#'>flash动画</a></li>

</ul>--%>

<ul style="z-index: 20;">
    <c:forEach items="${navs}" var="nav">
        <c:if test="${nav.children!=null}">
            <li class='has-sub'>
                <a href='#'>${nav.name}</a>
                <c:set var="navs" value="${nav.children}" scope="request"/>
                <c:import url="nav.jsp"/>
            </li>
        </c:if>
        <c:if test="${nav.children==null}">
            <li>
                <a href='${pageContext.request.contextPath}/contentList?categoryId=${nav.id}&client=${client}'>${nav.name}</a>
            </li>
        </c:if>
    </c:forEach>
</ul>
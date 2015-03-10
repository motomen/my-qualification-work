<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 26.01.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="page.header"></spring:message></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <sec:authorize access="isAnonymous()">
        <jsp:include page="frames/menu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="frames/adminmenu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <jsp:include page="frames/usermenu.jsp"/>
    </sec:authorize>
</div>

<!-- Page Content -->
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small><spring:message code="index.mainmessage"/>
                </small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <!-- Slider -->
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-2">
                <div class="carousel" id="myCarousel">
                    <div class="carousel-inner">
                        <% int i = 1; %>
                        <c:forEach items="${foodList}" var="food">
                            <% if (i == 1) {
                                i++;%>
                            <div class="item active">
                                <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                                    <div class="pull-left resize">
                                        <h3 class="title"><c:out value="${food.name}"/></h3>
                                        <img class="img-thumbnail resize" name="myImg"
                                             src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                                    </div>
                                </a>
                            </div>
                            <% } else {%>
                            <div class="item">
                                <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                                    <div class="pull-left resize">
                                        <h3 class="title"><c:out value="${food.name}"/></h3>
                                        <img class="img-thumbnail resize" name="myImg"
                                             src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                                    </div>
                                </a>
                            </div>
                            <%}%>
                        </c:forEach>
                    </div>
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                </div>
            </div>
        </div>
        <script>
            $('.carousel').carousel({
                interval: 5000
            })
        </script>
        <!-- End Slider -->
    </div>

    <div class="container">
        <h3>Останні коментарі</h3>

        <div class="carousel" id="myCarousel2">
            <div class="carousel-inner">
                <% i = 1; %>
                <c:forEach items="${commentList}" var="com">
                    <% if (i == 1) {
                        i++;%>
                    <div class="row item active media">
                        <div class="col-lg-8 col-lg-offset-1 well">
                            <a href="${pageContext.request.contextPath}/showfood/${com.food.idFood}">
                                <li class="media">
                                    <img class="media-object usercomment pull-left img-circle "
                                         src="data:image/jpg;base64,<c:out value='${com.user.photo}'/>">
                                    <div class="media-body">
                                        <h4 class="media-heading text-uppercase">
                                                ${com.user.nicname}
                                        </h4>
                                        <ul class="media-date text-uppercase list-inline">
                                            <li class="dd">
                                                <small><fmt:formatDate value="${com.dateComment}"
                                                                       pattern="dd/MM/yyyy HH:mm:ss"/></small>
                                            </li>
                                        </ul>

                                        <p class="media-body">
                                                ${com.textComment}
                                        </p>
                                    </div>
                                </li>
                            </a>
                        </div>
                    </div>
                    <% } else {%>
                    <div class="row item media">
                        <div class="col-lg-8 col-lg-offset-1 well">
                            <a href="${pageContext.request.contextPath}/showfood/${com.food.idFood}">
                                <li class="media">
                                    <img class="media-object usercomment pull-left img-circle "
                                         src="data:image/jpg;base64,<c:out value='${com.user.photo}'/>">

                                    <div class="media-body">
                                        <h4 class="media-heading text-uppercase">
                                                ${com.user.nicname}
                                        </h4>
                                        <ul class="media-date text-uppercase list-inline">
                                            <li class="dd">
                                                <small><fmt:formatDate value="${com.dateComment}"
                                                                       pattern="dd/MM/yyyy HH:mm:ss"/></small>
                                            </li>
                                        </ul>

                                        <p class="media-body">
                                                ${com.textComment}
                                        </p>
                                    </div>
                                </li>
                            </a>
                        </div>
                    </div>
                    <%}%>
                </c:forEach>
            </div>
        </div>
        <script>
            $('#myCarousel2').carousel({
                interval: 3000
            })
        </script>
    </div>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
        </div>
        <!-- /.row -->
    </footer>

</div>
<!-- /.container -->

</body>
</html>

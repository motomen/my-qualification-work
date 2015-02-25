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
    <script src="${pageContext.request.contextPath}/resources/js/jquery.liquid-slider.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap-responsive.min.css"
          rel="stylesheet">
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
            <div class="col-lg-6 offset1 well">
                <div class="carousel sizecarusel" id="myCarousel">
                    <div class="carousel-inner">
                        <% int i = 1; %>
                        <c:forEach items="${foodList}" var="food">
                            <% if (i == 1) {
                                i++;%>
                            <div class="item active media">
                                <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                                    <div class="pull-left resize">
                                        <h3 class="title"><c:out value="${food.name}"/></h3>
                                        <img class="img-thumbnail resize" name="myImg"
                                             src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                                    </div>

                                    <div class="media-body">
                                        <table border="1">
                                            <caption>Харчова цінність</caption>
                                            <tr>
                                                <th>Білки</th>
                                                <th>Жири</th>
                                                <th>Вуглеводи</th>
                                            </tr>
                                            <tr>
                                                <td>${food.protein}</td>
                                                <td>${food.fats}</td>
                                                <td>${food.carbs}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </a>
                            </div>
                            <% } else {%>
                            <div class="item media">
                                <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                                    <div class="pull-left resize">
                                        <h3 class="title"><c:out value="${food.name}"/></h3>
                                        <img class="img-thumbnail resize" name="myImg"
                                             src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                                    </div>

                                    <div class="media-body">
                                        <table border="1">
                                            <caption>Харчова цінність</caption>
                                            <tr>
                                                <th>Білки</th>
                                                <th>Жири</th>
                                                <th>Вуглеводи</th>
                                            </tr>
                                            <tr>
                                                <td>${food.protein}</td>
                                                <td>${food.fats}</td>
                                                <td>${food.carbs}</td>
                                            </tr>
                                        </table>
                                    </div>
                                </a>
                            </div>
                            <%}%>
                        </c:forEach>
                    </div>
                    <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                    <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
                </div>
                <script>
                    $('.carousel').carousel({
                        interval: 5000
                    })
                </script>
                <!-- End Slider -->
            </div>
        </div>
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

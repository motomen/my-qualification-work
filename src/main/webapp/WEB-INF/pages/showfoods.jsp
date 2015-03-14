<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 14.03.2015
  Time: 16:27
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
    <title></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
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


<div class="container">
    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small>Пошук
                </small>
            </h1>
        </div>
    </div>
    <!-- /.row -->
    <div class="row">
        <h3 align="center">Знайдено: <b> ${foodList.size()} </b></h3>

        <div class="col-lg-10 col-lg-offset-1">
            <c:forEach items="${foodList}" var="food">
                <div class="col-lg-2 block-foods">
                    <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                        <h3 class="title"><c:out value="${food.name}"/></h3>

                        <div class="con">
                            <img class="img-thumbnail resize" name="myImg"
                                 src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                            <img src="${pageContext.request.contextPath}/resources/images/<c:out value="${food.rating}"/>.jpg"
                                 class="tops">
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <jsp:include page="frames/foter.jsp"/>
</div>
</body>
</html>

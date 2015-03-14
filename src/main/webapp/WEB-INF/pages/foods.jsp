<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.02.2015
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/resources/js/page_js/foods.js"></script>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
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
                <small><spring:message text="Перегляд"/>
                </small>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-2 col-md-2 well block-category" >
            <h3 class="title" align="center">Категорії</h3>
            <c:forEach items="${listCategory}" var="item">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2 category">
                        <a onclick="clicklink('${item.name}')" href="#">
                           <h4> <c:out value="${item.name}"/> </h4>
                            <img class="img-thumbnail category" name="myImg"
                                 src="data:image/jpg;base64,<c:out value='${item.photo}'/>">
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-lg-9 well block-subcategory">
            <h3 class="title" align="center">Підкатегорії</h3>

            <div class="row" id="placesubcategory">
                <jsp:include page="frames/subcategory.jsp"/>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="frames/foter.jsp"/>
</div>

</body>
</html>

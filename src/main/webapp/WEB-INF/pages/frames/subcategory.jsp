<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 05.02.2015
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.liquid-slider.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
</head>
<body>
<form>
    <c:forEach items="${listSubcategory}" var="item">
        <a href="foodsubcategory/${item.idSubCategory}">
            <div class="col-lg-2 col-md-3 well colorsubcategory">
                <h4 class="title" align="center">${item.name}</h4>
                <img class="img-thumbnail subcategory" height="100" width="100" name="myImg"
                     src="data:image/jpg;base64,<c:out value='${item.img}'/>">
            </div>
        </a>
    </c:forEach>
</form>
</body>
</html>

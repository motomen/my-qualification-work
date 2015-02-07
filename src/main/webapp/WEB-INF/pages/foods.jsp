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
<html>
<head>
    <title></title>
  <!-- Latest compiled and minified CSS -->
  <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
  <!-- Optional theme -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
  <!-- Latest compiled and minified JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="frames/menu.jsp"/>

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
    <div class="col-xs-6 col-md-4">
      <c:forEach items="${listCategory}" var="item">
        <div class="row">
          <div class="col-xs-8 col-sm-6">
            <h4 class="title"><c:out value="${item.name}"/></h4>
            <img class="img-thumbnail" height="100" width="100" name="myImg" src="data:image/jpg;base64,<c:out value='${item.photo}'/>" >
          </div>
        </div>
      </c:forEach>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-8" id="placesubcategory">

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

</body>
</html>

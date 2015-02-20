<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 14.02.2015
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><spring:message code="menu.main"/> </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/foods"><spring:message code="admin.menu.food"/> </a></li>
                <li><a href="/control/addfood"><spring:message code="admin.menu.addfood"/> </a></li>
                <li><a href="/control/foodtocategory"><spring:message code="admin.menu.foodtocategory"/> </a></li>
                <li><a href="/control/addcategory"><spring:message code="admin.menu.addcategory"/> </a></li>
                <li><a href="/control/addsubcategory"><spring:message code="admin.menu.addsubcategory"/> </a></li>
                <li><a href="/j_spring_security_logout"><spring:message code="admin.menu.logout"/> </a> </li>
            </ul>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="<spring:message code="menu.search"/>">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
</body>
</html>

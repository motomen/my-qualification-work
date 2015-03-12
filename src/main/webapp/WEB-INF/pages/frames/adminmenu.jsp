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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
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
                <li><a href="/calc"><spring:message code="menu.calc"/></a> </li>
            </ul>
            <div class="col-lg-3">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="<spring:message code="menu.search"/>">
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Go!</button>
                  </span>
                </div><!-- /input-group -->
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
</body>
</html>

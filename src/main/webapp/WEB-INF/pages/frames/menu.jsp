<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 31.12.2014
  Time: 1:24
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
<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" id="header-color">
            <a class="navbar-brand" href="/"><spring:message code="menu.main"/> </a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/foods"><spring:message code="menu.food"/></a>
                </li>
                <li>
                    <a href="/login"><spring:message code="menu.login"/></a>
                </li>
                <li>
                    <a href="/registration"><spring:message code="menu.registration"/></a>
                </li>
                <li>
                    <a href="/about"><spring:message code="menu.about"/></a>
                </li>
                <li>
                    <a href="/feedback"><spring:message code="menu.fedback"/></a>
                </li>
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
    <!-- /.container -->
</nav>
</body>
</html>

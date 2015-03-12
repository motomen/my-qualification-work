<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 31.01.2015
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="container">
  <div class="row">
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
</div>
<div class="container">
  <!-- Page Header -->
  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">
        </br>
        <small><spring:message text="Про сервіс"/>
        </small>
      </h1>
    </div>
  </div>
  <!-- /.row -->
  <div class="row">
    <h1>Soon...</h1>
  </div>
</div>
<jsp:include page="frames/foter.jsp"/>
</body>
</html>

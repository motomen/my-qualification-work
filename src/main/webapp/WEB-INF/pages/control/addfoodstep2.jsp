<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 15.02.2015
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="menu.main"/></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="../frames/adminmenu.jsp"/>
    </sec:authorize>
</div>

<div class="container">
    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small><spring:message code="admin.page.vitamin"/></small>
                </br>
                <h3 class="color"><spring:message code="admin.page.message"/></h3>
            </h1>
        </div>
    </div>

    <!-- form vitamin -->
    <div class="row">
        <form:form action="/category/addfodstep2" method="post" modelAttribute="VitaminAdd" id="form"
                   commandName="Vitamin"
                   class="form-horisontal">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="form-group">
                    <label for="b1"><spring:message code="admin.vitamin.b1"/> </label>
                    <form:input type="text" class="form-control" id="b1" path="b1" name="b1" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b2"><spring:message code="admin.vitamin.b2"/> </label>
                    <form:input type="text" class="form-control" id="b2" path="b2" name="b2" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b3"><spring:message code="admin.vitamin.b3"/> </label>
                    <form:input type="text" class="form-control" id="b1" path="b3" name="b3" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b5"><spring:message code="admin.vitamin.b5"/> </label>
                    <form:input type="text" class="form-control" id="b5" path="b5" name="b5" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b6"><spring:message code="admin.vitamin.b6"/> </label>
                    <form:input type="text" class="form-control" id="b6" path="b6" name="b6" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b9"><spring:message code="admin.vitamin.b9"/> </label>
                    <form:input type="text" class="form-control" id="b9" path="b9" name="b9" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b12"><spring:message code="admin.vitamin.b12"/> </label>
                    <form:input type="text" class="form-control" id="b12" path="b12" name="b12" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="c"><spring:message code="admin.vitamin.c"/> </label>
                    <form:input type="text" class="form-control" id="c" path="c" name="c" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="b10"><spring:message code="admin.vitamin.b10"/> </label>
                    <form:input type="text" class="form-control" id="b10" path="b10" name="b10" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="e"><spring:message code="admin.vitamin.e"/> </label>
                    <form:input type="text" class="form-control" id="e" path="e" name="e" placeholder=""/>
                </div>

                <input type="submit" name="submit" id="submit" value="<spring:message code="admin.button.nextpage"/>" class="btn btn-info pull-right">
            </div>
        </form:form>
    </div>
    <!-- end form vitamin -->

    <!-- Footer -->
    <jsp:include page="../frames/foter.jsp"/>
</div>
</body>
</html>

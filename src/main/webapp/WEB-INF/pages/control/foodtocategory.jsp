<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 20.02.2015
  Time: 3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><spring:message code="menu.main"/></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
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
        <div class="col-lg-6 col-lg-offset-3">
            <h1 class="page-header">
                </br>
                <small><spring:message text="Додати їжу"/></small>
            </h1>
        </div>
    </div>


    <div class="row">
        <form:form action="/control/foodtocategory" method="POST" id="form" commandName="food" class="form-horizontal"
                   enctype="multipart/form-data">
            <div class="col-lg-6 col-lg-offset-3">
                <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="namecategory"><spring:message text="Виберіть підкатегорію"/> </label>

                    <div class="input-group">
                        <select name="namecategory" id="namecategory">
                            <c:forEach items="${subcategory}" var="item">
                                <option><c:out value="${item.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="food"><spring:message text="Виберіть Їжу"/> </label>

                    <div class="input-group">
                        <select name="food" id="food">
                            <c:forEach items="${foods}" var="item">
                                <option><c:out value="${item.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
            </div>
        </form:form>
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

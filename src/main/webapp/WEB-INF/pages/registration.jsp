<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 27.01.2015
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
</head>
<body>

<div class="container">

    <jsp:include page="frames/menu.jsp"/>
    <c:if test="${not empty error}">
        <p style="color: red"> ${error}</p>
    </c:if>

    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-6 offset2">
            <h1 class="page-header">
                </br>
                <small><spring:message text="Зареєструватись в системі"/></small>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 col-lg-offset-3 well">
            <c:if test="${not empty error}">
                <p style="color: red"> ${error}</p>
            </c:if>
            <form:form action="/registration" method="POST" modelAttribute="user" id="form" commandName="user"
                       class="form"
                       enctype="multipart/form-data">

                <!-- <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="login"><spring:message text="Введіть логін"/> </label>
                    <form:input type="text" class="form-control" id="login" path="login" name="login"
                                placeholder=""/>
                            <span id="er_login" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="login"/>
                                </span>
                            </span>
                </div>

                <div class="form-group">
                    <label for="nicname"><spring:message text="Введіть нікнейм"/> </label>

                    <form:input type="text" class="form-control" id="nicname" path="nicname" name="nicname"
                                placeholder=""/>
                            <span id="er_nicname" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="nicname"/>
                                </span>
                            </span>

                <div class="form-group">
                    <label for="password"><spring:message text="Пароль"/></label>
                    <form:input type="password" class="form-control" id="password" path="password" name="password"
                                placeholder=""/>
                            <span id="er_password" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="password"/>
                                </span>
                            </span>
                </div>

                <div class="form-group">
                    <label for="email"><spring:message text="Почтова скринька"/></label>
                    <form:input type="email" class="form-control" id="email" path="mail" name="email"
                                placeholder="example@mail.ru"/>
                            <span id="er_email" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="mail"/>
                                </span>
                            </span>
                </div>

                <div class="form-group">
                    <form:input type="file" id="files" name="photo" path="photo"/>
                    <div id="imgreplace">
                        <img id="photouser" class="img-thumbnail" heiht="140" width="140"
                             src="${pageContext.request.contextPath}/resources/img/user.jpg"/>
                    </div>
                </div>
                <input type="submit" name="submit" id="submit" value="Створити"
                       class="btn-login btn btn-lg btn-primary btn-block">
            </form:form>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="frames/foter.jsp"/>

</div>

<script src="${pageContext.request.contextPath}/resources/js/page_js/replacepicture.js"></script>
</body>
</html>

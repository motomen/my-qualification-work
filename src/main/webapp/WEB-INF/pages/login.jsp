<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 10.02.2015
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="social" uri="http://www.springframework.org/spring-social/social/tags" %>
<html>
<head>
    <title></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/social-buttons-3.css" rel="stylesheet">

    <script src="https://cdn.auth0.com/js/lock-6.js"></script>
    <script src="//use.typekit.net/iws6ohy.js"></script>
    <script>try {
        Typekit.load();
    } catch (e) {
    }</script>
    <script src="${pageContext.request.contextPath}/resources/js/auth0-variables.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/app.js"></script>

</head>
<body>
<div class="container">
    <jsp:include page="frames/menu.jsp"/>
</div>
<!-- Page Content -->
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header" align="center">
                </br>
                <small>Увійти</small>
            </h1>
        </div>
    </div>
    <div class="row">

        <div class="col-lg-6 col-lg-offset-3 well">
            <form class="form-signin" id="form" action="/j_spring_security_check" method="POST">
                <label for="us_name">Логін</label>
                <input type="text" id="us_name" class="form-control" placeholder="" name="username" required
                       autofocus></br>
                <label for="us_pass">Пароль</label>
                <input type="password" id="us_pass" name="password" class="form-control" placeholder="" required>

                <div align="right" style="margin-top: 15px">
                    <label for="remember">Запомнить:</label>
                    <input type="checkbox" id="remember" name="_spring_security_remember_me">
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
            </form>
            </br>
            <span style="color:red">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>

            <h2 align="center">OR</h2>
            <!-- Auth0 login -->
            <!-- <input type="submit" class="btn-login btn btn-lg btn-primary btn-block"
                    value=" "/> -->
            <!-- Social Sign In Buttons -->
            <div class="panel panel-default">
                <div class="panel-body">
                    <h2><spring:message code="login.social.sign.in.title"/></h2>

                    <div class="row social-button-row">
                        <div class="col-lg-4">
                            <!-- Add Facebook sign in button -->
                            <a href="${pageContext.request.contextPath}/auth/facebook">
                                <button class="btn btn-facebook"><i class="icon-facebook"></i> | <spring:message
                                        code="label.facebook.sign.in.button"/></button>
                            </a>
                        </div>
                    </div>
                    <div class="row social-button-row">
                        <div class="col-lg-4">
                            <!-- Add Facebook sign in button -->
                            <a href="${pageContext.request.contextPath}/signup">
                                <button class="btn btn-facebook"><i class="icon-facebook"></i> | <spring:message
                                        code="label.facebook.sign.in.button"/></button>
                            </a>
                        </div>
                    </div>

                    <form id="fb_signin" action="<c:url value="/auth/facebook"/>" method="POST">
                        <input type="hidden" name="scope" value="publish_stream,offline_access">
                        <input type="submit" name="submitBtn" value="Connect">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <!-- Footer -->
    <jsp:include page="frames/foter.jsp"/>

</div>
<!-- /.container -->
</body>
</html>

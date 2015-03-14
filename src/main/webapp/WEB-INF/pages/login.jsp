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
<html>
<head>
    <title></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">


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
            <form class="form-signin" id="form" action="/j_spring_security_check" method="POST" onsubmit="function checkForm() {
    var u_login = document.forms['form']['us_name'].value; var pass = document.forms['form']['us_pass'].value;
      if (u_login == null || u_login == '') {document.forms['form']['us_name'].focus(); return false;}
      if (pass == null || pass == '') {document.forms['form']['us_name'].focus(); return false;}
      return true;
    }
    return checkForm()">
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
            <h2 align="center">OR</h2>
            <!-- Auth0 login -->
            <input type="submit" class="btn-login btn btn-lg btn-primary btn-block"
                   value="<spring:message code="login.btn.social"/> "/>
        </div>
    </div>
    <hr>
    <!-- Footer -->
    <jsp:include page="frames/foter.jsp"/>

</div>
<!-- /.container -->
</body>
</html>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>

  <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Optional theme -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css">

  <!-- Latest compiled and minified JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div  class="col-lg-4">

  <form class="form-signin"  id="form" action="/j_spring_security_check" method="POST" onsubmit="function checkForm() {
    var u_login = document.forms['form']['us_name'].value; var pass = document.forms['form']['us_pass'].value;
      if (u_login == null || u_login == '') {document.forms['form']['us_name'].focus(); return false;}
      if (pass == null || pass == '') {document.forms['form']['us_name'].focus(); return false;}
      return true;
    }
    return checkForm()">
    <h2 class="form-signin-heading">Увійти</h2>
    <label for="us_name">Логін</label>
    <input type="text" id="us_name" class="form-control" placeholder="Логін" name="j_username" required autofocus></br>
    <label for="us_pass">Пароль</label>
    <input type="password" id="us_pass" name="j_password" class="form-control" placeholder="Пароль" required>
    <div align="right" style="margin-top: 15px">
      <label for="remember" >Запам’ятати :</label>
      <input type="checkbox" id="remember" name="_spring_security_remember_me" required>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Увійти</button>
  </form>

</div> <!-- /container -->

<font color="red">
  <span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span>
</font>
</div>


</body>
</html>

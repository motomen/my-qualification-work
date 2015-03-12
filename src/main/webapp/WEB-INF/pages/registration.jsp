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
            <form:form action="/registration" method="POST" id="form" commandName="user" class="form"
                       enctype="multipart/form-data">

                <!-- <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="login"><spring:message text="Введіть логін"/> </label>
                    <form:input type="text" class="form-control" id="login" path="login" name="login"
                                placeholder=""/>
                            <span id="er_login" class="input-group-addon" style="visibility: hidden"><span
                                    class="glyphicon glyphicon-asterisk"><spring:message
                                    text="Логін не повинен бути порожнім"/> </span></span>
                </div>

                <div class="form-group">
                    <label for="nicname"><spring:message text="Введіть нікнейм"/> </label>

                    <form:input type="text" class="form-control" id="nicname" path="nicname" name="nicname"
                                placeholder=""/>
            <span id="er_nicname" class="input-group-addon" style="visibility: hidden"><span
                    class="glyphicon glyphicon-asterisk"><spring:message
                    text="Логін не повинен бути порожнім"/> </span></span>
                </div>

                <div class="form-group">
                    <label for="password"><spring:message text="Пароль"/></label>
                    <form:input type="password" class="form-control" id="password" path="password" name="password"
                                placeholder=""/>
                <span id="er_pass" class="input-group-addon" style="visibility: hidden"><span
                        class="glyphicon glyphicon-asterisk"><spring:message
                        text="Пароль не повинен бути порожнім"/> </span></span>
                </div>

                <div class="form-group">
                    <label for="email"><spring:message text="Почтова скринька"/></label>
                    <form:input type="text" class="form-control" id="email" path="mail" name="email"
                                placeholder=""/>
                </div>

                <div class="form-group">
                    <form:input type="file" id="files" name="photo" path="photo"/>
                    <div id="imgreplace">
                        <img id="photouser" class="img-thumbnail" heiht="140" width="140"
                             src="${pageContext.request.contextPath}/resources/img/user.jpg"/>
                    </div>
                    <script>
                        function handleFileSelect(evt) {
                            function changeImage(a) {
                                document.getElementById("photouser").src = a.src;
                            }

                            var files = evt.target.files; // FileList object
                            // Loop through the FileList and render image files as thumbnails.
                            for (var i = 0, f; f = files[i]; i++) {

                                // Only process image files.
                                if (!f.type.match('image.*')) {
                                    continue;
                                }

                                var reader = new FileReader();

                                // Closure to capture the file information.
                                reader.onload = (function (theFile) {
                                    return function (e) {
                                        // Render thumbnail.
                                        var content;
                                        content = "<img class=\"img-thumbnail\" height=\"140\" width=\"140\" src=\"" + e.target.result + "\"/>";
                                        $("#imgreplace").html(content);
                                    };
                                })(f);

                                // Read in the image file as a data URL.
                                reader.readAsDataURL(f);
                            }
                        }

                        document.getElementById('files').addEventListener('change', handleFileSelect, false);
                    </script>
                </div>
                <input type="submit" name="submit" id="submit" value="Створити" class="btn-login btn btn-lg btn-primary btn-block">
            </form:form>
        </div>
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

<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 03.02.2015
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
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
                <small><spring:message text="Додати категорію"/></small>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-6 col-lg-offset-3 well">
            <form:form action="/control/addcategory" method="POST" id="form" commandName="category" class="form-horizontal"
                       enctype="multipart/form-data">
                <div class="col-lg-6 col-lg-offset-3">
                    <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                    <div class="form-group">
                        <label for="name"><spring:message text="Введіть назву категорії"/> </label>

                        <div class="input-group">
                            <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder=""/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="description"><spring:message text="Введіть короткий опис категорії"/> </label>

                        <div class="input-group">
                            <form:input type="text" class="form-control" id="description" path="description"
                                        name="description" placeholder=""/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="input-group">
                            <form:input path="" type="file" id="files" name="file"/>
                            <output id="list"></output>
                            <script>
                                function handleFileSelect(evt) {
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
                                                var span = document.createElement('span');
                                                span.innerHTML = ['<img class="img-thumbnail" height="140" width="140" + src="', e.target.result,
                                                    '" title="', escape(theFile.name), '"/>'].join('');
                                                document.getElementById('list').insertBefore(span, null);
                                            };
                                        })(f);

                                        // Read in the image file as a data URL.
                                        reader.readAsDataURL(f);
                                    }
                                }

                                document.getElementById('files').addEventListener('change', handleFileSelect, false);
                            </script>
                        </div>
                    </div>

                    <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
                </div>
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

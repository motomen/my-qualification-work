<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.02.2015
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.Blob" %>
<%@ page import="org.springframework.security.crypto.codec.Base64" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Їжа для вас</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.liquid-slider.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
</head>
<body>
<div class="container">
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="../frames/adminmenu.jsp"/>
    </sec:authorize>
</div>

<!-- Page Content -->
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3">
            <h1 class="page-header">
                </br>
                <small><spring:message text="Додати субкатегорію"/></small>
            </h1>
        </div>
    </div>

    <div class="row">
        <form:form action="/control/addsubcategory" method="POST" id="form" commandName="subcategory"
                   class="form-horizontal"
                   enctype="multipart/form-data">
            <div class="col-lg-6 col-lg-offset-3">
                <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->

                <div class="form-group">
                    <label for="namecategory"><spring:message text="Виберіть Категорію"/> </label>

                    <div class="input-group">
                        <select name="namecategory" id="namecategory">
                            <c:forEach items="${listCategory}" var="item">
                                <option><c:out value="${item.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="name"><spring:message text="Введіть назву субкатегорії"/> </label>

                    <div class="input-group">
                        <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder=""/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <input type="file" id="files" name="file"/>
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
<!-- /.container -->

<!-- jQuery -->
<script src="/resources/bootstrap/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>

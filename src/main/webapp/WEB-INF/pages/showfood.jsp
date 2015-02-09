<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 26.01.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<!-- Page Content -->
<div class="container">

    <div class="page-header">
        <jsp:include page="frames/menu.jsp"/>
    </div>

    <div class="row">

        <div class="col-lg-4">
            <h4> <c:out value="${food.name}"/> </h4>
            <img class="img-thumbnail" height="200" width="200" name="myImg"
                 src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
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
<!-- /.container -->

<!-- jQuery -->
<script src="/resources/bootstrap/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
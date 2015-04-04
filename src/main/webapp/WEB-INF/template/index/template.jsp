<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
    <title><spring:message code="page.header"/></title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">

    <!-- Autocomplete -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
</head>

<body>

<div class="container">
    <sec:authorize access="isAnonymous()">
        <tiles:insertAttribute name="menu"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <tiles:insertAttribute name="adminMenu"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <tiles:insertAttribute name="userMenu"/>
    </sec:authorize>
</div>

<!-- Page Content -->
<div class="container">
    <tiles:insertAttribute name="header"/>

    <tiles:insertAttribute name="body"/>

    <tiles:insertAttribute name="footer"/>
</div>

<script src="${pageContext.request.contextPath}/resources/js/viewportchecker.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        jQuery('.post').addClass("hiddens").viewportChecker({
            classToAdd: 'visibles animated fadeIn', // Class to add to the elements when they are visible
            offset: 100
        });
    });
</script>
<script>
    jQuery("#autocomplete").autocomplete({
        source: function (request, response) {
            jQuery.get("/search?input=" + request.term, function (data) {
                // assuming data is a JavaScript array such as
                // ["one@abc.de", "onf@abc.de","ong@abc.de"]
                // and not a string
                response(data);
                return data;
            });
        },
        minLength: 3
    });
</script>
</body>
</html>

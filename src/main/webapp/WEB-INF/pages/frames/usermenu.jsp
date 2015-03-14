<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 20.02.2015
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Autocomplete -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/"><spring:message code="menu.main"/> </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/foods"><spring:message code="admin.menu.food"/> </a></li>
                <li><a href="/j_spring_security_logout"><spring:message code="admin.menu.logout"/> </a></li>
                <li><a href="/calc"><spring:message code="menu.calc"/></a></li>
            </ul>
            <div class="col-lg-3">
                <form method="post" action="/search">
                    <div class="input-group">
                        <input id="autocomplete" type="text" name="name" class="form-control"
                               placeholder="<spring:message code="menu.search"/>">
                  <span class="input-group-btn">
                    <input class="btn btn-default" type="submit" value="Go!">
                  </span>
                    </div>
                </form>
                <!-- /input-group -->
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
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

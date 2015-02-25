<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 20.02.2015
  Time: 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="page.header"></spring:message></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.liquid-slider.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap-responsive.min.css"
          rel="stylesheet">
</head>
<body>
<div class="container">
    <sec:authorize access="isAnonymous()">
        <jsp:include page="frames/menu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="frames/adminmenu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <jsp:include page="frames/usermenu.jsp"/>
    </sec:authorize>
</div>

<div class="container">
    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small><spring:message text="show food"/>
                </small>
            </h1>
        </div>
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-8 offset1">
            <c:forEach items="${foodList}" var="food">
                <a href="/showfood/${food.idFood}">
                    <div class="media">
                        <img class="media-object pull-left img-thumbnail resize"
                             src="data:image/jpg;base64,<c:out value='${food.photo}'/>">

                        <div class="media-body">
                            <h4 class="media-heading">${food.name}</h4>
                            <table border="1">
                                <caption>Харчова цінність</caption>
                                <tr>
                                    <th>Білки</th>
                                    <th>Жири</th>
                                    <th>Вуглеводи</th>
                                </tr>
                                <tr>
                                    <td>${food.protein}</td>
                                    <td>${food.fats}</td>
                                    <td>${food.carbs}</td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>

    <!-- main
    <div class="column col-sm-13" id="main">
        <div class="padding">
            <div class="full col-sm-13">
                <div class="col-md-6 col-xs-6 follow line" align="center"><h3> ${page.list.size()} <br/>
                    <span><fmt:message key="foodsubcategory.count"/></span></h3></div>
                <c:if test="${page.list.size() > 0}">
                    <jsp:include page="frames/pagination.jsp"/>
                </c:if>

                <c:if test="${page.list.size() > 0}">
                    <jsp:include page="frames/pagination.jsp"/>
                </c:if>
            </div>
        </div>
        <hr>
    </div>
     /col-9 -->

</div>
</body>
</html>

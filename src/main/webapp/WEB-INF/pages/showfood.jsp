<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 26.01.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="menu.main"/></title>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/star-rating.min.css" media="all" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath}/resources/js/star-rating.min.js">
    </script>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/font-awesome/css/font-awesome.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap-responsive.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
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
<!-- Page Content -->
<div class="container">

    <!-- place main food information -->
    <div class="row">
        <div class="col-lg-4 well">
            <h4>Продукт</h4>
            <img class="img-thumbnail resize" name="myImg"
                 src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
            <input id="input-6a" class="rating" data-size="xs">
        </div>
        <div class="col-lg-6 well">
            <h4><c:out value="${food.name}"/></h4>
            <h5>Калорії: ${food.kcal}</h5>
        </div>
    </div>

    <!-- Main information fbout food -->
    <div class="row">
        <div class="col-lg-4 well">
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
        <div class="col-lg-6 well">
            <h4>Інгредієнти</h4>

            <p>
                ${food.ingredients}
            </p>
        </div>
    </div>
    <!-- End main information -->

    <!-- Alternative product -->

    <!-- End block alternative product -->

    <!-- Start place comments -->
    <div class="row">
        <div class="col-lg-8 well">
            ${count} Коментарів
        </div>
        <div class="col-lg-8 well">
            <div class="tab-content">
                <div class="tab-pane active" id="comments-logout">
                    <ul class="media-list">
                        <c:forEach items="${listComment}" var="com">
                            <li class="media">
                                <a class="pull-left" href="#">
                                    <img class="media-object img-circle usercomment"
                                         src="data:image/jpg;base64,<c:out value='${com.user.photo}'/>">
                                </a>

                                <div class="media-body">
                                    <h4 class="media-heading text-uppercase">
                                            ${com.user.nicname}
                                    </h4>
                                    <ul class="media-date text-uppercase list-inline">
                                        <li class="dd">
                                            <small><fmt:formatDate value="${com.dateComment}"
                                                                   pattern="dd/MM/yyyy HH:mm:ss"/></small>
                                        </li>
                                    </ul>

                                    <p class="media-body">
                                            ${com.textComment}
                                    </p>

                                    <!-- DELETE COMENT ONLY FOR ADMIN AND COMMENT OWNER-->
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <form id="del-f" method="post"
                                              action="/comments/${id}/delete/${com.idComment}">
                                            <a class="btn btn-danger btn-circle text-uppercase"
                                               onclick="document.forms['del-f'].submit()"><spring:message
                                                    code="function.delete"/></a>
                                        </form>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <c:if test="${com.user.login.toLowerCase() == pageContext.request.userPrincipal.name.toLowerCase()}">
                                            <form id="df" method="post"
                                                  action="/comments/${id}/delete/${com.idComment}">
                                                <a class="btn btn-danger btn-circle text-uppercase"
                                                   onclick="document.forms['df'].submit()"><spring:message
                                                        code="function.delete"/></a>
                                            </form>
                                        </c:if>
                                    </sec:authorize>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <sec:authorize access="isAuthenticated()">
            <div class="row">
                <div class="col-lg-8">
                    <h4><spring:message code="showfood.comment.title"/></h4>
                    <form:form id="add-c" method="post" action="/addcomment/${id}" modelAttribute="comment"
                               commandName="comment"> <!-- END -->
                        <form:errors cssClass="label label-danger"/>
                        <form:textarea id="comm-text" class="form-control" rows="3" name="textComment"
                                       path="textComment"/>

                        </br>
                        <p align="right">
                            <button type="submit" class="btn btn-primary"><spring:message
                                    code="showfood.button.comment"/></button>
                        </p>
                    </form:form>
                </div>
            </div>
        </sec:authorize>
    </div>
    <!-- end place comment -->

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
</body>
</html>
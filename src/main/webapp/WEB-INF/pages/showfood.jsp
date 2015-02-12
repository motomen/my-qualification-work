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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Їжа для вас</title>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/star-rating.min.css" media="all" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath}/resources/js/star-rating.min.js">
    </script>
    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/scripts/bootstrap/css/bootstrap-responsive.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
</head>
<body>

<!-- Page Content -->
<div class="container">

    <div class="page-header">
        <jsp:include page="frames/menu.jsp"/>
    </div>

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
            sdfsdf
        </div>
        <sec:authorize access="isAuthenticated()">
            <div class="row">
                <div class="col-lg-8">
                    <h4>Додати коментар</h4>
                    <form:form id="add-c" method="post" action="/reviews/${post.slug}/newComment"> <!-- END -->
                        <form:errors cssClass="label label-danger"/>
                        <textarea id="comm-text" class="form-control" rows="3"/>
                        </br>
                        <p align="right">
                            <button type="submit" class="btn btn-primary">Коментувати</button>
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
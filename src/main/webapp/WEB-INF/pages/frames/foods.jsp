<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 20.02.2015
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

</head>
<body>
<div>
    <c:forEach items="${page.list}" var="food">
        <div class="col-lg-2 block-foods">
            <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                <h3 class="title"><c:out value="${food.name}"/></h3>

                <div class="con">
                    <img class="img-thumbnail resize" name="myImg"
                         src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                    <img src="${pageContext.request.contextPath}/resources/images/<c:out value="${food.rating}"/>.jpg"
                         class="tops">
                </div>
            </a>
        </div>
    </c:forEach>
</div>
</body>
</html>

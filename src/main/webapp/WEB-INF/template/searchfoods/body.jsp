<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- /.row -->
<div class="row">
  <h3 align="center">Знайдено: <b> ${foodList.size()} </b></h3>

  <div class="col-lg-10 col-lg-offset-1">
    <c:forEach items="${foodList}" var="food">
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
</div>

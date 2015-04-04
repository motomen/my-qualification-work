<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
  <div class="col-lg-2 col-md-2 well block-category" >
    <h3 class="title" align="center">Категорії</h3>
    <c:forEach items="${listCategory}" var="item">
      <div class="row">
        <div class="col-lg-8 col-lg-offset-2 category">
          <a onclick="clicklink('${item.name}')" href="#">
            <h4> <c:out value="${item.name}"/> </h4>
            <img class="img-thumbnail category" name="myImg"
                 src="data:image/jpg;base64,<c:out value='${item.photo}'/>">
          </a>
        </div>
      </div>
    </c:forEach>
  </div>
  <div class="col-lg-9 well block-subcategory">
    <h3 class="title" align="center">Підкатегорії</h3>

    <div class="row" id="placesubcategory">
      <jsp:include page="/WEB-INF/pages/frames/subcategory.jsp"/>
    </div>
  </div>
</div>

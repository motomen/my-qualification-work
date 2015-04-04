<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="main">
  <div class="full col-lg-12">
    <div class="row">
      <c:if test="${page.list.size() > 0}">
        <jsp:include page="/WEB-INF/pages/frames/pagination.jsp"/>
      </c:if>
    </div>
    <div class="row">
      <jsp:include page="/WEB-INF/pages/frames/foods.jsp"/>
    </div>
    <div class="row">
      <c:if test="${page.list.size() > 0}">
        <jsp:include page="/WEB-INF/pages/frames/pagination.jsp"/>
      </c:if>
    </div>
  </div>
</div>

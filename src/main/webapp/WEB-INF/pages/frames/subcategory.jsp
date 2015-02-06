<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 05.02.2015
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.liquid-slider.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
<html>
<head>
    <title></title>
</head>
<body>
<form>
  <div class="form-group">
    <label for="subcategory"><spring:message text="Виберіть підкатегорію"/></label>
    <div class="input-group">
      <select name="subcategory" id="subcategory">
        <c:forEach items="${listSubcategory}" var="item">
          <option> <c:out value="${item.name}"/> </option>
        </c:forEach>
      </select>
    </div>
  </div>
</form>
</body>
</html>

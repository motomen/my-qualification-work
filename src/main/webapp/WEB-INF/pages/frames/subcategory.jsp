<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 05.02.2015
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="form-group">
  <label for="subcategory">Виберіть підкатегорію</label>
  <div class="input-group">
    <select name="subcategory" id="subcategory">
      <c:forEach items="${listSubcategory}" var="item">
        <option> <c:out value="${item.name}"/> </option>
      </c:forEach>
    </select>
  </div>
</div>
</body>
</html>

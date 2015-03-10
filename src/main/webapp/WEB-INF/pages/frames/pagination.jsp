<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 14.02.2015
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<!-- Pagination -->

<c:url var="firstUrl" value="/foodsubcategory/${idSubcategory}?p=1" />
<c:url var="lastUrl" value="/foodsubcategory/${idSubcategory}?=${page.end}" />
<c:url var="prevUrl" value="/foodsubcategory/${idSubcategory}?=${page.current - 1}" />
<c:url var="nextUrl" value="/foodsubcategory/${idSubcategory}?=${page.current + 1}" />
<div align="center">
  <nav >
    <ul class="pagination">
      <c:choose>
        <c:when test="${page.current == 1}">
          <li class="disabled"><a href="#">&lt;&lt;</a></li>
          <li class="disabled"><a href="#">&lt;</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${firstUrl}">&lt;&lt;</a></li>
          <li><a href="${prevUrl}">&lt;</a></li>
        </c:otherwise>
      </c:choose>
      <c:forEach var="i" begin="${page.begin}" end="${page.end}">
        <c:url var="pageUrl" value="/foodsubcategory/${idSubcategory}?p=${i}" />
        <c:choose>
          <c:when test="${i ==page.current}">
            <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
          </c:when>
          <c:otherwise>
            <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
          </c:otherwise>
        </c:choose>
      </c:forEach>
      <c:choose>
        <c:when test="${page.current == page.end}">
          <li class="disabled"><a href="#">&gt;</a></li>
          <li class="disabled"><a href="#">&gt;&gt;</a></li>
        </c:when>
        <c:otherwise>
          <li><a href="${nextUrl}">&gt;</a></li>
          <li><a href="${lastUrl}">&gt;&gt;</a></li>
        </c:otherwise>
      </c:choose>
    </ul>
  </nav>
</div>
<!-- /.row -->
</body>
</html>


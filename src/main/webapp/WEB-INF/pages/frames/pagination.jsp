<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
</head>
<body>
<!-- Pagination -->

<c:url var="firstUrl" value="/id${idSubcategory}?p=1" />
<c:url var="lastUrl" value="/id${idSubcategory}?=${page.end}" />
<c:url var="prevUrl" value="/id${idSubcategory}?=${page.current - 1}" />
<c:url var="nextUrl" value="/id${idSubcategory}?=${page.current + 1}" />
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
        <c:url var="pageUrl" value="/id${idSubcategory}?p=${i}" />
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


<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.04.2015
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row">
  <form:form action="/control/addsubcategory" method="POST" id="form" commandName="subcategory"
             class="form-horizontal"
             enctype="multipart/form-data">
    <div class="col-lg-6 col-lg-offset-3">
      <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->

      <div class="form-group">
        <label for="namecategory"><spring:message text="Виберіть Категорію"/> </label>

        <div class="input-group">
          <select name="namecategory" id="namecategory">
            <c:forEach items="${listCategory}" var="item">
              <option><c:out value="${item.name}"/></option>
            </c:forEach>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label for="name"><spring:message text="Введіть назву субкатегорії"/> </label>

        <div class="input-group">
          <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder=""/>
        </div>
      </div>

      <div class="form-group">
        <div class="input-group">
          <input type="file" id="files" name="file"/>
          <div id="imgreplace">
            <img id="photouser" class="img-thumbnail" heiht="140" width="140"
                 src="${pageContext.request.contextPath}/resources/img/user.jpg"/>
          </div>
        </div>
      </div>
      <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
    </div>
  </form:form>
</div>

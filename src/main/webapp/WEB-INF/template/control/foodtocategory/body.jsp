<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.04.2015
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row">
  <form:form action="/control/foodtocategory" method="POST" id="form" commandName="food" class="form-horizontal"
             enctype="multipart/form-data">
    <div class="col-lg-6 col-lg-offset-3 well">
      <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
      <div class="form-group">
        <label for="namecategory"><spring:message text="Виберіть підкатегорію"/> </label>

        <div class="input-group">
          <select name="namecategory" id="namecategory">
            <c:forEach items="${subcategory}" var="item">
              <option><c:out value="${item.name}"/></option>
            </c:forEach>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label for="food"><spring:message text="Id Їжі"/> </label>

        <div class="input-group">
          <input name="food" id="food" type="text">
        </div>
      </div>

      <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
    </div>
  </form:form>
</div>
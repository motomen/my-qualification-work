<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.04.2015
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="row">
  <div class="col-lg-6 col-lg-offset-3 well">
    <form:form action="/control/addcategory" method="POST" id="form" commandName="category" class="form-horizontal"
               enctype="multipart/form-data">
      <div class="col-lg-6 col-lg-offset-3">
        <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
        <div class="form-group">
          <label for="name"><spring:message text="Введіть назву категорії"/> </label>

          <div class="input-group">
            <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder=""/>
          </div>
        </div>

        <div class="form-group">
          <label for="description"><spring:message text="Введіть короткий опис категорії"/> </label>

          <div class="input-group">
            <form:input type="text" class="form-control" id="description" path="description"
                        name="description" placeholder=""/>
          </div>
        </div>

        <div class="form-group">
          <div class="input-group">
            <form:input path="" type="file" id="files" name="file"/>
            <div id="imgreplace">
              <img id="photouser" class="img-thumbnail" height="140" width="140"
                   src="${pageContext.request.contextPath}/resources/img/user.jpg"/>
            </div>
          </div>
        </div>

        <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
      </div>
    </form:form>
  </div>
</div>

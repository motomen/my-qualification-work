<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 23.04.2015
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <form:form action="/control/addingredient" method="POST" id="form" commandName="ingredient"
                   class="form-horizontal"
                   enctype="multipart/form-data">
            <div class="form-group">
                <label for="namecategory"><spring:message text="Виберіть тип інгредієнтів"/> </label>
                <select name="typeingredient" id="namecategory">
                    <c:forEach items="${listTypeIngredinets}" var="item">
                        <option><c:out value="${item.name}"/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="col-lg-6 col-lg-offset-3">
                <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="name"><spring:message text="Введіть назву інгредієнта"/> </label>
                    <form:input type="text" class="form-control" id="name" path="nameIngredient"
                                name="nameIngredient" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="description"><spring:message text="Введіть короткий опис інгредієнта "/> </label>
                    <form:textarea type="text" rows="5" class="form-control" id="description" path="description"
                                   name="description" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="bad"><spring:message text="0-корисний, 1-не корисний "/> </label>
                    <div class="input-group">
                        <form:checkbox id="bad" path="bad" name="bad"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">
                        <form:input path="" type="file" id="files" name="file"/>
                        <div id="imgreplace">
                            <img id="photouser" class="img-thumbnail" height="140" width="140"
                                 src="${pageContext.request.contextPath}/resources/img/ingredients.jpg"/>
                        </div>
                    </div>
                </div>

                <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
            </div>
        </form:form>
    </div>
</div>

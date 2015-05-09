<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 23.04.2015
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <form:form action="/control/linktoingredient" method="POST" id="form" commandName="link"
                   class="form-horizontal"
                   enctype="multipart/form-data">

            <div class="col-lg-6 col-lg-offset-3">
                <label for="ing_name"><c:out value="Ім’я інгредієнту"/> </label>
                <input type="text" id="ing_name" class="form-control" placeholder="" name="ingredient_name">
                <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="name"><spring:message text="Введіть назву посилання"/> </label>
                    <form:input type="text" class="form-control" id="name" path="name"
                                name="name" placeholder=""/>
                </div>

                <div class="form-group">
                    <label for="address"><spring:message text="Введіть адресу посилання"/> </label>
                    <form:input type="text" class="form-control" id="address" path="address"
                                name="address" placeholder=""/>
                </div>

                <input type="submit" name="submit" id="submit" value="Додати" class="btn btn-info pull-right">
            </div>
        </form:form>
    </div>
</div>



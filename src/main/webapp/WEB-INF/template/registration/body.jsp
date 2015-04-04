<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 well">
        <c:if test="${not empty error}">
            <p style="color: red"> ${error}</p>
        </c:if>
        <form:form action="/registration" method="POST" modelAttribute="user" id="form" commandName="user"
                   class="form"
                   enctype="multipart/form-data">

        <!-- <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
        <div class="form-group">
            <label for="login"><spring:message text="Введіть логін"/> </label>
            <form:input type="text" class="form-control" id="login" path="login" name="login"
                        placeholder=""/>
                            <span id="er_login" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="login"/>
                                </span>
                            </span>
        </div>

        <div class="form-group">
            <label for="nicname"><spring:message text="Введіть нікнейм"/> </label>

            <form:input type="text" class="form-control" id="nicname" path="nicname" name="nicname"
                        placeholder=""/>
                            <span id="er_nicname" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="nicname"/>
                                </span>
                            </span>

            <div class="form-group">
                <label for="password"><spring:message text="Пароль"/></label>
                <form:input type="password" class="form-control" id="password" path="password" name="password"
                            placeholder=""/>
                            <span id="er_password" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="password"/>
                                </span>
                            </span>
            </div>

            <div class="form-group">
                <label for="email"><spring:message text="Почтова скринька"/></label>
                <form:input type="email" class="form-control" id="email" path="mail" name="email"
                            placeholder="example@mail.ru"/>
                            <span id="er_email" class="input-group-addon">
                                <span class="glyphicon glyphicon-asterisk">
                                    <form:errors cssClass="label label-danger" path="mail"/>
                                </span>
                            </span>
            </div>

            <div class="form-group">
                <form:input type="file" id="files" name="photo" path="photo"/>
                <div id="imgreplace">
                    <img id="photouser" class="img-thumbnail" height="140" width="140"
                         src="${pageContext.request.contextPath}/resources/img/user.jpg"/>
                </div>
            </div>
            <input type="submit" name="submit" id="submit" value="Створити"
                   class="btn-login btn btn-lg btn-primary btn-block">
            </form:form>
        </div>
    </div>
</div>
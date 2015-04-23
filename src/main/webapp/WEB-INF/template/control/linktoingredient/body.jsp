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
        <form:form action="/control/linktoingredient/${nameIngredient}" method="POST" id="form" commandName="link"
                   class="form-horizontal"
                   enctype="multipart/form-data">

            <div class="col-lg-6 col-lg-offset-3">
                <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
                <div class="form-group">
                    <label for="name"><spring:message text="Введіть назву посилання"/> </label>

                    <div class="input-group">
                        <form:input type="text" class="form-control" id="name" path="name"
                                    name="name" placeholder=""/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="address"><spring:message text="Введіть адресу посилання"/> </label>

                    <div class="input-group">
                        <form:input type="text" class="form-control" id="address" path="address"
                                    name="address" placeholder=""/>
                    </div>
                </div>

                <input type="submit" name="submit" id="submit" value="Додати" class="btn btn-info pull-right">
            </div>
        </form:form>
    </div>
</div>

<div class="row">
    <div class="col-lg-3 well">
        <h2><c:out value='${ingredient.nameIngredient}'/></h2>
        <div class="row">
            <img class="img-thumbnail resize"
                 src="data:image/jpg;base64,<c:out value='${ingredient.photo}'/>">
        </div>
        <div class="row">
            <c:out value='${ingredient.description}'/>
        </div>
    </div>
    <c:if test="${linkList.size() > 0}">
        <div class="row">
            <h2>Список посиланнь</h2>

            <div class="col-lg-6 col-lg-offset-3 well">
                <c:forEach items="${linkList}" var="item">
                    <a href="<c:out value="${item.address}"/>"> <c:out value="${item.name}"/> </a>
                    <br>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${linkList.size() == 0}">
        <h2>Немає посиланнь</h2>
    </c:if>
</div>



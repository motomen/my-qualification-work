<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 21:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h4>Каклькулятор калорій по днях</h4>

<hr/>

<div class="well" style="overflow: auto">
    <div class="pull-left">
        <h4>
            Оберіть потрібний проміжок
        </h4>
    </div>
    <sec:authorize access="isAuthenticated()">
        <div id="reportrange" class="pull-right"
             style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
            <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
            <span></span> <b class="caret"></b>
        </div>
    </sec:authorize>
</div>

<div class="container" id="foods">
    <h4>Всього вжито калорій: <b> ${calories} </b></h4>
    <table class="table table-striped table-hover">
        <caption>Спожита їжа</caption>
        <tr>
            <th>Назва їжі</th>
            <th>Білки</th>
            <th>Жири</th>
            <th>Вуглеводи</th>
            <th>Калорії</th>
            <th>Вага</th>
            <th>Фото</th>
            <th>--------</th>
        </tr>
        <c:forEach items="${calc}" var="item">
            <tr>
                <td>${item.food.name}</td>
                <td>${item.food.protein / 100.0 * item.value}</td>
                <td>${item.food.fats / 100.0 * item.value}</td>
                <td>${item.food.carbs / 100.0 * item.value}</td>
                <td>${item.food.kcal / 100.0 * item.value}</td>
                <td>${item.value}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/showfood/${item.food.idFood}">
                        <img class="img-thumbnail smalls" name="myImg"
                             src="data:image/jpg;base64,<c:out value='${item.food.photo}'/>">
                    </a>
                </td>
                <td>
                    <a href="/calc/delete/${item.idCalc}" class="btn btn-xs btn-danger"><span
                            class="glyphicon glyphicon-trash"></span>
                        <spring:message code="function.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

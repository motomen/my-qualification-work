<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 25.04.2015
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<div class="container" id="foods">
    <table class="table table-striped">
        <caption>Список інгредієнтів</caption>
        <tr>
            <th>Назва</th>
            <th>Опис</th>
            <th>Фото</th>
            <th>action</th>
        </tr>
        <c:forEach items="${listIngredient}" var="item">
            <tr>
                <td>${item.nameIngredient}</td>
                <td>${item.description}</td>
                <td>
                    <img class="img-thumbnail smalls" name="myImg"
                         src="data:image/jpg;base64,<c:out value='${item.photo}'/>">
                </td>
                <td>
                    <a href="/control/ingredient/edit/${item.nameIngredient}" class="btn btn-sm btn-info"><span
                            class="glyphicon glyphicon-edit"></span>
                        <spring:message code="function.edit"/></a>
                    <br>
                    <a href="/control/ingredient/delete/${item.nameIngredient}" class="btn btn-sm btn-danger"><span
                            class="glyphicon glyphicon-trash"></span>
                        <spring:message code="function.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


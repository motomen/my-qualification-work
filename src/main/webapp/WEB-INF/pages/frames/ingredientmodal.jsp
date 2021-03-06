<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 24.04.2015
  Time: 0:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <h4 class="modal-title" id="ingredientModalLabel"><c:out value='${ingredient.nameIngredient}'/></h4>
    <c:if test="${ingredient.bad == false}">
        <h3 style="color: green"> Інгредієнт не шкідливий</h3>
    </c:if>

    <c:if test="${ingredient.bad == true}">
        <h3 style="color: red"> Інгредієнт шкідливий</h3>
    </c:if>
</div>

<div class="modal-body">
    <img class="img-thumbnail resize"
         src="data:image/jpg;base64,<c:out value='${ingredient.photo}'/>">

    <p>
        <c:out value='${ingredient.description}'/>
    </p>

    <c:if test="${ingredient.linkList.size() > 0}">
        <div class="row">
            <h3 align="center">Список посиланнь</h3>

            <div class="col-lg-10 col-lg-offset-1 well">
                <c:forEach items="${ingredient.linkList}" var="item">
                    <a href="<c:out value="${item.address}"/>"> <c:out value="${item.name}"/> </a>
                    <br>
                </c:forEach>
            </div>
        </div>
    </c:if>
    <c:if test="${ingredient.linkList.size() == 0}">
        <h2>Немає посиланнь</h2>
    </c:if>
</div>

<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div>

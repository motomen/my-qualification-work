<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 04.04.2015
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- place main food information -->
<sec:authorize access="isAuthenticated()">
    <div class="row">
        <div class="col-lg-1">
            <form class="form-signin" action="/control/editfood" method="POST">
                <input type="hidden" name="id" value="${food.idFood}"/>
                <button class="btn btn-sm btn-default button-edit" type="submit">Редагувати <span
                        class="glyphicon glyphicon-pencil"></span></button>
            </form>
        </div>
        <div class="col-lg-1 col-lg-offset-1">
            <form class="form-signin" action="/control/delete" method="POST">
                <input type="hidden" name="id" value="${food.idFood}"/>
                <button class="btn btn-sm btn-default button-delete" type="submit">Видалити <span
                        class="glyphicon glyphicon-trash"></span></button>
            </form>
        </div>

    </div>
</sec:authorize>
<div class="row well">
    <div class="col-lg-3">
        <h4>Продукт</h4>

        <div class="con">
            <img class="img-thumbnail resize" name="myImg"
                 src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
            <img src="${pageContext.request.contextPath}/resources/images/<c:out value="${food.rating}"/>.jpg"
                 class="tops">
        </div>
        <sec:authorize access="isAuthenticated()">
            <div id="jRate"></div>
            <div id="demo-onchange-value"></div>
            <script>
                $("#jRate").jRate({
                    rating: ${ratingUser},
                    width: 40,
                    height: 40,
                    onSet: function (rating) {
                        ratingclick(rating);
                    },
                    onChange: function (rating) {
                        $('#demo-onchange-value').text("Your Rating: " + rating);
                    },
                    shape: 'FOOD',
                    precision: 0
                });
            </script>
        </sec:authorize>
    </div>
    <div class="col-lg-7">
        <h5>Калорії: ${food.kcal}</h5>

        <div id="rating">
            <h4>rating: <fmt:formatNumber type="number"
                                          maxFractionDigits="2" value="${ratginFood}"/></h4>
        </div>
        <sec:authorize access="isAuthenticated()">
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                <spring:message code="showfood.popup.eat"/>
            </button>

            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">${food.name}</h4>
                        </div>
                        <div class="modal-body">
                            <!-- ---------------------------------------------------------------------------->
                            <form:form action="/calc/save/${food.idFood}" method="POST" id="form"
                                       commandName="calcFood"
                                       class="form-horizontal"
                                       enctype="multipart/form-data"
                                       modelAttribute="calcFood">
                                <div class="input-group">
                                    <label for="calcvalue"><spring:message code="showfood.popup.count"/> </label>
                                    <form:input id="calcvalue" path="value" type="text" class="form-control"/>
                                    <!-- /btn-group -->
                                </div>
                                <div id="calcinformation">

                                </div>
                                <input type="button" onclick="addcalcvalue()"
                                       value="<spring:message code="button.save" />"
                                       class="btn btn-info pull-right">
                            </form:form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message
                                    code="button.close"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>

<!-- Main information fbout food -->
<div class="row  well">
    <div class="col-lg-4 block-table">
        <table class="table table-hover">
            <caption>Харчова цінність</caption>
            <tr>
                <th>Білки</th>
                <th>Жири</th>
                <th>Вуглеводи</th>
            </tr>
            <tr>
                <td>${food.protein}</td>
                <td>${food.fats}</td>
                <td>${food.carbs}</td>
            </tr>
        </table>
    </div>
    <div class="col-lg-6">
        <h4>Інгредієнти</h4>

        <p>
            ${food.ingredients}
        </p>
    </div>
</div>
<!-- End main information -->

<!-- Alternative product -->

<!-- End block alternative product -->

<!-- Start place comments -->
<c:if test="${count == 0}">
    <div class="row">
        <div class="col-lg-8 well">
            Немає коментарів
        </div>
    </div>
</c:if>

<c:if test="${count > 0}">
    <div class="row">
        <div class="col-lg-8 well">
                ${count} Коментарів
        </div>
        <div class="col-lg-8 well">
            <div class="tab-content">
                <div class="tab-pane active" id="comments-logout">
                    <ul class="media-list">
                        <c:forEach items="${listComment}" var="com">
                            <li class="media block-comment">
                                <a class="pull-left" href="/showuser/${com.user.idUser}">
                                    <img class="media-object img-circle usercomment"
                                         src="data:image/jpg;base64,<c:out value='${com.user.photo}'/>">
                                </a>

                                <div class="media-body">
                                    <h4 class="media-heading text-uppercase">
                                            ${com.user.nicname}
                                    </h4>
                                    <ul class="media-date text-uppercase list-inline">
                                        <li class="dd">
                                            <small><fmt:formatDate value="${com.dateComment}"
                                                                   pattern="dd/MM/yyyy HH:mm:ss"/></small>
                                        </li>
                                    </ul>

                                    <p class="media-body">
                                            ${com.textComment}
                                    </p>

                                    <!-- DELETE COMENT ONLY FOR ADMIN AND COMMENT OWNER-->
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <form id="del-f" method="post"
                                              action="/comments/${id}/delete/${com.idComment}">
                                            <a class="btn btn-danger btn-circle text-uppercase"
                                               onclick="document.forms['del-f'].submit()"><spring:message
                                                    code="function.delete"/></a>
                                        </form>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <c:if test="${com.user.login.toLowerCase() == pageContext.request.userPrincipal.name.toLowerCase()}">
                                            <form id="df" method="post"
                                                  action="/comments/${id}/delete/${com.idComment}">
                                                <a class="btn btn-danger btn-circle text-uppercase"
                                                   onclick="document.forms['df'].submit()"><spring:message
                                                        code="function.delete"/></a>
                                            </form>
                                        </c:if>
                                    </sec:authorize>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="row">
    <sec:authorize access="isAuthenticated()">
        <div class="row">
            <div class="col-lg-8">
                <h4><spring:message code="showfood.comment.title"/></h4>
                <form:form id="add-c" method="post" action="/addcomment/${id}" modelAttribute="comment"
                           commandName="comment"> <!-- END -->
                    <form:errors cssClass="label label-danger"/>
                    <form:textarea id="comm-text" class="form-control" rows="3" name="textComment"
                                   path="textComment"/>

                    </br>
                    <p align="right">
                        <button type="submit" class="btn btn-primary"><spring:message
                                code="showfood.button.comment"/></button>
                    </p>
                </form:form>
            </div>
        </div>
    </sec:authorize>
</div>
<!-- end place comment -->

<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 08.04.2015
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="row">
    <div class="col-lg-2">
        <img class="img-thumbnail resize" name="myImg"
             src="data:image/jpg;base64,<c:out value='${user.photo}'/>">
        <h4>З нами з <fmt:formatDate value="${user.dateReg}"
                                     pattern="dd/MM/yyyy HH:mm:ss"/></h4>
    </div>
    <c:if test="${user.commentsList.size() == 0}">
        <div class="row">
            <div class="col-lg-8 well">
                Немає коментарів
            </div>
        </div>
    </c:if>
    <c:if test="${user.commentsList.size() > 0}">
        <div class="row">
            <div class="col-lg-8 well">
                    ${user.commentsList.size()} Коментарів
            </div>
            <div class="col-lg-8 well">
                <div class="tab-content">
                    <div class="tab-pane active" id="comments-logout">
                        <ul class="media-list">
                            <c:forEach items="${user.commentsList}" var="com">
                                <li class="media block-comment">
                                    <a class="pull-left" href="/showfood/${com.food.idFood}">
                                        <img class="media-object img-circle usercomment"
                                             src="data:image/jpg;base64,<c:out value='${com.food.photo}'/>">
                                    </a>

                                    <div class="media-body">
                                        <h4 class="media-heading text-uppercase">
                                                ${com.food.name}
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
                                                  action="/comments/${com.food.idFood}/delete/${com.idComment}">
                                                <a class="btn btn-danger btn-circle text-uppercase"
                                                   onclick="document.forms['del-f'].submit()"><spring:message
                                                        code="function.delete"/></a>
                                            </form>
                                        </sec:authorize>

                                        <sec:authorize access="hasRole('ROLE_USER')">
                                            <c:if test="${com.user.login.toLowerCase() == pageContext.request.userPrincipal.name.toLowerCase()}">
                                                <form id="df" method="post"
                                                      action="/comments/${com.food.idFood}/delete/${com.idComment}">
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
</div>

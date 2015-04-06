<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.04.2015
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<div class="row">
    <form:form action="/control/savefood/${food.idFood}" method="POST" id="form" commandName="food" class="form-horizontal"
               enctype="multipart/form-data">
        <div class="col-lg-6 col-lg-offset-3 well">
            <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
            <div class="form-group">
                <label for="namecategory"><spring:message text="Виберіть Категорію"/> </label>

                <div class="input-group">
                    <select name="namecategory" id="namecategory" onchange="changecategory()">
                        <c:forEach items="${listCategory}" var="item">
                            <option><c:out value="${item.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div id="listsubcategory">

                </div>
            </div>

            <div class="form-group">
                <label for="idFood"><spring:message text="Введіть ід продукту"/> </label>
                <form:input type="text" class="form-control" id="idFood" path="idFood" name="idFood"
                            placeholder=""/>
            </div>

            <div class="form-group">
                <label for="name"><spring:message text="Введіть ім’я"/> </label>
                <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder=""/>
            </div>

            <div class="form-group">
                <label for="protein"><spring:message text="Білки"/></label>
                <form:input type="text" class="form-control" id="protein" path="protein" name="protein"
                            placeholder=""/>
            </div>

            <div class="form-group">
                <label for="fats"><spring:message text="Жири"/></label>
                <form:input type="text" class="form-control" id="fats" path="fats" name="fats" placeholder=""/>
            </div>

            <div class="form-group">
                <label for="carbs"><spring:message text="Вуглеводи"/></label>
                <form:input type="text" class="form-control" id="carbs" path="carbs" name="carbs"
                            placeholder=""/>
            </div>

            <div class="form-group">
                <label for="kcal"><spring:message text="Кіло калорій в 100 гр."/></label>
                <form:input type="text" class="form-control" id="kcal" path="kcal" name="kcal" placeholder=""/>
            </div>

            <div class="form-group">
                <input type="file" id="files" name="file"/>

                <div id="imgreplace">
                    <img id="photouser" class="img-thumbnail" height="140" width="140"
                         src="data:image/jpg;base64,<c:out value='${food.photo}'/>"/>
                </div>
            </div>

            <div class="form-group">
                <label for="ingredients"><spring:message text="Інгредієнти"/></label>
                <form:textarea type="text" class="form-control" rows="5" id="ingredients" path="ingredients"
                               name="ingredients" placeholder=""/>
            </div>

            <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
        </div>
    </form:form>
    <script>
        function changecategory() {
            var name = $('#namecategory').val();
            //  var json = { "namecategory" : name};
            $.ajax({
                type: 'GET',
                url: "${pageContext.request.contextPath}/getsubcategory/" + name,
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (list) {
                    var respContent =
                            "<select name=" + "\"subcategory\" id=" + "\"subcategory\"" + ">";
                    jQuery.each(list, function (index, value) {
                        respContent += "<option>" + value.name + "</option>"
                    });
                    respContent += "</select>";
                    $("#listsubcategory").html(respContent);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status + ' ' + jqXHR.responseText);
                }
            });
        }
    </script>
</div>


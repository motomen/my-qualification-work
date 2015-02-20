<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.02.2015
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title></title>
    <!-- Latest compiled and minified CSS -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Optional theme -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.2.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">

    <script type="text/javascript">
//        $(document).ready(function () {
//
//
//
//            $('#some_ajax_link').click(function(){
//                $.ajax({
//                    type: "POST",
//                    /* insert a valid url here */
//                    url: "",
//                    data: { name: "John", location: "Boston" },
//                    beforeSend: function (xhr) {
//                        alert('yyy');
//                    },
//                    success: function (xhr) {
//                        alert('xxx');
//                    }
//                });
//            });
//        });

        function clicklink (name) {
            //disable all other links

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
                    var respContent = "";
                    var img = "";
                    jQuery.each(list, function (index, value) {
                        respContent += "<a href = \"foodsubcategory/" + value.idSubCategory + "\" >";
                        respContent += " <div class=\"col-lg-2 col-md-3 well colorsubcategory\"> ";
                        respContent += " <h4 class=\"title\" align=\"center\">" + value.name + "</h4> ";
                        img = value.img;
                        respContent += " <img class=\"img-thumbnail subcategory\" height=\"100\" width=\"100\" name=\"myImg\" src=\"data:image/jpg;base64,";
                        respContent += value.img + "\">";
                        respContent += " </div> ";
                        respContent += "</a>";
                    });
                    $("#placesubcategory").html(respContent);
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.status + ' ' + jqXHR.responseText);
                }

            });
        }
    </script>
</head>
<body>

<div class="container">
    <sec:authorize access="isAnonymous()" >
        <jsp:include page="frames/menu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="frames/adminmenu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <jsp:include page="frames/usermenu.jsp"/>
    </sec:authorize>
</div>

<div class="container">
    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small><spring:message text="Перегляд"/>
                </small>
            </h1>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-2 col-md-2 well ">
            <h3 class="title" align="center">Категорії</h3>
            <c:forEach items="${listCategory}" var="item">
                <div class="col-lg-8 col-md-4 category">
                    <a onclick="clicklink('${item.name}')" href="#">
                        <c:out value="${item.name}"/>
                    </a>
                    <img class="img-thumbnail category"  name="myImg"
                         src="data:image/jpg;base64,<c:out value='${item.photo}'/>">
                </div>

            </c:forEach>
        </div>
        <div class="col-lg-9 col-md-7 well">
            <h3 class="title" align="center">Підкатегорії</h3>

            <div class="row" id="placesubcategory">

            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; Your Website 2014</p>
            </div>
        </div>
        <!-- /.row -->
    </footer>
</div>

</body>
</html>

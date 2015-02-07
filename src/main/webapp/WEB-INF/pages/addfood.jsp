<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 01.02.2015
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title></title>
  <!-- Latest compiled and minified CSS -->
  <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
  <!-- Optional theme -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css">
  <!-- Latest compiled and minified JavaScript -->
  <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

  <script>
    $(document).ready(function() {

      $('#namecategory').change(function() {

        var name = $('#namecategory').val();

      //  var json = { "namecategory" : name};

        $.ajax({
          type: 'GET',
          url: "${pageContext.request.contextPath}/getsubcategory/" + name,
          dataType: 'json',
//          beforeSend: function(xhr) {
//            xhr.setRequestHeader("Accept", "application/json");
//            xhr.setRequestHeader("Content-Type", "application/json");
//          },
          success: function(list) {
            var respContent =
                    "<select name=" + "\"subcategory\" id=" + "\"subcategory\"" +">";
            jQuery.each(list, function(index, value){
              respContent += "<option>" + value.name + "</option>"
            });
            respContent += "</select>";
            $("#listsubcategory").html(respContent);
          },
          error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
          }
        });

       // event.preventDefault();
      });

    });
  </script>

</head>
<body>

<div class="container" >

  <jsp:include page="frames/menu.jsp"/>
  <c:if test="${not empty error}">
    <p style="color: red"> ${error}</p>
  </c:if>

  <!-- Page Header -->
  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">
        </br>
        <small><spring:message text="Додати їжу"/> </small>
      </h1>
    </div>
  </div>



  <div class="row">
    <form:form action="/addfood" method="POST" id="form" commandName="food" class="form-horizontal" enctype="multipart/form-data">
      <div class="col-lg-12">
        <!--   <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span></strong></div> -->
        <div class="form-group">
          <label for="namecategory"><spring:message text="Виберіть Категорію"/> </label>
          <div class="input-group">
            <select name="namecategory" id="namecategory">
              <c:forEach items="${listCategory}" var="item">
                <option> <c:out value="${item.name}"/> </option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-group">
          <div class="input-group">
            <div id = "listsubcategory">

            </div>
          </div>
        </div>

        <div class="form-group">
          <label for="idFood"><spring:message text="Введіть ід продукту"/> </label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="idFood" path="idFood" name="idFood" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <label for="name"><spring:message text="Введіть ім’я"/> </label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="name" path="name" name="name" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <label for="protein"><spring:message text="Білки"/></label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="protein" path="protein" name="protein" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <label for="fats"><spring:message text="Жири"/></label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="fats" path="fats" name="fats" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <label for="carbs"><spring:message text="Вуглеводи"/></label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="carbs" path="carbs" name="carbs" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <label for="kcal"><spring:message text="Кіло калорій в 100 гр."/></label>
          <div class="input-group">
            <form:input type="text" class="form-control" id="kcal" path="kcal" name="kcal" placeholder="" />
          </div>
        </div>

        <div class="form-group">
          <div class="input-group">
            <input type="file" id="files" name="file" />
            <div id="imgreplace">
              <img id="photouser" class="img-thumbnail" heiht="140" width="140" src="${pageContext.request.contextPath}/resources/img/user.jpg" />
            </div>
            <output id="list"></output>
            <script >
              function handleFileSelect(evt) {
                var files = evt.target.files; // FileList object

                // Loop through the FileList and render image files as thumbnails.
                for (var i = 0, f; f = files[i]; i++) {

                  // Only process image files.
                  if (!f.type.match('image.*')) {
                    continue;
                  }

                  var reader = new FileReader();

                  // Closure to capture the file information.
                  reader.onload = (function(theFile) {
                    return function(e) {
                      // Render thumbnail.
                      var content;
                      content = "<img class=\"img-thumbnail\" height=\"140\" width=\"140\" src=\""+ e.target.result+"\"/>";
                      $("#imgreplace").html(content);
                    };
                  })(f);

                  // Read in the image file as a data URL.
                  reader.readAsDataURL(f);
                }
              }

              document.getElementById('files').addEventListener('change', handleFileSelect, false);
            </script>
          </div>
        </div>

        <div class="form-group">
          <label for="ingredients"><spring:message text="Інгредієнти"/></label>
          <div class="input-group">
            <form:textarea type="text" class="form-control" rows="5" id="ingredients" path="ingredients" name="ingredients" placeholder="" />
          </div>
        </div>

        <input type="submit" name="submit" id="submit" value="Створити" class="btn btn-info pull-right">
      </div>
    </form:form>
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


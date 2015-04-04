<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
              data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/"><spring:message code="menu.main"/> </a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="/foods"><spring:message code="admin.menu.food"/> </a></li>
        <li><a href="/j_spring_security_logout"><spring:message code="admin.menu.logout"/> </a></li>
        <li><a href="/calc"><spring:message code="menu.calc"/></a></li>
      </ul>
      <div class="col-lg-3">
        <form method="post" action="/search">
          <div class="input-group">
            <input id="autocomplete" type="text" name="name" class="form-control"
                   placeholder="<spring:message code="menu.search"/>">
                  <span class="input-group-btn">
                    <input class="btn btn-default" type="submit" value="Go!">
                  </span>
          </div>
        </form>
        <!-- /input-group -->
      </div>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>

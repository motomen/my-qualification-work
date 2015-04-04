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
        <li>
          <a href="/foods"><spring:message code="menu.food"/></a>
        </li>
        <li>
          <a href="/login"><spring:message code="menu.login"/></a>
        </li>
        <li>
          <a href="/registration"><spring:message code="menu.registration"/></a>
        </li>
        <li>
          <a href="/about"><spring:message code="menu.about"/></a>
        </li>
        <li>
          <a href="/feedback"><spring:message code="menu.fedback"/></a>
        </li>
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

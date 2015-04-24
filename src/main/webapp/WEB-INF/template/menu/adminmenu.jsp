<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
                <li class="dropdown">
                    <a onclick="" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-expanded="false"><spring:message code="admin.menu.trogle"/><span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/control/addfood"><spring:message code="admin.menu.addfood"/> </a></li>
                        <li><a href="/control/foodtocategory"><spring:message code="admin.menu.foodtocategory"/> </a>
                        </li>
                        <li><a href="/control/addcategory"><spring:message code="admin.menu.addcategory"/> </a></li>
                        <li><a href="/control/addsubcategory"><spring:message code="admin.menu.addsubcategory"/> </a>
                        </li>
                        <li><a href="/control/addingredient"><spring:message code="admin.menu.addingredients"/> </a>
                        </li>
                        <li><a href="/control/addtypeingredient"><spring:message code="admin.menu.addtypeingredients"/> </a>
                        </li>
                        <li><a href="/control/linktoingredient"><spring:message code="admin.menu.linktoingredient"/> </a>
                        </li>
                    </ul>
                </li>

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
            </div>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>

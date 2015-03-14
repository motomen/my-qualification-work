<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 26.01.2015
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="page.header"></spring:message></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <sec:authorize access="isAnonymous()">
        <jsp:include page="frames/menu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="frames/adminmenu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <jsp:include page="frames/usermenu.jsp"/>
    </sec:authorize>
</div>


<!-- Page Content -->
<div class="container">

    <!-- Page Header -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                </br>
                <small><spring:message code="index.mainmessage"/>
                </small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <!-- Slider -->
    <div class="container">
        <div class="row">
            <%--<div class="col-lg-6 col-lg-offset-2">--%>
            <div class="carousel slide" id="myCarousel" data-ride="carousel" style="margin: 0 auto;">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="2" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="3" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="4" class=""></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <!-- Block items -->

                    <div class="item active">
                        <div class="container"
                             style="background: url(${pageContext.request.contextPath}/resources/images/carousel/slider1.jpg) center no-repeat; background-size: cover; width: 100%; height: 100%">
                            <div class="carousel-caption op">
                                <h1 style="color: #ffffff">Вітаю вас на сайті "Їжа для вас"</h1>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="container"
                             style="background: url(${pageContext.request.contextPath}/resources/images/carousel/slider2.jpg) center no-repeat; background-size: cover; width: 100%; height: 100%">
                            <div class="carousel-caption op">
                                <h1 style="color:#ffffff ">"Їжа для вас"</h1>

                                <p>Це сайт в якому ви можете знайти інформацію про будь-який товар в магазині</p>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="container"
                             style="background: url(${pageContext.request.contextPath}/resources/images/carousel/slider3.jpg) center no-repeat; background-size: cover; width: 100%; height: 100%">
                            <div class="carousel-caption op">
                                <h2 style="color:#ffffff ">"Їжа для вас"</h2>

                                <p>Всю інформацію про товари ми дбайливо перевіряємо, та заносимо в нашу базу даних</p>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="container"
                             style="background: url(${pageContext.request.contextPath}/resources/images/carousel/slider4.jpg) center no-repeat; background-size: cover; width: 100%; height: 100%">
                            <div class="carousel-caption op">
                                <h2 style="color:blue ">"Їжа для вас"</h2>

                                <p>Ви легко зможете уникати товарів шкідливих для вашого здоров’я</p>
                            </div>
                        </div>
                    </div>

                    <div class="item">
                        <div class="container"
                             style="background: url(${pageContext.request.contextPath}/resources/images/carousel/slider5.jpg) center no-repeat; background-size: cover; width: 100%; height: 100%">
                            <div class="carousel-caption op">
                                <h2 style="color:inherit ">"Їжа для вас"</h2>

                                <p>Здорова їжа завжди триматиме вас в гарному настрої</p>

                                <p><a class="btn btn-lg btn-primary" href="/foods" role="button">Завітайте до нашої
                                    галереї товарів</a></p>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="http://getbootstrap.com/examples/carousel/#myCarousel"
                   role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="http://getbootstrap.com/examples/carousel/#myCarousel"
                   role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <%--</div>--%>
        </div>

        <!-- End Slider -->
    </div>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Brand</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a onclick="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>



    <!-- recent product -->
    <div class="row">
        <div class="col-lg-3">
            <h1>Останні продукти</h1>

            <p>Перегляньте нашу базу даних, щоб знайти здорову їжу, йогурт або перекусити</p>
            <a href="/foods" class="btn btn-primary btn-success">Перегляд</a>
        </div>
        <div class="row">
            <c:forEach items="${foods}" var="food">
                <div class="col-lg-2 block-foods post">
                    <a href="${pageContext.request.contextPath}/showfood/${food.idFood}">
                        <h3 class="title"><c:out value="${food.name}"/></h3>

                        <div class="con">
                            <img class="img-thumbnail resize" name="myImg"
                                 src="data:image/jpg;base64,<c:out value='${food.photo}'/>">
                            <img src="${pageContext.request.contextPath}/resources/images/<c:out value="${food.rating}"/>.jpg"
                                 class="tops">
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <!-- /recent product -->

    <!-- about Grading System -->
    <div class="row">
        <div class="col-lg-12 well">
            <h1 style="align-content: center">Класифікація їжі</h1>

            <p>
                Всі продукти оцінюються за шкалою від 1 до 10. На основі панелі харчування, факти про продукт та список
                інгредієнтів. Мінімально оброблені, реальні продукти з власних поживних речовин матимуть кращу оцінку,
                ніж оброблені харчові продукти, які є бідними на поживні речовини.
            </p>
            <img src="${pageContext.request.contextPath}/resources/images/scorebar_large.png" class="img-rounded">
        </div>
    </div>
    <!-- end about Grading System -->
    <jsp:include page="frames/foter.jsp"/>

</div>
<!-- /.container -->

<script src="${pageContext.request.contextPath}/resources/js/viewportchecker.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        jQuery('.post').addClass("hiddens").viewportChecker({
            classToAdd: 'visibles animated fadeIn', // Class to add to the elements when they are visible
            offset: 100
        });
    });
</script>
</body>
</html>

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
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
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
<!-- /.container -->

</body>
</html>

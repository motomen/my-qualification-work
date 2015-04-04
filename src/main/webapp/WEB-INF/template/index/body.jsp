<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Page Content -->
<div class="container">
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
</div>
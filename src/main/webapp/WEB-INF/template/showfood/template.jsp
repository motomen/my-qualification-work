<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
    <title><spring:message code="page.header"/></title>
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- jQuery -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>

    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap-theme.css" rel="stylesheet">

    <!-- Autocomplete -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/jRate.js"></script>

    <script>
        function addcalcvalue() {
            var calcvalue = $('#calcvalue').val();
            $.ajax({
                url: "/calc/save/${food.idFood}/" + calcvalue,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (suc) {
                    if (suc) {
                        var resp = '<p style="color: #398439">Ви успішно зїли товар</p>';
                        $("#calcinformation").html(resp);
                    } else {
                        var resp = '<p style="color: red">Сталася помилка</p>';
                        $("#calcinformation").html(resp);
                    }
                }
            });
        }

        function ratingclick(rating) {
            $.ajax({
                type: 'GET',
                url: "/rating/" + rating + "/${id}",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                success: function (value) {
                    var respContent = "<h4>rating:" + value + "</h4>";
                    $("#rating").html(respContent);
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
    <sec:authorize access="isAnonymous()">
        <tiles:insertAttribute name="menu"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <tiles:insertAttribute name="adminMenu"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <tiles:insertAttribute name="userMenu"/>
    </sec:authorize>
</div>

<!-- Page Content -->
<div class="container">
    <tiles:insertAttribute name="header"/>

    <tiles:insertAttribute name="body"/>

    <tiles:insertAttribute name="footer"/>
</div>


<script>
    jQuery("#autocomplete").autocomplete({
        source: function (request, response) {
            jQuery.get("/search?input=" + request.term, function (data) {
                // assuming data is a JavaScript array such as
                // ["one@abc.de", "onf@abc.de","ong@abc.de"]
                // and not a string
                response(data);
                return data;
            });
        },
        minLength: 3
    });
</script>
<script>
    jQuery(document).ready(function () {
//        // wrap words in spans
//        $('p.highlight').each(function () {
//            var $this = $(this);
//            $this.html($this.text().replace(/\b(\w+)\b/g, "<span>$1</span>"));
//        });

        // bind to each span
        $('p.highlight span').hover(
                function () {
                    $(this).parent().find('span').css('background-color', '');
                    $(this).css('background-color', '#ffff66');
                });

        // this place for ajax request
        $('p.highlight span').click(
                function () {
                    showInfIngredient($(this).text());
                });

        function showInfIngredient(value) {
            alert(value);
            <%--$.ajax({--%>
                <%--url: "/calc/save/${food.idFood}/" + calcvalue,--%>
                <%--type: "POST",--%>
                <%--beforeSend: function (xhr) {--%>
                    <%--xhr.setRequestHeader("Accept", "application/json");--%>
                    <%--xhr.setRequestHeader("Content-Type", "application/json");--%>
                <%--},--%>
                <%--success: function (suc) {--%>
                    <%--if (suc) {--%>
                        <%--var resp = '<p style="color: #398439">Ви успішно зїли товар</p>';--%>
                        <%--$("#calcinformation").html(resp);--%>
                    <%--} else {--%>
                        <%--var resp = '<p style="color: red">Сталася помилка</p>';--%>
                        <%--$("#calcinformation").html(resp);--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>
        }
    });

</script>
</body>
</html>


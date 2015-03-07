<%--
  Created by IntelliJ IDEA.
  User: Yaroslav
  Date: 06.03.2015
  Time: 11:30
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
    <title></title>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/liquid-slider.css"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.touchSwipe.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>

    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/resources/js/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/daterangepicker.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/daterangepicker-bs3.css"/>
</head>
<body>
<div class="container">
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
        <jsp:include page="frames/adminmenu.jsp"/>
    </sec:authorize>
    <sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
        <jsp:include page="frames/usermenu.jsp"/>
    </sec:authorize>
</div>

<div class="container">
    <h4>Каклькулятор калорій по днях</h4>

    <hr/>

    <div class="well" style="overflow: auto">
        <div class="pull-left">
            <h4>
                Оберіть потрібний проміжок
            </h4>
        </div>
        <sec:authorize access="isAuthenticated()">
            <div id="reportrange" class="pull-right"
                 style="background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc">
                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                <span></span> <b class="caret"></b>
            </div>
        </sec:authorize>
    </div>

    <script type="text/javascript">
        $(document).ready(function () {
            var cb = function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
                $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                //alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
            };

            $('#reportrange span').html(moment().subtract(29, 'days').format('MMMM D, YYYY') + ' - ' + moment().format('MMMM D, YYYY'));

            $('#reportrange').daterangepicker(optionSet1, cb);

            $('#reportrange').on('show.daterangepicker', function () {
                console.log("show event fired");
            });
            $('#reportrange').on('hide.daterangepicker', function () {
                console.log("hide event fired");
            });
            $('#reportrange').on('apply.daterangepicker', function (ev, picker) {
                $.ajax({
                    url: "/foodtable/" + picker.startDate + "/" + picker.endDate,
                    type: "POST",
                    beforeSend: function (xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-Type", "application/json");
                    },
                    success: function (suc) {
                        $('#foods').html(suc);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.status + ' ' + jqXHR.responseText);
                    }
                });
                console.log("apply event fired, start/end dates are "
                        + picker.startDate.format('MMMM D, YYYY')
                        + " to "
                        + picker.endDate.format('MMMM D, YYYY')
                );
            });
            $('#reportrange').on('cancel.daterangepicker', function (ev, picker) {
                console.log("cancel event fired");
            });

            var optionSet1 = {
                startDate: moment(),
                endDate: moment(),
                minDate: '01/01/2012',
                maxDate: '12/31/2026',
                dateLimit: {days: 60},
                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: false,
                timePickerIncrement: 1,
                timePicker12Hour: true,
                ranges: {
                    '<spring:message code="date.now"/>': [moment(), moment()],
                    '<spring:message code="date.yesterday"/>': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                    '<spring:message code="date.last7"/>': [moment().subtract(6, 'days'), moment()],
                    '<spring:message code="date.last30"/>': [moment().subtract(29, 'days'), moment()],
                    '<spring:message code="date.thismonth"/>': [moment().startOf('month'), moment().endOf('month')],
                    '<spring:message code="date.lastmonth"/>': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                },
                opens: 'left',
                buttonClasses: ['btn btn-default'],
                applyClass: 'btn-sm btn-primary',
                cancelClass: 'btn-sm',
                format: 'MM/DD/YYYY',
                separator: ' to ',
                locale: {
                    applyLabel: 'Submit',
                    cancelLabel: 'Clear',
                    fromLabel: 'From',
                    toLabel: 'To',
                    customRangeLabel: '<spring:message code="date.custom"/>',
                    daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
                    monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    firstDay: 1
                }
            };

            $('#reportrange').data('daterangepicker').setOptions(optionSet1, cb);
        });
    </script>
</div>

<div class="container" id="foods">
    <h4>Всього вжито калорій: <b> ${calories} </b></h4>
    <table class="table table-striped table-hover">
        <caption>Спожита їжа</caption>
        <tr>
            <th>Назва їжі</th>
            <th>Білки</th>
            <th>Жири</th>
            <th>Вуглеводи</th>
            <th>Калорії</th>
            <th>Вага</th>
            <th>Фото</th>
            <th>--------</th>
        </tr>
        <c:forEach items="${calc}" var="item">
            <tr>
                <td>${item.food.name}</td>
                <td>${item.food.protein / 100.0 * item.value}</td>
                <td>${item.food.fats / 100.0 * item.value}</td>
                <td>${item.food.carbs / 100.0 * item.value}</td>
                <td>${item.food.kcal / 100.0 * item.value}</td>
                <td>${item.value}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/showfood/${item.food.idFood}">
                        <img class="img-thumbnail smalls" name="myImg"
                             src="data:image/jpg;base64,<c:out value='${item.food.photo}'/>">
                    </a>
                </td>
                <td>
                    <a href="/calc/delete/${item.idCalc}" class="btn btn-xs btn-danger"><span
                            class="glyphicon glyphicon-trash"></span>
                        <spring:message code="function.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
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
</body>
</html>

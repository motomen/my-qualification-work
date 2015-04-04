<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html; charset=utf-8"%>
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

  <!-- Calendar -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/daterangepicker-bs3.css"/>
  <script src="${pageContext.request.contextPath}/resources/js/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/daterangepicker.js"></script>



  <!-- Autocomplete -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css">
  <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
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
</body>
</html>

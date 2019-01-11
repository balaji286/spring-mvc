<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="<c:url value='static/css/libs/bootstrap/3.3.7/bootstrap.min.css' />" rel="stylesheet">
        <link href="<c:url value='static/css/common.css' />" rel="stylesheet">

        <script src="<c:url value='/static/js/libs/jquery/3.3.1/jquery.min.js' />"></script>
        <script src="<c:url value='/static/js/libs/bootstrap/3.3.7/bootstrap.min.js' />"></script>
        <script src="<c:url value='/static/js/libs/angularjs/1.7.2/angular.min.js' />"></script>
        <script src="<c:url value='/static/js/libs/angularjs/1.7.2/angular-animate.min.js' />"></script>
        <script src="<c:url value='/static/js/libs/angularjs/1.7.2/angular-aria.min.js' />"></script>
        <script src="<c:url value='/static/js/libs/angularjs/1.7.2/angular-messages.min.js' />"></script>
     
    </head>
    <body  ng-app="myApp" class="ng-cloak" ng-controller="CustomerController as ctrl">

        <div ng-include src="'static/containers/customerTable/CustomerTable.jsp'"></div>

        <script src="<c:url value='/static/js/app.js' />"></script>

        <script src="<c:url value='/static/js/service/customer_service.js' />"></script>
        <script src="<c:url value='/static/js/service/attachment_service.js' />"></script>

        <script src="<c:url value='/static/js/controller/attachment_controller.js' />"></script>
        <script src="<c:url value='/static/js/controller/customer_controller.js' />"></script>

        <script src="<c:url value='/static/js/directive/customer_directive.js' />"></script>
        <script src="<c:url value='/static/js/directive/attachment_directive.js' />"></script>
        
    </body>
</html>

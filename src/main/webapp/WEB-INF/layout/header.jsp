<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="<c:url value="/css/header.css"/>" rel="stylesheet" />

<nav id="navbar_top" class="navbar navbar-expand-sm   navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />"><img style="height: 23px" id="logo" src="<c:url value="/images/logo.png" />"><span class="sr-only">(current)</span></a>
            </li>


            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item dropdown dmenu">
                    <a class="nav-link dropdown-toggle" href="<c:url value="/admin/user-admin/" />" id="navbardrop" data-toggle="dropdown">
                        <spring:message code="label.user" />
                    </a>
                    <div class="dropdown-menu sm-menu mt-0">
                        <a class="dropdown-item" href="<c:url value="/admin/doctor/" />"><spring:message code="label.doctor" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/nurse/" />"><spring:message code="label.nurse" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/patient/" />"><spring:message code="label.patient" /></a>
                    </div>
                </li>
                <li class="nav-item dropdown dmenu ">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        <spring:message code="label.schedule" />
                    </a>
                    <div class="dropdown-menu sm-menu mt-0">
                        <a class="dropdown-item" href="<c:url value="/admin/schedule-doctor/" />"><spring:message code="label.scheduleDoctor" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/schedule-nurse/" />"><spring:message code="label.scheduleNurse" /></a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/medicine/" />"><spring:message code="label.medicine" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/sick/" />"><spring:message code="label.sick" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/appointment/" />"><spring:message code="label.appointment" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/prescription/" />"><spring:message code="label.prescription" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/prescription-detail/" />"><spring:message code="label.prescriptionDetail" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/order/" />"><spring:message code="label.order" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/medical-record/" />"><spring:message code="label.medicalRecord" /></a>
                </li>
                <li class="nav-item dropdown dmenu ">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        <spring:message code="chart.statistic" />
                    </a>
                    <div class="dropdown-menu sm-menu mt-0">
                        <a class="dropdown-item" href="<c:url value="/admin/chart-count-patient/" />"><spring:message code="chart.statisticPatient" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/chart-sales-month/" />"><spring:message code="chart.statisticSalesMonth" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/chart-sales-quarter/" />"><spring:message code="chart.statisticSalesQuarter" /></a>
                        <a class="dropdown-item" href="<c:url value="/admin/chart-sales-year/" />"><spring:message code="chart.statisticSalesYear" /></a>
                    </div>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_DOCTOR')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/doctor/history-patient/" />"><spring:message code="label.historyPatient" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/doctor/schedule-doctor/" />"><spring:message code="label.scheduleDoctor" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_NURSE')">
                <li class="nav-item dropdown dmenu ">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        <spring:message code="label.appointment" />
                    </a>
                    <div class="dropdown-menu sm-menu mt-0">
                        <a class="dropdown-item" href="<c:url value="/nurse/list-appointment/" />"><spring:message code="appoint.list" /></a>
                        <a class="dropdown-item" href="<c:url value="/nurse/confirm-appointment/" />"><spring:message code="label.confirmAppointment" /></a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=""><spring:message code="" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/nurse/add-order/" />"><spring:message code="label.addOrder" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/nurse/see-order-list/" />"><spring:message code="order.list" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/nurse/schedule-nurse/" />"><spring:message code="label.scheduleNurse" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_PATIENT')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />"><spring:message code="label.homePage" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/book-appointment/" />"><spring:message code="label.priceList" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/list-appointment/" />"><spring:message code="label.appointment" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/medical-record/" />"><spring:message code="label.medicalRecord" /></a>
                </li>
            </sec:authorize>


            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login" />"><spring:message code="label.login" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/register" />"><spring:message code="label.register" /></a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li class="nav-item p-">
                    <a class="nav-link" href="<c:url value="/profile" />">
                     <img src="${userInfo.avatar}" alt="..." height="36">
                    ${userInfo.firstName} ${userInfo.lastName}
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/logout" />">Logout</a>
                </li>
            </c:if>



        </ul>
        <div class="nav-item ml-2">
            <a class="nav-link p-0" href="<c:url value="/?lang=vi" />"><img style="height: 25px" id="logo" src="<c:url value="/images/vi.png" />"></a>
        </div>
        <div class="nav-item ml-2">
            <a class="nav-link p-0" href="<c:url value="/?lang=en" />"><img style="height: 25px" id="logo" src="<c:url value="/images/en.png" />"></a>
        </div>

    </div>
</nav>


<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->

<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {
        window.addEventListener('scroll', function () {
            if (window.scrollY > 50) {
                document.getElementById('navbar_top').classList.add('fixed-top');
                // add padding top to show content behind navbar
                navbar_height = document.querySelector('.navbar').offsetHeight;
                document.body.style.paddingTop = navbar_height + 'px';
            } else {
                document.getElementById('navbar_top').classList.remove('fixed-top');
                // remove padding top from body
                document.body.style.paddingTop = '0';
            }
        });
    });
</script>

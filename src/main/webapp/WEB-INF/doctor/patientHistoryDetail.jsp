<%-- 
    Document   : sick
    Created on : Aug 4, 2021, 4:50:59 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">

    <h1 class="text-center text-primary my-3"><spring:message code="label.seeHistory" /></h1>
    <h2> ${historyPatients[0].patient.user.firstName} ${historyPatients[0].patient.user.lastName}</h2>
    <div class="row my-2">  
        <div class="col-md-11">
            <form action="">
                <div class="row">
                    <div class="col-md-4">
                        <input class="form-control" type="date" name="fromDate" />
                    </div>
                    <div class="col-md-4">
                        <input class="form-control" type="date" name="toDate" />
                    </div>
                    <div class="col-md-1 p-0"> 
                        <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
                    </div>
                </div>
            </form>  
        </div>
        <div class="col-md-11">
    <sec:authorize access="hasRole('ROLE_DOCTOR')">
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == null && param.toDate == null}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient-detail/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
   <sec:authorize access="hasRole('ROLE_PATIENT')">
      
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == null && param.toDate == null}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
            </div>
    
        <div class="col-md-11">
            <table class="table align-middle">
                <thead>
                    <tr>
                        <th scope="col" class="col-md-1">#</th>
                        <th scope="col" class="col-md-auto"><spring:message code="appoint.meetDate" /></th>
                        <th scope="col" class="col-md-auto"><spring:message code="appoint.meetTime" /></th>
                        <th></th>
                        <th></th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${historyPatients}">
                        <tr id="appointment${d.id}">
                            <th scope="row">${d.id}</th>
                            <td>${d.meetDate}</td>
                            <td>${d.meetTime}</td>
                            <sec:authorize access="hasRole('ROLE_DOCTOR')">
                                <td class="">
                                    <a href="<c:url value="/doctor/add-prescription/?appointmentId=${d.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.add" /></a>
                                </td>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_PATIENT')">
                                <c:if test="${d.nurse == null}">
                                    <td class="">
                                        NOT-CONFIRM
                                    </td>
                                    <td class="">
                                        
                                    </td>
                                </c:if>
                                <c:if test="${d.nurse != null}">
                                    <td class="">
                                        CONFIRM
                                    </td>
                                    <c:if test="${d.prescription.id != null}">
                                    <td class="">
                                        <a href="<c:url value="/see-prescription/?preId=${d.prescription.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.prescriptionDetail" /></a>
                                    </td>
                                    </c:if>
                                    <c:if test="${d.prescription.id == null}">
                                    <td class="">
                                        <a href="" class="btn btn-dark btn-sm px-3"><spring:message code="label.notPrescription" /></a>
                                    </td>
                                    </c:if>
                                </c:if>
                                <td>
                                    <a href="javascript:;" class="btn btn-danger btn-sm px-3" onclick="deleteAppointment(${d.id})">Xoá</a>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="<c:url value="/js/main.js" />"></script>
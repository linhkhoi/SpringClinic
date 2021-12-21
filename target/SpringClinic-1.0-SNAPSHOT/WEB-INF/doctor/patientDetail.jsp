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
    <h1 class="text-center text-primary my-3">${medicalPatients[0].patient.user.firstName} ${medicalPatients[0].patient.user.lastName}</h1>
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
            <sec:authorize access="hasRole('ROLE_DOCTOR')">
    <div class="col-md-1"> 
            <a href="<c:url value="/doctor/add-medical/?patientId=${patientId}" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
        </sec:authorize>
  </div>
    
    
     <sec:authorize access="hasRole('ROLE_PATIENT')">
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.fromDate == null && param.toDate == null}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/medical-record/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
    <div class="row my-2">  

        <div class="col-md-11">
            <table class="table align-middle">
                <thead>
                    <tr>
                        <th scope="col" class="col-md-1">#</th>
                        <th scope="col" class="col-md-auto"><spring:message code="mere.startDate" /></th>
                        <th scope="col" class="col-md-auto"><spring:message code="mere.endDate" /></th>
                        <th scope="col" class="col-md-auto"><spring:message code="sick.name" /></th>
                        <th scope="col" class="col-md-1"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${medicalPatients}">
                        <tr>
                            <th scope="row">${d.id}</th>
                            <td>${d.startDate}</td>
                            <td>${d.endDate}</td>
                            <td>${d.sick.name}</td>
                            <sec:authorize access="hasRole('ROLE_DOCTOR')">
                            <td class="">
                                <a href="<c:url value="/doctor/add-medical/?medicalRecordId=${d.id}&patientId=${d.patient.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.add" /></a>
                            </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

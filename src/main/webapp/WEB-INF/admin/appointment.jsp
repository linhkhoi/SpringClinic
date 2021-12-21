<%-- 
    Document   : sick
    Created on : Aug 4, 2021, 4:50:59 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">
    
    <h1 class="text-center text-primary my-3"><spring:message code="appoint.list" /></h1>
     
    
    <div class="row my-2">  

        <div class="col-md-11">
            <form action="">
                <div class="row">
                     <div class="col-md-3">
                        <input class="form-control" type="text" name="kw" />
                    </div>
                    <div class="col-md-3">
                        <input class="form-control" type="date" name="fromDate" />
                    </div>
                    <div class="col-md-3">
                        <input class="form-control" type="date" name="toDate" />
                    </div>
                    <div class="col-md-1 p-0"> 
                        <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
                    </div>
                </div>
            </form>  
        </div>
 <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="col-md-1"> 
            <a href="<c:url value="/admin/appointment-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
    </div>
         </sec:authorize>
           <div class="col-md-11">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/admin/appointment/"/>${historyPatients[0].patient.id}/?page=${i}">${i}</a></li>
                </c:if>
                <c:if test = "${page != i}">
                    <li class="page-item"><a class="page-link mx-0" href="<c:url value="/admin/appointment/"/>${historyPatients[0].patient.id}/?page=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
                <sec:authorize access="hasRole('ROLE_NURSE')">
                  
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.kw != ''&& param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=${param.kw}&fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=${param.kw}&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=${param.kw}&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=&fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=${param.kw}&fromDate=&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    
                    
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=${param.kw}&fromDate=&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?kw=&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == null&& param.fromDate == null && param.toDate == null}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/list-appointment/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
            </div>
    <table class="table align-middle">
        <thead>
            <tr>
                <th scope="col" class="col-md-1">#</th>
                <th scope="col" class="col-md-2"><spring:message code="appoint.meetDate" /></th>
                <th scope="col" class="col-md-2"><spring:message code="appoint.meetTime" /></th>
                <th scope="col" class="col-md-2"><spring:message code="appoint.expense" /></th>
                <th scope="col" class="col-md-2"><spring:message code="label.nurse" /></th>
                <th scope="col" class="col-md-2"><spring:message code="label.patient" /></th>
                <th scope="col" class="col-md-1"><spring:message code="label.confirmPrescip" /></th>
                <th scope="col" class="col-md-1"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="a" items="${appointments}">
                <c:if test="${a.nurse != null}">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <tr>
                        <th scope="row">${a.id}</th>
                        <td>${a.meetDate}</td>
                        <td>${a.meetTime}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${a.expense}"/></td>

                        <td>${a.nurse.user.firstName} ${a.nurse.user.lastName}</td>
                        <td>${a.patient.user.firstName} ${a.patient.user.lastName}</td>
                        
                            <td class="">
                                <a href="<c:url value="/admin/appointment-edit/?appointmentId=${a.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.update" /></a>
                            </td>
                            <td>
                                <a href="" class="btn btn-danger btn-sm px-3"><spring:message code="label.delete" /></a>
                            </td>
                        
                    </tr>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_NURSE')">
                    <tr>
                        <th scope="row">${a.id}</th>
                        <td>${a.meetDate}</td>
                        <td>${a.meetTime}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${a.expense}"/></td>

                        <td>${a.nurse.user.firstName} ${a.nurse.user.lastName}</td>
                        <td>${a.patient.user.firstName} ${a.patient.user.lastName}</td>
                        
                    </tr>
                    </sec:authorize>
                </c:if>
                <c:if test="${a.nurse == null}">
                    <sec:authorize access="hasRole('ROLE_NURSE')">
                    <tr>
                        <th scope="row">${a.id}</th>
                        <td>${a.meetDate}</td>
                        <td>${a.meetTime}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${a.expense}"/></td>

                        <td>${a.nurse.user.firstName} ${a.nurse.user.lastName}</td>
                        <td>${a.patient.user.firstName} ${a.patient.user.lastName}</td>
                        
                            <td class="">
                                <a id="a" href="<c:url value="/nurse/checked-appointment/?appointmentId=${a.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.confirmPrescip" /></a>
                            </td>
                        
                    </tr>
                    </sec:authorize>
                </c:if>    
            </c:forEach>

        </tbody>
    </table>
</div>
</div>
                
<%-- 
    Document   : confirmAppointment
    Created on : Aug 21, 2021, 4:44:37 PM
    Author     : MSIGE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    
    <h1 class="text-center text-primary my-3"><spring:message code="appoint.list" /></h1>
 
<table class="table align-middle">
  <thead>
    <tr>
      <th scope="col" class="col-md-1">#</th>
      <th scope="col" class="col-md-2"><spring:message code="appoint.meetDate" /></th>
      <th scope="col" class="col-md-2"><spring:message code="appoint.expense" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.patient" /></th>
      <th scope="col" class="col-md-1"></th>
      <th scope="col" class="col-md-1"></th>
    </tr>
  </thead>
  <tbody>
      <c:forEach var="a" items="${appointments}">
          <tr>
      <th scope="row">${a.id}</th>
      <td>${a.meetDate}</td>
      <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${a.expense}"/></td>
      <td>${a.patient.user.username}</td>
      <td class="">
        <a href="<c:url value="/nurse/checked-appointment/?appointmentId=${a.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.confirmPrescip" /></a>
      </td>
    </tr>
      </c:forEach>
    
  </tbody>
</table>
</div>
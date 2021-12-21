<%-- 
    Document   : sick
    Created on : Aug 4, 2021, 4:50:59 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    
    <h1 class="text-center text-primary my-3"><spring:message code="patient.list" /></h1>
  <div class="row my-2">  
      
      <div class="col-md-11">
 <form action="">
    <div class="row">
        <div class="col-md-4">
            <input class="form-control" type="text" name="kw" placeholder="Nhập từ khoá để tìm" />
        </div>
        <div class="col-md-1 p-0"> 
            <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
        </div>
    </div>
        
</form>  
      </div>
  </div>
        
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.kw != ''}">
                         <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient/"/>?kw=${param.kw}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient/"/>?page=${i}">${i}</a></li>
                    </c:if>
                   
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.kw != ''}">
                         <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient/"/>?kw=${param.kw}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/history-patient/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
<table class="table align-middle">
  <thead>
    <tr>
      <th scope="col" class="col-md-1">#</th>
      <th scope="col" class="col-md-auto"><spring:message code="lable.firstName" /></th>
      <th scope="col" class="col-md-auto"><spring:message code="lable.lastName" /></th>
      <th scope="col" class="col-md-auto"><spring:message code="patient.job" /></th>
      <th scope="col" class="col-md-2"></th>
      <th scope="col" class="col-md-2"></th>
      <th scope="col" class="col-md-2"></th>
    </tr>
  </thead>
  <tbody>
      <c:forEach var="d" items="${patientcommom}">
          <tr>
      <th scope="row">${d.id}</th>
      <td>${d.user.firstName}</td>
      <td>${d.user.lastName}</td>
      <td>${d.job}</td>
      <td class="mx-0 px-0">
        <a href="<c:url value="/doctor/medical-patient/${d.id}/" />" class="btn btn-info btn-sm px-3"><spring:message code="label.seeMedical" /></a>
      </td>
      <td class="mx-0 px-0">
        <a href="<c:url value="/doctor/history-patient-detail/${d.id}/" />" class="btn btn-success btn-sm px-3"><spring:message code="label.seeAppoint" /></a>
      </td>
      <td class="mx-0 px-0">
        <a href="<c:url value="/doctor/list-appointment/${d.id}/" />" class="btn btn-primary btn-sm px-3"><spring:message code="label.seeHistory" /></a>
      </td>
    </tr>
      </c:forEach>
    
  </tbody>
</table>
</div>
     
      
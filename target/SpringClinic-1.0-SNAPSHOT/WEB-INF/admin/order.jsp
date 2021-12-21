
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">
    
    <h1 class="text-center text-primary my-3"><spring:message code="order.list" /></h1>
  <div class="row my-2">  
      
      <div class="col-md-11">
        <form action="">
           <div class="row">
               <div class="col-md-3">
                   <input class="form-control" type="text" name="kw" placeholder="Nhập từ khoá để tìm" />
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
            <a href="<c:url value="/admin/order-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
        </sec:authorize>
  </div>
        <sec:authorize access="hasRole('ROLE_NURSE')">
                  
   <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.kw != ''&& param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=${param.kw}&fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=${param.kw}&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=${param.kw}&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=&fromDate=${param.fromDate}&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=${param.kw}&fromDate=&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    
                    
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.kw != ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=${param.kw}&fromDate=&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate != '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=&fromDate=${param.fromDate}&toDate=&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?kw=&fromDate=&toDate=${param.toDate}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''&& param.fromDate == '' && param.toDate == ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == null&& param.fromDate == null && param.toDate == null}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/nurse/see-order-list/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
   </sec:authorize>
<table class="table align-middle">
  <thead>
    <tr>
      <th scope="col" class="col-md-1">#</th>
      <th scope="col" class="col-md-2"><spring:message code="order.createdDate" /></th>
      <th scope="col" class="col-md-2"><spring:message code="order.total" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.nurse" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.prescription" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.patient" /></th>
      <th scope="col" class="col-md-1"></th>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
      <th scope="col" class="col-md-1"></th>
      <th scope="col" class="col-md-1"></th>
      
      </sec:authorize>
    </tr>
  </thead>
  <tbody>
      <c:forEach var="m" items="${orders}">
          <tr>
      <th scope="row">${m.id}</th>
      <td>${m.createdDate}</td>
      <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${m.totalPriceOrder}"/></td>
      <td>${m.nurse.user.firstName} ${m.nurse.user.lastName}</td>
      <td>${m.prescription.id}</td>
      <td>${m.prescription.appointment.patient.user.firstName} ${m.prescription.appointment.patient.user.lastName}</td>
      <sec:authorize access="hasRole('ROLE_NURSE')">
      <td class="">
            <a href="<c:url value="/nurse/see-order/?preId=${m.prescription.id}&orderId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.prescriptionDetail" /></a>
      </td>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
      <td class="">
        <a href="<c:url value="/admin/order-edit/?orderId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.update" /></a>
      </td>
      <td>
        <a href="" class="btn btn-danger btn-sm px-3"><spring:message code="label.delete" /></a>
      </td>
      </sec:authorize>
    </tr>
      </c:forEach>
    
  </tbody>
</table>
</div>
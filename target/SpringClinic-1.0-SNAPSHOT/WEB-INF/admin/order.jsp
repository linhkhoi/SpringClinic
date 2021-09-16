
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
        <div class="col-md-4">
            <input class="form-control" type="text" name="kw" placeholder="Nhập từ khoá để tìm" />
        </div>
        <div class="col-md-1 p-0"> 
            <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
        </div>
    </div>
</form>  
      </div>
    <div class="col-md-1"> 
            <a href="<c:url value="/admin/order-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
  </div>
<table class="table align-middle">
  <thead>
    <tr>
      <th scope="col" class="col-md-1">#</th>
      <th scope="col" class="col-md-2"><spring:message code="order.createdDate" /></th>
      <th scope="col" class="col-md-2"><spring:message code="order.total" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.nurse" /></th>
      <th scope="col" class="col-md-2"><spring:message code="label.prescription" /></th>
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
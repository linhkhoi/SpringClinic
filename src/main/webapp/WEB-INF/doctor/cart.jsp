<%-- 
    Document   : cart
    Created on : Aug 24, 2021, 9:15:08 PM
    Author     : MSIGE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
<h1 class="text-center text-info"><spring:message code="label.prescription"/></h1>
<c:if test="${carts == null}">
    <h4 class="text-danger"><spring:message code="label.noMedicine"/></h4>
</c:if>
<c:if test="${carts != null}">
<div class="alert alert-warning">
    <h2 style="text-align:left;float:left;"><spring:message code="label.totalMedicine"/>: </h2><h2 id="cart-counter"> ${cartCounter}</h2>
    <h2 style="text-align:left;float:left;"><spring:message code="label.totalPrice"/>: </h2><h2 id="amount-cart"><fmt:formatNumber type="number" maxFractionDigits="2" value="${cartStat.amount}"/> VNĐ</h2>
</div>

<table class="table">
    <tr>
        <th>#</th>
        <th><spring:message code="medicine.name"/></th>
        <th><spring:message code="medicine.price"/></th>
        <th><spring:message code="prede.quantity"/></th>
    </tr>
    <c:forEach items="${carts}" var="p">
    <tr>
        <td>${p.medicineId}</td>
        <td>${p.medicineName}</td>
        <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${p.price}"/> VNĐ</td>
        <td>
            <div class="form-group">
                <input type="number" onblur="updateCart(this, ${p.medicineId})" class="form-control" value="${p.quantity}" />
            </div>
        </td>
        <td>
            <input type="button" onclick="deleteCart(${p.medicineId})" class="btn btn-danger" value="Xoá" />
        </td>
    </tr>
    </c:forEach>
</table>
    <input type="button"  onclick="pay()"
           value="<spring:message code="label.confirmPrescip"/>" class="btn btn-danger mb-3" />

</c:if>
</div>
<script src="<c:url value="/js/main.js" />"></script>
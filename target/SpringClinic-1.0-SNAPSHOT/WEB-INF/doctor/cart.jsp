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

<div class="alert alert-warning">
    <h2><spring:message code="label.totalMedicine"/>: ${cartStats.totalQuantity}</h2>
    <h2><spring:message code="label.totalPrice"/>: <fmt:formatNumber type="number" maxFractionDigits="2" value="${cartStats.totalAmount}"/> VNĐ</h2>
</div>

<table class="table">
    <tr>
        <th>#</th>
        <th><spring:message code="medicine.name"/></th>
        <th><spring:message code="medicine.price"/></th>
        <th><spring:message code="prede.quantity"/></th>
    </tr>
    <c:if test="${cart != null}">
        <c:forEach items="${cart.values()}" var="p">
        <tr>
            <td>${p.medicineId}</td>
            <td>${p.medicineName}</td>
            <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${p.price}"/> VNĐ</td>
            <td>
                <input type="number" class="form-control" value="${p.quantity}" />
            </td>
        </tr>
        </c:forEach>
    </c:if>
    <c:if test="${cart == null}">
        <tr>
            <td colspan="4"><spring:message code="label.noMedicine"/></td>
        </tr>
    </c:if>
</table>

<div>
    <input type="button"  onclick="pay()"
           value="<spring:message code="label.confirmPrescip"/>" class="btn btn-danger mb-3" />
</div>
</div>
<script src="<c:url value="/js/main.js" />"></script>
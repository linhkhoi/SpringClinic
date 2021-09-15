<%-- 
    Document   : cart
    Created on : Apr 16, 2021, 10:17:05 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info"><spring:message code="label.prescriptionDetail" /></h1>

<div class="alert alert-warning">
    <h2>Tổng sản phẩm trong giỏ: ${cartStats.totalQuantity}</h2>
    <h2>Tổng tiền: ${cartStats.totalAmount} VNĐ</h2>
</div>

<table class="table">
    <tr>
        <th>ID</th>
        <th><spring:message code="medicine.name" /></th>
        <th><spring:message code="medicine.price" /></th>
        <th><spring:message code="prede.quantity" /></th>
    </tr>
    <c:if test="${cart != null}">
        <c:forEach items="${cart.values()}" var="p">
        <tr>
            <td>${p.productId}</td>
            <td>${p.productName}</td>
            <td>${p.price} VNĐ</td>
            <td>
                <input type="number" class="form-control" value="${p.quantity}" />
            </td>
        </tr>
        </c:forEach>
    </c:if>
    <c:if test="${cart == null}">
        <tr>
            <td colspan="4">KHÔNG CÓ SẢN PHẨM NÀO TRONG GIỎ!!!</td>
        </tr>
    </c:if>
</table>

<div>
    <input type="button"  onclick="pay()"
           value="Thanh toán" class="btn btn-danger" />
</div>

<script src="<c:url value="/js/main.js" />"></script>

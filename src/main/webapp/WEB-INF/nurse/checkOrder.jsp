
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="<c:url value="/css/checkOrder.css"/>" rel="stylesheet" />
<div class="container my-5">
    <div class="d-flex justify-content-center row">
        <div class="col-md-8">
            <div class="p-3 bg-white rounded">
                <div class="row">
                    <div class="col-md-6">
                        <h1 class="text-uppercase"><spring:message code="label.order" /></h1>
                        <div class="billed"><span class="font-weight-bold text-uppercase">Billed:</span><span class="ml-1">Life Care</span></div>
                    </div>
                    <div class="col-md-6 text-right mt-3">
                        <h4 class="text-danger mb-0"><img style="height: 23px" id="logo" src="<c:url value="/images/logo.png" />"></h4>
                    </div>
                </div>
                <div class="mt-3">
                    <div class="table-responsive">

                        <table class="table">
                            <thead>
                                <tr>
                                    <th><spring:message code="label.note" /></th>
                                    <th><spring:message code="label.intoMoney" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><spring:message code="label.expense" /></td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${data.get('expense')}"/> VNĐ</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="label.prescription" /></td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${data.get('totalPrice')}"/> VNĐ</td>
                                </tr>
                                <c:forEach var="s" items="${preDetail}">
                                    <tr>
                                        <td class="pl-5">${s[0].name}</td>
                                        <td class="pl-5"><fmt:formatNumber type="number" maxFractionDigits="2" value="${s[1]}"/> VNĐ x ${s[2]}</td>
                                    </tr>
                                </c:forEach>

                                <tr class="table-info">
                                    <td><spring:message code="label.total" /></td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${data.get('total')}"/> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_NURSE')">
                    <div class="text-right mb-3"><a class="btn btn-danger btn-sm mr-5" href="<c:url value="/nurse/pay-by-zalo/?total=${data.get('total')}&presciptionId=${data.get('id')}" />"><spring:message code="label.pay" /></a></div>
                    </sec:authorize>
            </div>
        </div>
    </div>
</div>
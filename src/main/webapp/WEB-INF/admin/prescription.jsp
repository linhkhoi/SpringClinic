
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="container">

    <h1 class="text-center text-primary my-3"><spring:message code="prescription.list" /></h1>
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
            <a href="<c:url value="/admin/prescription-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
    </div>
    <table class="table align-middle">
        <thead>
            <tr>
                <th scope="col" class="col-md-1">#</th>
                <th scope="col" class="col-md-2"><spring:message code="prescription.createdDate" /></th>
                <th scope="col" class="col-md-2"><spring:message code="prescription.total" /></th>
                <th scope="col" class="col-md-2"><spring:message code="label.doctor" /></th>
                <th scope="col" class="col-md-2"><spring:message code="label.appointment" /></th>
                <th scope="col" class="col-md-1"></th>
                <th scope="col" class="col-md-1"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${prescriptions}">
                <tr>
                    <th scope="row">${m.id}</th>
                    <td>${m.createdDate}</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${m.totalPrice}"/></td>
                    <td>${m.doctor.id}</td>
                    <td>${m.appointment.id}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="<c:url value="/admin/prescription-edit/?prescriptionId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.update" /></a>
                        </td>
                        <td>
                            <a href="" class="btn btn-danger btn-sm px-3"><spring:message code="label.delete" /></a>
                        </td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_NURSE')">
                        <td>
                            <a href="<c:url value="/nurse/check-order/?prescriptionId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.add" /></a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>
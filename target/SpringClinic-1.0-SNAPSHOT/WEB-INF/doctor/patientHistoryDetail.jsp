<%-- 
    Document   : sick
    Created on : Aug 4, 2021, 4:50:59 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">

    <h1 class="text-center text-primary my-3"><spring:message code="label.seeHistory" /></h1>
    <h2>- ${historyPatients[0].patient.user.firstName} ${historyPatients[0].patient.user.lastName}</h2>
    <div class="row my-2">  

        <div class="col-md-11">
            <table class="table align-middle">
                <thead>
                    <tr>
                        <th scope="col" class="col-md-1">#</th>
                        <th scope="col" class="col-md-auto"><spring:message code="appoint.meetDate" /></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="d" items="${historyPatients}">
                        <tr>
                            <th scope="row">${d.id}</th>
                            <td>${d.meetDate}</td>
                            <td class="">
                                <a href="<c:url value="/doctor/add-prescription/?appointmentId=${d.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="patient.see" /></a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</div>

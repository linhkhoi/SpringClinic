

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="appointment.title" />
        </h1>
        <form:form method="post" modelAttribute="appointment" acceptCharset="UTF-8">

            <div class="form-group">
                <label for="meetDate">
                    <spring:message code="appoint.meetDate" />
                </label>
                <form:input id="meetDate" type="date" cssClass="form-control" path="meetDate"  />
            </div>
            <div class="form-group">
                <label for="expense">
                    <spring:message code="appoint.expense" />
                </label>
                <form:input id="expense" cssClass="form-control" path="expense" />
            </div>

            <div class="form-group">
                <label for="patient">
                    <spring:message code="label.patient" />
                </label>
                <form:select cssClass="form-control" id="patient" path="patient">
                    <c:forEach items="${patientcommom}" var="s">
                        <c:if test="${s.id == appointment.patient.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != appointment.patient.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
                
            <div class="form-group">
                <label for="nurse">
                    <spring:message code="label.nurse" />
                </label>
                <form:select cssClass="form-control" id="nurse" path="nurse">
                    <c:forEach items="${nursecommom}" var="s">
                        <c:if test="${s.id == appointment.nurse.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != appointment.nurse.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${appointment.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${appointment.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>

        </form:form>
    </section>
</div>


<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="mere.title" />
        </h1>
        <h1>${medicalRecord.patient.job}</h1>
        <c:url value="/doctor/add-medical" var="action" />
            <form:form method="post" action="${action}" modelAttribute="medicalRecord" acceptCharset="UTF-8">
            <div class="form-group">
                <label for="meetDate">
                    <spring:message code="mere.startDate" />
                </label>
                <form:input id="startDate" type="date" cssClass="form-control date-picker" path="startDate"  />
            </div>
            <div class="form-group">
                <label for="endDate">
                    <spring:message code="mere.endDate" />
                </label>
                <form:input id="endDate" type="date" cssClass="form-control date-picker" path="endDate" />
            </div>
            
            <div class="form-group">
                <label for="patient">
                    <spring:message code="label.patient" />
                </label>
                <form:select cssClass="form-control" id="patient" path="patient">
                    <option selected value="${patient.id}">${patient.id}</option>
                </form:select>
            </div>
                
            <div class="form-group">
                <label for="sick">
                    <spring:message code="label.sick" />
                </label>
                <form:select cssClass="form-control" id="sick" path="sick">
                    <c:forEach items="${sickcommom}" var="s">
                        <c:if test="${s.id == medicalRecord.sick.id}">
                            <option selected value="${s.id}">${s.name}</option>
                        </c:if>
                        <c:if test="${s.id != medicalRecord.sick.id}">
                            <option value="${s.id}">${s.name}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <form:hidden path="id" />
                <form:hidden path="patient" value="5" />
                <c:if test="${medicalRecord.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${medicalRecord.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>
        </form:form>
    </section>
</div>
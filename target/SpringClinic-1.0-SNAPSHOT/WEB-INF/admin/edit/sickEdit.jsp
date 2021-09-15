

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="sick.title" />
        </h1>
        <form:form method="post" modelAttribute="sick" acceptCharset="UTF-8">
                <form:errors path="*" element="div" cssClass="alert alert-danger" />
            <div class="form-group">
                <label for="name">
                    <spring:message code="sick.name" />
                </label>
                <form:input id="name" cssClass="form-control" path="name"  />
                <form:errors path="name" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="symptom">
                    <spring:message code="sick.symptom" />
                </label>
                <form:input id="symptom" cssClass="form-control" path="symptom" />
                <form:errors path="symptom" cssClass="text-danger" />
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${sick.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="sick.update" />" />
                </c:if>
                <c:if test="${sick.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="sick.add" />" />
                </c:if>
            </div>
        </form:form>
    </section>
</div>

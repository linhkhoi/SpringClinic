

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="patient.title" />
        </h1>
        <form:form method="post" modelAttribute="patient" acceptCharset="UTF-8">

            
            <div class="form-group">
                <label for="user">
                    <spring:message code="label.user" />
                </label>
                <form:select cssClass="form-control" id="user" path="user">
                    <c:forEach items="${usercommom}" var="s">
                        <c:if test="${s.id == patient.user.id}">
                            <option selected value="${s.id}">${s.username}</option>
                        </c:if>
                        <c:if test="${s.id != patient.user.id}">
                            <option value="${s.id}">${s.username}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label for="job">
                    <spring:message code="patient.job" />
                </label>
                <form:input id="job" cssClass="form-control" path="job"  />
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${patient.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${patient.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>
        </form:form>
    </section>
</div>


<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="schenur.title" />
        </h1>
        <form:form method="post" modelAttribute="scheduleNurse" acceptCharset="UTF-8">

            
            <div class="form-group">
                <label for="nurse">
                    <spring:message code="label.nurse" />
                </label>
                <form:select cssClass="form-control" id="nurse" path="nurse">
                    <c:forEach items="${nursecommom}" var="s">
                        <c:if test="${s.id == scheduleNurse.nurse.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != scheduleNurse.nurse.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
            <div class="form-group">
                <label for="position">
                    <spring:message code="schenur.position" />
                </label>
                <form:input id="position" cssClass="form-control" path="position"  />
            </div>

            <div class="form-group">
                <label for="schedule">
                    <spring:message code="schenur.schedule" />
                </label>
                    <form:input id="schedule" type="date" cssClass="form-control date-picker" path="schedule" />
            </div>
            <div class="form-group">
                <label for="schedule">
                    <spring:message code="schedoc.time" />
                </label>
                    <form:input id="timeDuty" type="time" cssClass="form-control date-picker" path="timeDuty" />
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${scheduleNurse.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${scheduleNurse.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>
        </form:form>
    </section>
</div>
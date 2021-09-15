

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="prescription.title" />
        </h1>
        <form:form method="post" modelAttribute="prescription" acceptCharset="UTF-8">

            <div class="form-group">
                <label for="createdDate">
                    <spring:message code="prescription.createdDate" />
                </label>
                <form:input id="createdDate" type="date" cssClass="form-control" path="createdDate"  />
            </div>
            <div class="form-group">
                <label for="totalPrice">
                    <spring:message code="prescription.total" />
                </label>
                <form:input id="totalPrice" cssClass="form-control" path="totalPrice" />
            </div>

            <div class="form-group">
                <label for="patient">
                    <spring:message code="label.appointment" />
                </label>
                <form:select cssClass="form-control" id="appointment" path="appointment">
                    <c:forEach items="${appointmentcommom}" var="s">
                        <c:if test="${s.id == prescription.appointment.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != prescription.appointment.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
                
            <div class="form-group">
                <label for="doctor">
                    <spring:message code="label.doctor" />
                </label>
                <form:select cssClass="form-control" id="doctor" path="doctor">
                    <c:forEach items="${doctorcommom}" var="s">
                        <c:if test="${s.id == prescription.doctor.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != prescription.doctor.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${prescription.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${prescription.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>



        </form:form>
    </section>
</div>
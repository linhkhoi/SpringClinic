

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="order.title" />
        </h1>
        <form:form method="post" modelAttribute="order" acceptCharset="UTF-8">

            <div class="form-group">
                <label for="createdDate">
                    <spring:message code="order.createdDate" />
                </label>
                <form:input id="createdDate" type="date" cssClass="form-control" path="createdDate"  />
            </div>
            <div class="form-group">
                <label for="totalPriceOrder">
                    <spring:message code="order.total" />
                </label>
                <form:input id="totalPriceOrder" cssClass="form-control" path="totalPriceOrder" />
            </div>

            <div class="form-group">
                <label for="nurse">
                    <spring:message code="label.nurse" />
                </label>
                <form:select cssClass="form-control" id="nurse" path="nurse">
                    <c:forEach items="${nursecommom}" var="s">
                        <c:if test="${s.id == order.nurse.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != order.nurse.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>
                
            <div class="form-group">
                <label for="prescription">
                    <spring:message code="label.prescription" />
                </label>
                <form:select cssClass="form-control" id="prescription" path="prescription">
                    <c:forEach items="${prescriptioncommom}" var="s">
                        <c:if test="${s.id == order.prescription.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != order.prescription.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${order.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${order.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>



        </form:form>
    </section>
</div>
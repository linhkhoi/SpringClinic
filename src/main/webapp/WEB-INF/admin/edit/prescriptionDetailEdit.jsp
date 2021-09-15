

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="prede.title" />
        </h1>
        <form:form method="post" modelAttribute="prescriptionDetail" acceptCharset="UTF-8">

            <div class="form-group">
                <label for="quantity">
                    <spring:message code="prede.quantity" />
                </label>
                <form:input id="quantity" cssClass="form-control" path="quantity"  />
            </div>
            <div class="form-group">
                <label for="price">
                    <spring:message code="prede.price" />
                </label>
                <form:input id="price" cssClass="form-control" path="price" />
            </div>

            <div class="form-group">
                <label for="medicine">
                    <spring:message code="label.medicine" />
                </label>
                <form:select cssClass="form-control" id="medicine" path="medicine">
                    <c:forEach items="${medicinecommom}" var="s">
                        <c:if test="${s.id == prescriptionDetail.medicine.id}">
                            <option selected value="${s.id}">${s.name}</option>
                        </c:if>
                        <c:if test="${s.id != prescriptionDetail.medicine.id}">
                            <option value="${s.id}">${s.name}</option>
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
                        <c:if test="${s.id == prescriptionDetail.prescription.id}">
                            <option selected value="${s.id}">${s.id}</option>
                        </c:if>
                        <c:if test="${s.id != prescriptionDetail.prescription.id}">
                            <option value="${s.id}">${s.id}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${prescriptionDetail.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${prescriptionDetail.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>



        </form:form>
    </section>
</div>
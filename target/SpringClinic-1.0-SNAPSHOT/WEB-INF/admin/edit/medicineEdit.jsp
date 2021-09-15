

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="medicine.title" />
        </h1>
        <form:form method="post" modelAttribute="medicine" acceptCharset="UTF-8" enctype="multipart/form-data">
            <form:errors path="*" element="div" cssClass="alert alert-danger" />
            <div class="form-group">
                <label for="name">
                    <spring:message code="medicine.name" />
                </label>
                <form:input id="name" cssClass="form-control" path="name"  />
                <form:errors path="name" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="price">
                    <spring:message code="medicine.price" />
                </label>
                <form:input id="price" cssClass="form-control" path="price" />
                <form:errors path="price" cssClass="text-danger" />
            </div>
            <div class="form-group">
                <label for="countInStock">
                    <spring:message code="medicine.countInStock" />
                </label>
                <form:input id="countInStock" cssClass="form-control" path="countInStock" />
            </div>
            <div class="form-group">
                <label for="manufacturingDate">
                    <spring:message code="medicine.manuDate" />
                </label>
                <form:input id="manufacturingDate" type="date" cssClass="form-control date-picker" path="manufacturingDate" />
            </div>
            <div class="form-group">
                <label for="expiryDate">
                    <spring:message code="medicine.expiryDate" />
                </label>
                <form:input id="expiryDate" type="date" cssClass="form-control date-picker" path="expiryDate" />
            </div>
            <div class="form-group">
                <label for="file">
                    <spring:message code="medicine.image" />
                </label>
                <form:input id="file" type="file" cssClass="form-control date-picker" path="file" accept="image/*"  required="required" />
            </div>

            <div class="form-group">
                <label for="sick">
                    <spring:message code="label.sick" />
                </label>
                <form:select cssClass="form-control" id="sick" path="sick">
                    <c:forEach items="${sickcommom}" var="s">
                        <c:if test="${s.id == medicine.sick.id}">
                            <option selected value="${s.id}">${s.name}</option>
                        </c:if>
                        <c:if test="${s.id != medicine.sick.id}">
                            <option value="${s.id}">${s.name}</option>
                        </c:if>
                    </c:forEach>
                </form:select>
            </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${medicine.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${medicine.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>



        </form:form>
    </section>
</div>
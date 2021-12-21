

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<div class="container">
    <section>
        <h1 class="text-center text-danger">
            <spring:message code="user.title" />
        </h1>
        <form:form method="post" modelAttribute="user" acceptCharset="UTF-8" enctype="multipart/form-data">
            <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <c:if test="${errMsg != null}">
            <div class="alert alert-danger">
                ${errMsg}
            </div>
        </c:if>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <form:input id="firstName" class="form-control" path="firstName" />
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <form:input id="lastName" class="form-control" path="lastName" />
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input id="email" class="form-control" path="email" />
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <form:input id="username" class="form-control" path="username" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <form:password id="password" class="form-control" path="password" />
        </div>
        <div class="form-group">
            <label for="confirm-password">Confirm Password</label>
            <form:password id="confirm-password" class="form-control" path="confirmPassword" />
        </div>
        <div class="form-group">
            <label for="userRole">User Role</label>
            <form:input id="userRole" class="form-control" path="userRole" />
        </div>
         <div class="form-group">
            <label for="dateJoined">Date Joined</label>
            <form:input id="dateJoined" type="date" class="form-control" path="dateJoined" />
        </div>
        <div class="form-group">
            <label for="file">
                <spring:message code="medicine.image" />
            </label>
            <form:input id="file" type="file" cssClass="form-control date-picker" path="file" accept="image/*" />
        </div>

            <div class="form-group">
                <form:hidden path="id" />
                <c:if test="${user.id > 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.update" />" />
                </c:if>
                <c:if test="${user.id <= 0}">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
                </c:if>
            </div>



        </form:form>
    </section>
</div>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <h1 class="text-center text-info">ĐĂNG KÝ TÀI KHOẢN</h1>

    <c:url value="/register" var="action" />
    <form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
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
            <label for="file">
                <spring:message code="medicine.image" />
            </label>
            <form:input onchange="loadFile(event)" id="file" type="file" cssClass="form-control date-picker" path="file" accept="image/*" />
            <img id="output" class="w-25 h-25"/>
        </div>
        <div>
            <input type="submit" value="Đăng ký" class="btn btn-danger mb-4" />
            
        </div>
    </form:form>
</div>
<script>
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
    output.onload = function() {
      URL.revokeObjectURL(output.src) // free memory
    }
  };
</script>
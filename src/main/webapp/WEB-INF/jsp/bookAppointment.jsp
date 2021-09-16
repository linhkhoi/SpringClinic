<%-- 
    Document   : bookAppointment
    Created on : Aug 5, 2021, 5:59:34 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test="${errMsg != null}">
    <h1>${errMsg}</h1>
</c:if>
<div class="container">
    <h2 class="text-center">Bảng giá khám</h2>       
  <table class="table table-info">
    <thead>
      <tr>
        <th>Thứ</th>
        <th>Khung giờ</th>
        <th>Giá khám</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>T2-T6</td>
        <td>9h-18h</td>
        <td>100,000 VNĐ</td>
      </tr>
      <tr>
        <td>T2-T6</td>
        <td>18h-21h</td>
        <td>150,000 VNĐ</td>
      </tr>
      <tr>
        <td>T7-CN</td>
        <td>9h-18h</td>
        <td>150,000 VNĐ</td>
      </tr>
      <tr>
        <td>T7-CN</td>
        <td>18h-21h</td>
        <td>200,000 VNĐ</td>
      </tr>
    </tbody>
  </table>
</div>
<link href="<c:url value="/css/index.css"/>" rel="stylesheet" />


<c:if test="${msg != ''}">
    <div class="alert alert-danger">
        <spring:message code="label.bookError" />
    </div>
</c:if>
<div class="container px-5 py-5 mx-auto">
    <div class="form bg-white">
        <div class="note">
            <h1 class="pt-3"><spring:message code="label.bookAppointment" /></h1>
        </div>
        <c:url value="/patient/bookingg" var="action" />
        <form:form method="post" modelAttribute="appointment" acceptCharset="UTF-8">
        <div class="form-content">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class=""><spring:message code="label.chooseDate" />: </label>
                         <form:input id="meetDate" type="date" cssClass="rounded-pill border-2 px-3 py-2" path="meetDate" />
                    </div>
                </div>
                    <div class="col-md-6">
                    <div class="form-group">
                        <label class=""><spring:message code="label.chooseDate" />: </label>
                        <form:input id="meetTime" type="time" cssClass="rounded-pill border-2 px-3 py-2" path="meetTime" />
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">

                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-warning" 
                           value="<spring:message code="label.add" />" />
            </div>
            </div>

        </div>
        </form:form>
    </div>
</div>

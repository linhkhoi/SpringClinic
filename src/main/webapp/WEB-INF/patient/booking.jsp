<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/index.css"/>" rel="stylesheet" />
<div class="container px-5 py-5 mx-auto">
    <div class="form bg-white">
        <div class="note">
            <h1 class="pt-3"><spring:message code="label.bookAppointment" /></h1>
        </div>

        <div class="form-content">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class=""><spring:message code="label.chooseDate" />: </label>
                        <input type="datetime-local" class="rounded-pill border-2 px-3 py-2" placeholder="Your Name *" value=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group ml-5 mt-1">
                        <label><spring:message code="label.bookFee" />: </label>
                        <label>1200000 VNĐ </label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10">

                </div>
                <div class="col-md-2 "> 
                    <input type="submit" value="<spring:message code="label.book" />" class="btn btn-danger right"/>
                </div>
            </div>

        </div>
    </div>
</div>
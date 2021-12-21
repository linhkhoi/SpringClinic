<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/index.css"/>" rel="stylesheet" />
<div class="container-fluid px-5 py-5 mx-auto">
    <h1 class="text-center text-light"><spring:message code="label.welcome" /></h1>
    <h4 class="text-center text-light"><spring:message code="label.introLogin" /></h4>
    <h4 class="text-center text-light"><spring:message code="label.introLogin2" /></h4>
    <div class="row">
        <img class="img-fluid col-md-4" src="<c:url value="/images/clinic1.jpg"/>" alt="123" />
        <img class="img-fluid col-md-4" src="<c:url value="/images/clinic2.jpg"/>" alt="123" />
        <img class="img-fluid col-md-4" src="<c:url value="/images/clinic3.jpg"/>" alt="123" />
    </div>
</div>

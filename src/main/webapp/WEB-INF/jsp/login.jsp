<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/css/login.css"/>" rel="stylesheet" />


<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Đã có lỗi
    </div>
    
</c:if>

<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        Bạn không có quyền truy cập
    </div>
</c:if>
<div class="container px-4 py-5 mx-auto">
    <div class="card card0">
        <div class="d-flex flex-lg-row flex-column-reverse">
            <div class="card card1">
                <div class="">
                    <c:url value="/login" var="action" />
                    <div class="row justify-content-center my-auto">
                    <form method="post" action="${action}" class="row justify-content-center my-auto">
                    <div class="col-md-8 col-10 my-1">
                        <div class="row justify-content-center px-3 mb-3"> <img id="logo" src="<c:url value="/images/logo.png" />"> </div>
                        <h3 class="mb-5 text-center heading"><spring:message code="label.login" /></h3>
                        <div class="form-group mb-0"> <label class="form-control-label text-muted"><spring:message code="label.userName"/></label><input id="username" name="username" class="form-control px-4" /></div>
                        <div class="form-group mb-0"> <label class="form-control-label text-muted"><spring:message code="label.password"/></label> <input type="password" id="password" name="password" class="form-control" /> </div>
                        <div class="row justify-content-center my-1 px-3"> <button class="btn-block btn-color" type="submit"><spring:message code="label.login" /></button> </div>
                        <div class="row justify-content-center my-2"> <a href="#"><small class="text-muted"><spring:message code="label.forgotPassword" /></small></a> </div>
                    </div>
                    </form>
                </div>
                </div>
                    <div class="row justify-content-center my-1 px-3"> <a href="https://www.facebook.com/dialog/oauth?client_id=274262237510505&redirect_uri=https://localhost:8443/SpringClinic/login-facebook" class="btn btn-block btn-facebook"> <i class="fab fa-facebook-f"></i>     <spring:message code="label.loginWith" /> Facebook</a> </div>
                    
                   <div class="row justify-content-center my-1 px-3"> <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://localhost:8443/SpringClinic/login-google&response_type=code
    &client_id=65963281795-guocpher4k54h97iijf3rrb662s4135b.apps.googleusercontent.com&approval_prompt=force" class="btn btn-block btn-twitter"> <i class="fab fa-google"></i>     <spring:message code="label.loginWith" /> Google</a> </div> 
                <div class="bottom text-center mb-5">
                    <p href="#" class="sm-text mx-auto mb-3"><spring:message code="label.dontHaveAccout" /><a class="btn btn-white ml-2" href="register"><spring:message code="label.register" /></a></p>
                </div>
            </div>
            <div class="card card2">
                <div class="my-auto mx-md-4 px-md-5 right">
                    <h3 class="text-white"><spring:message code="label.introLogin" /></h3> <small class="text-white"><spring:message code="label.introLogin2" /></small>
                </div>
            </div>
        </div>
    </div>
</div>
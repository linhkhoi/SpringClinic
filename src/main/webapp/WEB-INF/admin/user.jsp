
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container-fluid">

    <h1 class="text-center text-primary my-3"><spring:message code="user.list" /></h1>
    <div class="row my-2">  

        <div class="col-md-11">
            <form action="">
                <div class="row">
                    <div class="col-md-4">
                        <input class="form-control" type="text" name="kw" placeholder="Nhập từ khoá để tìm" />
                    </div>
                    <div class="col-md-1 p-0"> 
                        <input type="submit" value="<spring:message code="label.search" />" class="btn btn-warning"/>
                    </div>
                </div>
            </form>  
        </div>
        <div class="col-md-1"> 
            <a href="<c:url value="/admin/user-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
    </div>
        <div class="col-md-11">
         <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/12)}" var="i">
                <c:if test = "${page == i}">
                    <c:if test="${param.kw != ''}">
                         <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/admin/user-admin/"/>?kw=${param.kw}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''}">
                        <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/admin/user-admin/"/>?page=${i}">${i}</a></li>
                    </c:if>
                   
                </c:if>
                <c:if test = "${page != i}">
                    <c:if test="${param.kw != ''}">
                        <li class="page-item"><a class="page-link mx-0" href="<c:url value="/admin/user-admin/"/>?kw=${param.kw}&page=${i}">${i}</a></li>
                    </c:if>
                    <c:if test="${param.kw == ''}">
                       <li class="page-item"><a class="page-link mx-0" href="<c:url value="/admin/user-admin/"/>?page=${i}">${i}</a></li>
                    </c:if>
                </c:if>
            </c:forEach>
        </ul>
        </div>
    <table class="table align-middle">
        <thead>
            <tr>
                <th scope="col" class="col-md-0">#</th>
                <th scope="col" class="col-md-auto"><spring:message code="user.username" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.firstName" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.lastName" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.dateJoined" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.email" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.isActive" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="user.userRole" /></th>
                <th scope="col" class="col-md-1"><spring:message code="medicine.image" /></th>
                <th scope="col" class="col-md-1"></th>
                <th scope="col" class="col-md-1"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${users}">
                <tr>
                    <th scope="row">${m.id}</th>
                    <td>${m.username}</td>
                    <td>${m.firstName}</td>
                    <td>${m.lastName}</td>
                    <td>${m.dateJoined}</td>
                    <td>${m.email}</td>
                    <td>${m.isActive}</td>
                    <td>${m.userRole}</td>
                    <c:if test = "${m.avatar != null}">
                        <td><img class="img-fluid" src="${m.avatar}" /></td>
                        </c:if>
                        <c:if test = "${m.avatar == null}">
                        <td><img class="img-fluid" src="<c:url value="/images/default.jpg"/>" /></td>
                        </c:if>

                    <td class="">
                        <a href="<c:url value="/admin/user-edit/?userId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.update" /></a>
                    </td>
                    <td>
                        <a href="" class="btn btn-danger btn-sm px-3"><spring:message code="label.delete" /></a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</div>
<%-- 
    Document   : addPrescription
    Created on : Aug 11, 2021, 6:12:32 PM
    Author     : MSIGE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<h1 class="text-center text-danger">DANH MỤC THUỐC</h1>


<div class="row my-2">  

    <div class="col-md-8">
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
            <a href="<c:url value="/admin/medicine-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
        </div>
    </div>
        
        <ul class="pagination">
            <c:forEach begin="1" end="${Math.ceil(count/6)}" var="i">
                <c:if test = "${page == i}">
                    <li class="page-item active"><a class="page-link mx-0" href="<c:url value="/doctor/add-prescription/"/>?page=${i}">${i}</a></li>
                </c:if>
                <c:if test = "${page != i}">
                    <li class="page-item"><a class="page-link mx-0" href="<c:url value="/doctor/add-prescription/"/>?page=${i}">${i}</a></li>
                </c:if>
            </c:forEach>
        </ul>
    <table class="table align-middle">
        <thead>
            <tr>
                <th scope="col" class="col-md-0">#</th>
                <th scope="col" class="col-md-auto"><spring:message code="medicine.name" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="medicine.price" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="medicine.countInStock" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="medicine.manuDate" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="medicine.expiryDate" /></th>
                <th scope="col" class="col-md-1"><spring:message code="medicine.image" /></th>
                <th scope="col" class="col-md-auto"><spring:message code="label.sick" /></th>
                <th scope="col" class="col-md-1"></th>
                <th scope="col" class="col-md-1"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="m" items="${medicines}">
                <tr>
                    <th scope="row">${m.id}</th>
                    <td>${m.name}</td>
                    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${m.price}"/></td>
                    <td>${m.countInStock}</td>
                    <td>${m.manufacturingDate}</td>
                    <td>${m.expiryDate}</td>
                    
                    <c:if test = "${m.image != null}">
                        <td><img class="img-fluid" src="${m.image}" /></td>
                    </c:if>
                    <c:if test = "${m.image == null}">
                    <td><img class="img-fluid" src="<c:url value="/images/default.jpg"/>" /></td>
                    </c:if>
                    <td>${m.sick.name}</td>

                    <td class="">
                        <a href="<c:url value="/admin/medicine-edit/?medicineId=${m.id}" />" class="btn btn-info btn-sm px-3"><spring:message code="label.update" /></a>
                    </td>
                    <td>
                        <a href="" class="btn btn-danger btn-sm px-3"><spring:message code="label.delete" /></a>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    </div>
    <div class="col-md-4"> 
        <a href="<c:url value="/admin/doctor-edit/" />" class="btn btn-success"><spring:message code="label.add" /></a>
    </div>
</div>

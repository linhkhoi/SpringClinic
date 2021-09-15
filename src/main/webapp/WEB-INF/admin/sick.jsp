<%-- 
    Document   : sick
    Created on : Aug 4, 2021, 4:50:59 PM
    Author     : MSI GE66
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    
    <h1 class="text-center text-primary my-3">Danh sách bệnh</h1>
  <div class="row my-2">  
      
      <div class="col-md-11">
 <form action="">
    <div class="row">
        <div class="col-md-4">
            <input class="form-control" type="text" name="kw" placeholder="Nhập từ khoá để tìm" />
        </div>
        <div class="col-md-1 p-0"> 
            <input type="submit" value="Search" class="btn btn-warning"/>
        </div>
    </div>
</form>  
      </div>
    <div class="col-md-1"> 
            <a href="<c:url value="/admin/sick-edit/" />" class="btn btn-success">Thêm</a>
        </div>
  </div>
<table class="table align-middle">
  <thead>
    <tr>
      <th scope="col" class="col-md-1">#</th>
      <th scope="col" class="col-md-6">Tên</th>
      <th scope="col" class="col-md-6">Triệu chứng</th>
      <th scope="col" class="col-md-1"></th>
      <th scope="col" class="col-md-1"></th>
    </tr>
  </thead>
  <tbody>
      <c:forEach var="s" items="${sicks}">
          <tr>
      <th scope="row">${s.id}</th>
      <td>${s.name}</td>
      <td>${s.symptom}</td>
      <td class="">
        <a href="<c:url value="/admin/sick-edit/?sickId=${s.id}" />" class="btn btn-info btn-sm px-3">Sửa</a>
      </td>
      <td>
        <a href="" class="btn btn-danger btn-sm px-3">Xoá</a>
      </td>
    </tr>
      </c:forEach>
    
  </tbody>
</table>
</div>

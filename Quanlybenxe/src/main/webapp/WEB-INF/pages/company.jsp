<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ NHÀ XE</h1>

<c:url value="/companies" var="action" />
<form:form method="post" action="${action}" modelAttribute="company" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="companyName"  placeholder="Tên bến xe" path="companyName" />
        <label for="name">Tên bến xe</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="address"  placeholder="Địa chỉ bến xe" path="address" />
        <label for="name">Địa chỉ bến xe</label>
    </div>  
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="email"  placeholder="Email của bến xe" path="email" />
        <label for="name">Email của bến xe</label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="phoneNumber"  placeholder="Số điện thoại bến xe " path="phoneNumber" />
        <label for="name">Số điện thoại bến xe</label>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  id="isShippingAvailable"  placeholder="Có nhận giao hàng không" path="isShippingAvailable" />
        <label for="name">Có nhận giao hàng không</label>
    </div> 

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control"  id="avatar" path="file" />
        <label for="image">Ảnh nhà xe</label>
    </div>
    <!-- Hidden input for isActive field -->
    <form:hidden path="isActive" />



    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            Thêm
        </button>
        <form:hidden path="companyID" />
    </div>
</form:form>




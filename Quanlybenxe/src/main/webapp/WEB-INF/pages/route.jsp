<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ XE KHÁCH</h1>

<c:url value="/routes" var="action" />
<form:form method="post" action="${action}" modelAttribute="route" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="startLocation" placeholder="Biển số xe khách" path="startLocation" />
        <label for="startLocation">Điểm bắt đầu</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="endLocation" placeholder="Điểm kết thúc" path="endLocation" />
        <label for="endLocation">Điểm kết thúc</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="distance" placeholder="Khoảng cách" path="distance" />
        <label for="distance">Khoảng cách</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="estimatedDuration" placeholder="Thời gian ước tính" path="estimatedDuration" />
        <label for="estimatedDuration">Thời gian ước tính</label>
    </div>  
    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="description" placeholder="Mô tả" path="description" />
        <label for="description">Mô tả</label>
    </div>

    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            Thêm
        </button>
        <form:hidden path="routeID" />
    </div>
</form:form>

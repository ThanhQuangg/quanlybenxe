<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1 class="text-center">Danh Sách Xe Khách</h1>
<div>
    <a class="btn btn-info" href="<c:url value="/buses"/>"> Thêm xe khách</a>
</div>
<div>
    <a class="btn btn-info" href="<c:url value="/routes"/>"> Thêm chuyến xe </a>
</div>
<div>
    <a class="btn btn-info" href="<c:url value="/companies"/>"> Thêm bến xe </a>
</div>
<div>
    <a class="btn btn-info" href="<c:url value="/categories"/>"> Thêm loại xe </a>
</div>


<table class="table table-striped mt-1">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Biển số xe</th>
        <th>Sức chứa</th>
        <th>Loại xe</th>
        <th></th>
    </tr>
    <c:forEach items="${buses}" var="b">
        <tr>
            <td> <img class="card-img-top" src="${b.avatar}"  style="width:200px;"></td>
            <td>${b.busID}</td>
            <td>${b.plateNumber}</td>
            <td>${b.capacity}</td>
            <td><script>document.write(getCategoryName(${b.categoryID}));</script></td>
            <td>
                <c:url value="/api/buses/${b.busID}" var="url" />
                <a class="btn btn-info"href="<c:url value="/buses/${b.busID}"/>" >Cập nhật</a>
                <button onclick="deleteBus('${url}',${b.busID})" class="btn btn-danger">Xóa</button>
                
               
                
            </td>
        </tr>
    </c:forEach>
</table>

<script src="<c:url value="/js/script.js" />"></script>






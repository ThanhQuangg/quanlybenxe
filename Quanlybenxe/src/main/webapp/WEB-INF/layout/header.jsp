<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="header">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Quản Lí Bến Xe Khách</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Trang Chủ</a>
                    </li>

                    <c:forEach items="${categories}" var="c">
                        <li class="nav-item">
                            <a class="nav-link" href="#">${c.name}</a>
                        </li>
                    </c:forEach>
                    
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item">
                                <a class=" btn btn-info " href="<c:url value="/login" />">Đăng nhập</a>
                            </li>
                        </c:when>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="nav-item">
                                <a class=" btn btn-danger " href="<c:url value="/" />">Chào ${pageContext.request.userPrincipal.name}!</a>
                            </li>
                            <li class="nav-item">
                                <a class=" btn btn-info " href="<c:url value="/logout" />">Đăng xuất</a>
                            </li>
                        </c:when>
                    </c:choose>

                </ul>
            </div>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        </div>
    </nav>
</div>
<style>
    .header {
        position: fixed;
        width: 100%;
        z-index: 1000;
        overflow-y: auto;
    }
</style>
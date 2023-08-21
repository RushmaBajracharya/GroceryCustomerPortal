<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<div class="header">
			
            <div class="nav">
                <form action="search_customer" method="get" class="search">
                <input type="text" name="name" placeholder="Search..">
                <button type="submit"><i class="fas fa-search"></i></button>
                </form>
            </div>
            <c:if test="${not empty errorMsg}">
            <p><center>${errorMsg}</center></p>
            <c:remove var="errorMsg"/>
            </c:if>
        </div>
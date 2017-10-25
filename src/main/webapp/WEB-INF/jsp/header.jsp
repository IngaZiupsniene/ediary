<%--
  Created by IntelliJ IDEA.
  User: Dainius
  Date: 2017.09.11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


    <div class="container-fluid">
    <div class="">
    <c:if test="${pageContext.request.userPrincipal.name!=null}">
        <form id="logoutForm" method="post" action="${path}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <p>Labas :${pageContext.request.userPrincipal.name} |->
            <a onclick="document.forms['logoutForm'].submit()">Atsijungti</a>
        </p>
    </c:if>
    </div>

    </div>




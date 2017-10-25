<%--
  Created by IntelliJ IDEA.
  User: Dainius
  Date: 2017.10.03
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="path" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Registracijos langas</title>

    <link href="../../resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../resource/css/style.css" rel="stylesheet">



</head>
<body>

<div class = "container">
    <div class="wrapper">
<form:form method="post" action="/register" modelAttribute="userform" cssClass="form-signin">

            <h3 class="form-signin-heading">Registracijos langas</h3>
            <hr class="colorgraph"><br>
    <form:input path="username" type="text" placeholder="Vartotojo vardas" cssClass="form-control" autofocus="true"></form:input>
    <form:errors path="username"></form:errors>

    <form:input path="password" type="password" placeholder="Slaptažodis" cssClass="form-control" ></form:input>
    <form:errors path="password"></form:errors>

    <form:input path="passwordconfirm" type="password" placeholder="Pakartoti slaptažodį" cssClass="form-control" ></form:input>
    <form:errors path="passwordconfirm"></form:errors>

    <form:select path="role"  class="selectpicker form-control">
        <form:option value="0">Pasirinkite role</form:option>
        <form:options items="${rolelist}" itemValue="id" itemLabel="name"></form:options>
    </form:select>

            <button class="btn btn-lg btn-primary btn-block"  name="" value="" type="Submit">Registruotis</button>
        </form:form>>
    </div>
</div>

<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>
</body>
</html>

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
<html>
<head>
    <title>E-dienynas</title>

    <link type="text/css" href="../../resource/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="../../resource/css/style.css">
</head>
<body>

<div class="row" style="background-color: #708090">
    <jsp:include page="header.jsp"/>
</div>

<div class="row">
    <div class="col-md-2" style="background-color: 	#C0C0C0; height: 100%" >


        <div class="menu_simple">

            <ul>

                <li><a href="#">Link 1</a></li>

                <li><a href="#">Link 2</a></li>

                <li><a href="#">Link 3</a></li>

                <li><a href="#">Link 4</a></li>

                <li><a href="#">Link 5</a></li>

            </ul>

        </div>
    </div>
</div>

    <div class="col-md-9" style="background-color: 	#E6E6FA;  height: 100%">
        <p>pirmas puslapis</p>
    </div>
    <div class="col-md-1" style="background-color: 	#C0C0C0;  height: 100%">

    </div>
</div>
<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>

</body>
</html>

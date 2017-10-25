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
    <title>Prisijungimo langas</title>

    <link type="text/css" href="../../resource/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="../../resource/css/style.css" rel="stylesheet">

</head>
<body>

<div class = "container">
    <div class="wrapper">
        <form method="post" action="${path}/login" class="form-signin ${error!=null?'show-erorr':'ok'}">

            <h3 class="form-signin-heading">Prisijungimo langas</h3>
            <hr class="colorgraph"><br>
            <input name="username" type="text" placeholder="Vartotojo vardas" class="form-control" autofocus="true"/>
            <input name="password" type="password" placeholder="Slaptažodis" class="form-control " />



            <span>${error}</span>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block"  name="" value="" type="Submit">Prisijungti</button>
           <div class="row" style="padding: 20px">
               <h6><a href="${path}/register">Sukurti nauja vartotoja</a> </h6>
           </div>



        </form>
    </div>
</div>

<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>
</body>
</html>

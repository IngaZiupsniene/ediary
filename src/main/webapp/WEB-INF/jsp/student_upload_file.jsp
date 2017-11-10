
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Mokiniai</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <%--<link href="../../resource/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="../../resource/css/style.css">
    <link rel="stylesheet" type="text/css" href="../../resource/css/dropdown_menu.css">


</head>
<body>

<div class="row" style="background-color: #90578a">
    <jsp:include page="header.jsp"/>
</div>


<div class="container-fluid">
    <div class="row">
        <div class="col-md-2" style="height: auto; background-color: rgba(40,42,92,0.59)">
            <jsp:include page="menu.jsp"/>

        </div>


        <div class="col-md-9" style="height: 1000px; background-color: rgba(160,158,223,0.34)">


            <div class="row" style="padding-bottom: 20px">
               <div class="col-6">
                    <h3>Mokiniu saraso ikelimas is failo</h3>
            </div>
                </div>

            <%--<form method="POST" action="/studentfileupload" enctype="multipart/form-data">--%>
                <%--File to upload: <input type="file" name="file">--%>

                <%--Name: <input type="text" name="name">--%>


                <%--<input type="submit" value="Upload"> Press here to upload the file!--%>
            <%--</form>--%>


        <form method="post"  action="/studentfileupload"  enctype="multipart/form-data">


            <div class="row">
                <div class="col">
                    <input class="form-control" type="file" name="file" /><br/>

                </div>
                <div class="col">
                    <p>${message}</p>

                </div>


            </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col">
                    <button type="submit" class="btn btn-primary" >Irasyti</button>
                    </div>
                <%--<button type="submit" >Įrašyti</button>--%>
                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>

            </div>
        </form>
            </div>

        </div>


        <div class="col" style="height: 1000px; background-color: rgba(40,42,92,0.59)">

        </div>


</div>




<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>



<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>

<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
</body>
</html>
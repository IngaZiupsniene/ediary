
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
                    <h3>Mokinio redagavimas</h3>
            </div>
                </div>

            <div class="row">
                <div class="col-6">
                    <form action="/updatestudent" method="post">

                                <div class="row">
                                    <div class="col">
                                        <label>Vardas</label>
                                        <input type="text" name="name" class="form-control" value="${onestudent.getName()}"/>
                                    </div>
                                    <div class="col">
                                        <label>Pavardė</label>
                                        <input type="text" name="surname" class="form-control" value="${onestudent.getSurname()}"/>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col">
                                        <label>Identifikacijos kodas</label>
                                        <input type="text" name="personalcode" class="form-control" value="${onestudent.getPersonalcode()}"/>
                                    </div>
                                </div>

                    <%--<div class="row">--%>
                        <%--<div class="col">--%>
                            <%--<label>Tevai</label>--%>
                            <%--<p name="parents" class="form-control">${onestudent.getParents().getName()},${onestudent.getParents().getSurname()}</p>--%>

                            <%--<input type="button" class="btn btn-secondary" onclick="delete_parents_from_student(${onestudent.getId()})" value="Istrinti"/>--%>
                            <%--&lt;%&ndash;<input type="hidden" id="student_parent_id" value="${onestudent.getParents().getId()}">&ndash;%&gt;--%>

<%--<p>${onestudent.getId()}</p>--%>

                            <%--<button type="button" class="btn btn-secondary" value="${onestudent.getId()}" onclick="">Prideti</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>


                                    <div class="row" style="padding-top: 10px" >
                                        <div class="col">
                                            <label style="padding-top: 10px; text-decoration: underline">Priskirta klase:</label>
                                            <p type="text"  class="old_form">${onestudent.getSchoolclass().getName()}</p>
                                            <input type="hidden" name="schoolclass" value="${onestudent.getSchoolclass().getId()}">
                                        </div>

                                        <div class="col" style="padding-top: 10px" >
                                            <label>Pasirinkite kita klasę:</label>
                                            <select id="schoolclass"  name="schoolclass_new" class="form-control" style="padding-bottom: 10px" >
                                                <option style="padding-bottom: 10px" value="${onestudent.getSchoolclass().getId()}"> pasirinkta klase - ${onestudent.getSchoolclass().getName()}</option>
                                            <c:forEach var="cl" items="${classlist}">
                                                <option  value="${cl.getId()}">${cl.getName()}</option>
                                            </c:forEach>
                                            </select>
                                        </div>

                                    </div>

                        <div class="row" style="padding-top: 10px" >
                            <div class="col">
                                <label style="padding-top: 10px; text-decoration: underline">Mokinio tevai:</label>
                                <p type="text"  class="old_form">${onestudent.getParents().getSurname()}, ${onestudent.getParents().getName()} </p>
                                <input type="hidden" name="parents" value="${onestudent.getParents().getId()}">
                            </div>

                            <div class="col" style="padding-top: 10px" >
                                <label>Pasirinkite kita globeja:</label>
                                <select id="parents"  name="parents_new" class="form-control" style="padding-bottom: 10px" >
                                    <option style="padding-bottom: 10px" value="${onestudent.getParents().getId()}"> mokinio tevai - ${onestudent.getParents().getSurname()}, ${onestudent.getParents().getName()}</option>
                                    <c:forEach var="par" items="${parentlist}">
                                        <option  value="${par.getId()}">${par.getSurname()}, ${par.getName()} </option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>

                           <div class="row" style="padding-top: 10px">
                               <div class="col-1">

                                <button type="submit" class="btn btn-primary" name="studentid" value="${onestudent.getId()}" onclick="">Išsaugoti pakoreguota mokini</button>

                           </div>
                            </div>

            </form>

                </div>
            </div>

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
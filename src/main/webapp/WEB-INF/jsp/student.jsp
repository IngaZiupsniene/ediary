
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
                <div class="col-5" style="padding-top: 20px">
                    <h5>Mokinių sąrašas (mokinių skaičius: ${studlist.size()}) </h5>
                </div>

                <div class="col-2" style="padding-top: 20px">

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#studentModal">
                        Naujas mokinys
                    </button>
                </div >


                <!-- Modal -->

                <form action="/addstudent" method="post">
                <div class="modal fade" id="studentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Naujo mokinio įvedimas:</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <div class="row">
                                    <div class="col">
                                        <label>Vardas</label>
                                        <input type="text" name="name" class="form-control">
                                    </div>
                                    <div class="col">
                                        <label>Pavardė</label>
                                        <input type="text" name="surname" class="form-control">
                                    </div>
                                </div>

                                    <div class="row" style="padding-bottom: 10px" >

                                        <div class="col" style="padding-bottom: 10px" >
                                            <select id="" name="schoolclass" class="form-control" style="padding-bottom: 10px" >
                                                <option style="padding-bottom: 10px" value="0">Pasirinkite klasę </option>
                                            <c:forEach var="cl" items="${classlist}">
                                                <option  value="${cl.getId()}">${cl.getName()}</option>
                                            </c:forEach>
                                            </select>
                                        </div>

                                    </div>
                                <div class="row">
                                    <%--<label>Įvesti globėjų info:</label>--%>
                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#parentModal">
                                        Įvesti globėjų info:
                                    </button>
                                </div>

                            </div>

                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" onclick="">Išsaugoti</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Grįžti</button>
                            </div>
                        </div>
                    </div>
                </div>
</form>
                <!-- Modal -->
            </div>


            <div class="table-responsive">
                <input  class="border border-primary" type="text" id="myInput" onkeyup="student_search_byName()" placeholder="Ieškoti pagal pavardę..." >
                <table  id="myTable" class="table table-bordred ">
                    <tr style="border: double">
                        <th>Nr.</th>
                        <th>Pavardė</th>
                        <th>Vardas</th>
                        <th>Klasė</th>
                        <th>Globejai</th>
                        <th>Veiksmai</th>
                    </tr>

                    <c:forEach var="stud" items="${studlist}">
                        <tr id="student${stud.getId()}">
                            <td></td>
                            <td id="student_row_surname${stud.getId()}">${stud.getSurname()}</td>
                            <td id="student_row_name${stud.getId()}">${stud.getName()}</td>
                            <td id="student_row_class${stud.getId()}">${stud.getSchoolclass().getName()}</td>
                            <td id="student_row_parent${stud.getId()}">${stud.getParents().getSurname()}, ${stud.getParents().getName()}</td>
                            <td>
                                <input type="button" id="student_delete${stud.getId()}" class="btn btn-primary btn-xs" value="Pašalinti" onclick="student_delete_row(${stud.getId()})"/>
                                <input type="button" id="student_edit${stud.getId()}" class="btn btn-primary btn-xs" value="Redaguoti" onclick="student_edit_row(${stud.getId()})"/>
                                <input type="button" id="student_save${stud.getId()}" class="btn btn-primary btn-xs save_button" value="Saugoti" onclick="student_save_row(${stud.getId()})"/>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </div>
        <div class="col" style="height: 1000px; background-color: rgba(40,42,92,0.59)">

        </div>
    </div>


</div>

<script type="text/javascript" src="../../resource/js/student.js"></script>

<%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>


<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>



</body>
</html>
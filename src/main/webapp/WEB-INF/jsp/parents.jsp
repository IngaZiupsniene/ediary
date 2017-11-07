
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Globejai</title>


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
                    <h5>Globeju sąrašas (globeju skaičius: ${parentlist.size()}) </h5>
                </div>

                <div class="col-2" style="padding-top: 20px">

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#parentsModal">
                        Naujas globejas
                    </button>
                </div >


                <!-- Modal -->

                <form action="/addsparents" method="post">
                <div class="modal fade" id="parentsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                <div class="row">
                                    <div class="col">
                                        <label>Telefonas</label>
                                        <input type="text" name="phone" class="form-control">
                                    </div>
                                    <div class="col">
                                        <label>El. pastas</label>
                                        <input type="text" name="email" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <label>Adresas</label>
                                        <input type="text" name="adress" class="form-control">
                                    </div>
                                    <div class="col">
                                        <label>Identifikacijos kodas</label>
                                        <input type="text" name="personalcode" class="form-control">
                                    </div>
                                </div>




                                <div class="row" >
                                        <div class="col" >
                                            <label>Pasirinkite vaika:</label>
                                            <select  multiple id="" name="studentid" class="form-control" >
                                                <%--<option style="padding-bottom: 10px" value="0">Pasirinkite klasę </option>--%>
                                            <c:forEach var="stud" items="${studlist}">
                                                <option  value="${stud.getId()}">${stud.getName()},${stud.getSurname()} </option>
                                            </c:forEach>
                                            </select>
                                        </div>
                                </div>




                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" onclick="">Išsaugoti</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Grįžti</button>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
</form>
                <!-- Modal -->
            </div>


            <div class="table-responsive">
                <div class="row">
                    <div class="col-3">
                        <input  class="border border-primary myInput" type="text" id="parents_surname_search" onkeyup="parents_search_byName()" placeholder="Ieškoti pagal pavardę..." >
                    </div>
                    <div class="col-3">
                        <input  class="border border-primary myInput" type="text" id="parents_student_search" onkeyup="parents_search_byStudent()" placeholder="Ieškoti pagal mokini..." >
                    </div>
                </div>

                <table  id="myTable" class="table table-bordred ">
                    <tr style="border: double">
                        <th>Nr.</th>
                        <th>Pavardė</th>
                        <th>Vardas</th>
                        <th>Telefonas</th>
                        <th>El.pastas</th>
                        <th>Adresas</th>
                        <th>Vaikai</th>
                        <th>Veiksmai</th>
                    </tr>

                    <c:forEach var="par" items="${parentlist}">
                        <tr id="parents_row${par.getId()}">
                            <td></td>
                            <td id="student_row_surname${par.getId()}">${par.getSurname()}</td>
                            <td id="student_row_name${par.getId()}">${par.getName()}</td>
                            <td id="student_row_name${par.getId()}">${par.getPhone()}</td>
                            <td id="student_row_name${par.getId()}">${par.getEmail()}</td>
                            <td id="student_row_name${par.getId()}">${par.getAdress()}</td>
                            <td id="student_row_class${par.getId()}">
                                <table>


                                    <c:forEach items="${par.getStudentList()}" var="studlist">
                                        <c:out value="${studlist.getName()}, ${studlist.getSurname()}"/></br>

                                    </c:forEach>

                                </table>
                                    <%--${par.getStudentList().getName()}, ${par.getStudentList().getSurname()}</td>--%>
                            <td>
                                <input type="button" id="student_delete${par.getId()}" class="btn btn-primary btn-sm" value="Pašalinti" onclick="parents_delete_row(${par.getId()})"/>
                                <form action="/getoneparents" method="post">
                                    <button type="submit" name="getoneparents" id="${par.getId()}" class="btn btn-primary btn-sm" value="${par.getId()}">Redaguoti</button>
                                </form>
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

<script type="text/javascript" src="../../resource/js/parents.js"></script>



<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>


<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>



</body>
</html>
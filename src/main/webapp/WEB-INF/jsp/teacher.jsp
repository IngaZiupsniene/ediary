
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Mokytojai</title>


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
                    <h5>Mokytojų sąrašas (mokytojų skaičius: ${teacherlist.size()}) </h5>
                </div>

                <div class="col-2" style="padding-top: 20px">

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#teacherModal">
                        Naujas mokytojas
                    </button>
                </div >


                <!-- Modal -->

                <form action="/addteacher" method="post">
                <div class="modal fade" id="teacherModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Naujo mokytojo įvedimas:</h5>
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
                                        <label>El.paštas</label>
                                        <input type="text" name="email" class="form-control">
                                    </div>
                                </div>
                                <div class="row">
                                    <label class="form-control">Dėstomas dalykas:</label>
                                </div>
                                    <div class="row">
                                    <div class="col">
                                        <div class="">

                                            <label><input type="checkbox" class="chk" name="subject" value="lietuviu k.">Lietuvių k.</label>
                                        </div>
                                        <div class="checkbox">
                                            <label><input type="checkbox" class="chk" name="subject" value="matematika">Matematika</label>
                                        </div>
                                        <div class="checkbox ">
                                            <label><input type="checkbox" class="chk" name="subject" value="istorija" >Istorija</label>
                                        </div>
                                    </div>
                                    <div class="col">
                                    <div class="checkbox">
                                        <label><input type="checkbox" class="chk" name="subject" value="anglu k">Anglų k.</label>
                                    </div>
                                    <div class="checkbox">
                                        <label><input type="checkbox" class="chk" name="subject" value="muzika">Muzika</label>
                                    </div>
                                    <div class="checkbox ">
                                        <label><input type="checkbox" class="chk" name="subject" value="geografija" >Geografija</label>
                                    </div>
                                    </div>
                                    </div>

                                    <div class="row" style="padding-bottom: 10px" >

                                        <div class="col" style="padding-bottom: 10px" >
                                            <select id="" name="schoolclass" class="form-control" style="padding-bottom: 10px" >
                                                <option style="padding-bottom: 10px" value="0">Pasirinkite auklėjamą klasę </option>
                                            <c:forEach var="cl" items="${classlist}">
                                                <option  value="${cl.getId()}">${cl.getName()}</option>
                                            </c:forEach>
                                            </select>
                                        </div>

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
                <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names..">
                <%--<input  class="border border-primary" type="text" id="teacherInput" onkeyup="teacher_search_byName()" placeholder="Ieškoti pagal pavardę..." >--%>
                <table id="myTable" class="table table-bordred ">
                    <tr style="border: double">
                        <th>Nr.</th>
                        <th>Pavardė</th>
                        <th>Vardas</th>
                        <th>Telefonas</th>
                        <th>El.paštas</th>
                        <th>Dalykai</th>
                        <th>Auklėjama klasė</th>
                        <th></th>
                    </tr>

                    <c:forEach var="tc" items="${teacherlist}">
                        <tr id="teacher_row${tc.getId()}">
                            <td></td>
                            <td id="teacher_row_surname${tc.getId()}">${tc.getSurname()}</td>
                            <td id="teacher_row_name${tc.getId()}">${tc.getName()}</td>
                            <td id="teacher_row_phone${tc.getId()}">${tc.getPhone()}</td>
                            <td id="teacher_row_email${tc.getId()}">${tc.getEmail()}</td>
                            <td id="teacher_row_subject${tc.getId()}">
                                <table>


                                    <c:forEach items="${tc.getSubject()}" var="sub">
                                        <c:out value="${sub.getName()}"/><br>
                                    </c:forEach>

                                </table>


                            </td>
                            <td id="teacher_row_class${tc.getId()}">${tc.getSchoolclass().getName()}</td>

                            <td>
                                <input type="button" id="teacher_delete${tc.getId()}" class="btn btn-primary btn-xs" value="Pašalinti" onclick="teacher_delete_row(${tc.getId()})"/>
                                <input type="button" id="teacher_edit${tc.getId()}" class="btn btn-primary btn-xs" value="Redaguoti" onclick="teacher_edit_row(${tc.getId()})"/>
                                <input type="button" id="teacher_save${tc.getId()}" class="btn btn-primary btn-xs save_button" value="Saugoti" onclick="teacher_save_row(${tc.getId()})"/>

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

<script>
    function myFunction() {
        // Declare variables
        var input, filter, table, tr, td, i;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[0];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>
<script type="text/javascript" src="../../resource/js/teacher.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>



<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>

<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
</body>
</html>
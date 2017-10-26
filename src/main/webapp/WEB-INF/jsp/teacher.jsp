
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>E-dienynas</title>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <%--<link href="../../resource/css/bootstrap.min.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="../../resource/css/style.css">
    <link rel="stylesheet" type="text/css" href="../../resource/css/dropdown_menu.css">


</head>
<>

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
                <h4>Mokytojų sąrašas (mokytojų skaičius: xx) </h4>
                <div class="row">

                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                        Launch demo modal
                    </button>
                </div >


                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                ...
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal -->
            </div>


            <div class="table-responsive">
                <input  class="border border-primary" type="text" id="myInput" onkeyup="myFunction()" placeholder="Ieškoti pagal pavardę..." >
                <table  id="myTable" class="table table-bordred ">
                    <tr style="border: double">

                        <th>Pavardė, vardas</th>
                        <th>Telefonas</th>
                        <th>El.paštas</th>
                        <th>Dalykai</th>
                        <th>Auklėjama klasė</th>
                        <th></th>
                    </tr>

                    <c:forEach var="tc" items="${teacherlist}">
                        <tr id="list_row${tc.getId()}">
                            <td id="listid_row${tc.getId()}">${tc.getSurname()}, ${tc.getName()}</td>
                            <td id="listname_row${tc.getId()}">${tc.getPhone()}</td>
                            <td id="listname_row${tc.getId()}">${tc.getEmail()}</td>
                            <td id="listname_row${tc.getId()}">${tc.getSubject()}</td>
                            <td id="listname_row${tc.getId()}">${tc.getSchoolclass().getName()}</td>

                            <td>
                                <input type="button" class="btn btn-primary btn-xs" value="Pašalinti" onclick=""/>
                                <input type="button" class="btn btn-primary btn-xs" value="Atnaujinti" onclick=""/>

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

<script type="text/javascript" src="../../resource/js/table.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
<%--<script type="text/javascript" src="../../resource/js/modal.js"></script>--%>
<%--<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>--%>
<%--<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>--%>


</body>
</html>

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
               <div class="col-6">
                    <h3>Mokytojo redagavimas</h3>
            </div>
                </div>


            <div class="row" style="padding-top: 10px" >
                <div class="col">
                    <table class="table ">
                        <tr >
                            <th>Destomi dalykai:</th>
                            <th></th>
                            <th>
                                <%--cia bus mazas modalas - prideti nauja dalyka--%>
                                <button type="button"  class="btn btn-dark btn-sm" data-toggle="modal" data-target="#teacher_subject_update_Modal">Prideti nauja dalyka</button>

                                <!-- Modal -->

                                <form action="/addnewsubjecttoteacher" method="post">
                                    <div class="modal fade" id="teacher_subject_update_Modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Naujo dalyko pasirinkimas:</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">



                                                    <div class="row" >
                                                        <div class="col" >
                                                            <label class="" >Dėstomas dalykas: </label><br>
                                                            <select multiple class="form-control" name="subjectid" >

                                                            <c:forEach var="subject" items="${schoolsubjectlist}">

                                                                <option value="${subject.getId()}">${subject.getName()}</option>
                                                            </c:forEach>

                                                            </select>
                                                        </div>
                                                    </div>
                                                    <input type="hidden" name="teacher_id_to_subject" value="${oneteacher.getId()}">


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

                            </th>
                        </tr>

                        <c:forEach var="sub" items="${oneteacher.getSubject()}">
                            <tr id="subject_update_row${sub.getId()}">
                                <td><c:out value="${sub.getSchoolSubjectName().getName()}"></c:out></td>
                                <input type="hidden" name="subject_update_id" value="${sub.getId()}">
                                <td>
                                    <input type="button" id="delete_subject_update${sub.getId()}" class="btn btn-danger btn-sm" value="Pašalinti" onclick="delete_subject_update(${sub.getId()})"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>

            </div>



            <div class="row">
                <div class="col-6">
                <form action="/updateteacher" method="post">

                                <div class="row">
                                    <div class="col">
                                        <label>Vardas</label>
                                        <input type="text" name="name" class="form-control" value="${oneteacher.getName()}">
                                    </div>
                                    <div class="col">
                                        <label>Pavardė</label>
                                        <input type="text" name="surname" class="form-control" value="${oneteacher.getSurname()}">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <label>Telefonas</label>
                                        <input type="text" name="phone" class="form-control" value="${oneteacher.getPhone()}">
                                    </div>
                                    <div class="col">
                                        <label>El.paštas</label>
                                        <input type="text" name="email" class="form-control" value="${oneteacher.getEmail()}">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <label>Identifikacijos kodas</label>
                                        <input type="text" name="personalcode" class="form-control" value="${oneteacher.getPersonalcode()}">
                                    </div>


                                </div>

                    <div class="row" style="padding-top: 10px" >
                        <div class="col">
                            <label style="padding-top: 10px; text-decoration: underline">Priskirta klase:</label>
                            <p type="text"  class="old_form">${oneteacher.getSchoolclass().getName()}</p>
                            <input type="hidden" name="schoolclass" value="${oneteacher.getSchoolclass().getId()}">
                        </div>

                        <div class="col">
                            <label>Pasirinkite kita klasę:</label>
                            <select id="schoolclass"  name="schoolclass_new" class="form-control" style="padding: 0px" >
                                <option style="padding-bottom: 10px" value="${oneteacher.getSchoolclass().getId()}"> pasirinkta klase - ${oneteacher.getSchoolclass().getName()}</option>
                                <c:forEach var="cl" items="${classlist}">
                                    <option  value="${cl.getId()}">${cl.getName()}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>


                           <div class="row" style="padding-top: 10px">
                               <div class="col-6">
                                <button type="submit" class="btn btn-primary" name="teacherid" value="${oneteacher.getId()}" onclick="">Išsaugoti pakoreguota mokytoja</button>
                           </div>
                               <div class="col">

                                   <button type="button" class="btn btn-secondary" onclick="location.href='/teacherlist'" >Grizti</button>

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



<script type="text/javascript" src="../../resource/js/teacher.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>



<script type="text/javascript" src="../../resource/js/jquery-3.2.1.min.js"></script>

<script type="text/javascript" src="../../resource/js/bootstrap.min.js"></script>
</body>
</html>
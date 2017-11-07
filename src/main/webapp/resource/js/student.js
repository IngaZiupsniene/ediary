
function student_search_byName() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("student_surname_search");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
function student_search_byClass() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("student_class_search");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function student_delete_row(id) {
    document.getElementById("student_row"+id).outerHTML="";
    var student_delete_data={id:id};
    $.ajax({
        url:"/studentdelete",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(student_delete_data),
        datatype:"Json",
        success:function (data, status) {

        }
    });
}

function delete_parents_from_student(id) {
    // var parentid=document.getElementById("student_parent_id").value;
    var stud_id={id:id};
    $.ajax({
        url:"/parentdeletefromstudent",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(stud_id),
        datatype:"Json",
        success:function (data, status) {

        }
    });
}
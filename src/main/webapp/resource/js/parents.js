
function parents_search_byName() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("parents_surname_search");
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
function parents_search_byStudent() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("parents_student_search");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[6];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function parents_delete_row(id) {
    document.getElementById("parents_row"+id).outerHTML="";
    var parents_delete_data={id:id};
    $.ajax({
        url:"/parentsdelete",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(parents_delete_data),
        datatype:"Json",
        success:function (data, status) {

        }
    });
}

function delete_student_update(id) {
    document.getElementById("student_update_row"+id).outerHTML="";
    var student_delete__update_data={id:id};
    $.ajax({
        url:"/studentdeletefromparents",
        type:"POST",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(student_delete__update_data),
        datatype:"Json",
        success:function (data, status) {

        }
    });
}
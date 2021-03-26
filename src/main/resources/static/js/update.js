$(document).ready(
    function() {
        $("#addButton").click( async function(event) {
            const headers = {
                'Content-Type': 'application/json'
            };

            var formData = {
                password : $("#password-add").val(),
                firstName : $("#first-add").val(),
                lastName : $("#last-add").val(),
                email : $("#email-add").val()
            };

            document.getElementById("password-add").value="";
            document.getElementById("first-add").value="";
            document.getElementById("last-add").value="";
            document.getElementById("email-add").value="";

            var roles = $("#select-2").val();

            document.getElementById("select-2").value="";

            var param = '?role='+roles.toString();

            console.log(param);

            await fetch('http://localhost:8080/api/admin/add'+param, {
                 method: 'POST',
                 body: JSON.stringify(formData),
                 headers: headers
             });

            await getAllUsers('http://localhost:8080/api/admin');
        });


        $("#editButton").click( async function(event) {
            const headers = {
                'Content-Type': 'application/json'
            };

            var formData = {
                id : $("#id").val(),
                password : $("#password").val(),
                firstName : $("#first").val(),
                lastName : $("#last").val(),
                email : $("#email").val()
            };

            var roles = $("#select-1").val();

            var param = '?role='+roles.toString();

            console.log(param);

            await fetch('http://localhost:8080/api/admin/update'+param, {
                method: 'POST',
                body: JSON.stringify(formData),
                headers: headers
            });

            $("#editModal").modal('hide');

            await getAllUsers('http://localhost:8080/api/admin');
        });


    });

function modalFormEditUser(user) {
            $("#id").val(user.id);
            $("#first").val(user.firstName);
            $("#last").val(user.lastName);
            $("#email").val(user.email);
}


function editUser(id) {

    const headers = {
        'Content-Type': 'application/json'
    };

    return fetch('http://localhost:8080/api/admin/'+id, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(json => modalFormEditUser(json));
}

function deleteUser(id) {
    return fetch('http://localhost:8080/api/admin/delete/'+id, {
        method: 'DELETE'
    });
}

async function deleteUserAndUpdate(id) {
   await deleteUser(id);
   await getAllUsers('http://localhost:8080/api/admin');
}








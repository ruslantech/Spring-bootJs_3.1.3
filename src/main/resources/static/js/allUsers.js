getAllUsers('http://localhost:8080/api/admin');
getAllRoles('http://localhost:8080/api/admin/roles');

function getAllUsers(url) {
    const headers = {
        'Content-Type': 'application/json'
    };

    return fetch(url, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(json => allUsers(json));
}

function getAllRoles(url) {
    const headers = {
        'Content-Type': 'application/json'
    };

    return fetch(url, {
        method: 'GET',
        headers: headers
    })
        .then(response => response.json())
        .then(json => roles(json));
}

function roles(json) {
    const select1 = document.querySelector('#select-1');
    const select2 = document.querySelector('#select-2');
    json.forEach(role => {
        console.log(role.nameRole);

        const option1 = document.createElement('option');
        option1.innerHTML = `<option value="${role.id}">${role.nameRole}</option>`;

        const option2 = document.createElement('option');
        option2.innerHTML = `<option value="${role.id}">${role.nameRole}</option>`;

        select1.appendChild(option1);
        select2.appendChild(option2);
    })
}

function allUsers(json) {
    const main = document.querySelector('#table-tbody');

    $('.delete').empty();

    json.forEach(users => {
        var name = "";

        users.roles.forEach(role => {
            name = name + role.nameRole + ", ";
        });

        name = name.substring(0, name.length-2);
        console.log(name);

        const h2 = document.createElement('tr');
        h2.innerHTML = ` <tr>
                        <td>${users.id}</td>
                        <td>${name}</td>
                        <td>${users.firstName}</td>
                        <td>${users.lastName}</td>
                        <td>${users.email}</td>
                        <td>
                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#editModal"
                                                onclick="editUser(\''+${users.id}+'\')">
                                            Edit
                                        </button>
                        </td>
                        <td>
                        <button type="button" class="btn btn-danger"
                                                onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false
                                                else deleteUserAndUpdate(\''+${users.id}+'\')">
                                            Delete
                                        </button>
                        </td>
                        </tr>`;
        main.appendChild(h2);
    })
}
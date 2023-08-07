const url_users = 'http://localhost:8081/api/admin/users'
const url_roles = 'http://localhost:8081/api/roles'
const roles_list = []
const users_tab = document.getElementById('users-tab')
const new_user = document.getElementById('new-user')
const user_new = document.getElementById('user')
const tab_users = document.getElementById('users')
const form_new_user = document.getElementById('form-new-user')
user_new.style.height = '0%'

getRoles()
getAllUsers()
fillRoles('new')

new_user.addEventListener('click', ev => {
    tab_users.classList.remove("show", "active")
    user_new.classList.add("show", "active")
    users_tab.classList.remove('active')
    new_user.classList.add('active')
    user_new.style.height = '100%';
})

users_tab.addEventListener('click', ev => {
    user_new.classList.remove("show", "active")
    tab_users.classList.add("show", "active")
    new_user.classList.remove('active')
    users_tab.classList.add('active')
    user_new.style.height = '0%'
})

function getRoles() {
    fetch(url_roles)
        .then(response => response.json())
        .then(roles => {
            roles.forEach(role => {
                roles_list.push(role)
            })
        })
}

function getAllUsers() {
    const users_table = document.getElementById('users_table')

    users_table.innerHTML = ''

    fetch(url_users)
        .then(response => response.json())
        .then(users => {
            users.forEach(user => {
                users_table.insertAdjacentHTML(
                    'beforeend',
                    `
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.age}</td>
                        <td>${user.mail}</td>
                        <td>${user.roles.map(role => role.name).join(' ').replaceAll('ROLE_', '')}</td>
                        <td><button class="btn btn-info" data-toggle="modal" onclick="modalEdit(${user.id})">Edit</button></td>
                        <td><button class="btn btn-danger text-white" data-toggle="modal" onclick="modalDelete(${user.id})">Delete</button></td>
                    </tr>
                `
                )
            })
        })
        .catch(err => console.log(err))
}

function newUser() {
    form_new_user.addEventListener('submit', ev => {
        ev.preventDefault()
        ev.stopPropagation()

        const user = {
            name: document.getElementById('new-name').value,
            lastName: document.getElementById('new-last-name').value,
            age: document.getElementById('new-age').value,
            mail: document.getElementById('new-mail').value,
            password: document.getElementById('new-password').value,
            roles: getUserRoles(document.getElementById("new-roles").options)
        }

        fetch(url_users, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then(r => {
                getAllUsers()
                form_new_user.reset()
                return r.json()
        }
        ).catch(err => console.log(err))
    })
}

function modalDelete(id) {
    const modal_delete = new bootstrap.Modal(document.getElementById("modal-delete"))
    const form_delete = document.getElementById("form-delete")
    modal_delete.show()
    fillForm(id, 'delete')
    form_delete.addEventListener('submit', ev => {
        ev.preventDefault()
        ev.stopPropagation()

        fetch(url_users + `/${id}`, {method: 'DELETE'})
            .then(r => {
                modal_delete.hide()
                getAllUsers()
                return r.json()
            })
    })
}

function modalEdit(id) {
    const modal_edit = new bootstrap.Modal(document.getElementById("modal-edit"))
    const form_edit = document.getElementById("form-edit")
    modal_edit.show()
    fillForm(id, 'edit')
    form_edit.addEventListener('submit', ev => {
        ev.preventDefault()
        ev.stopPropagation()

        const user = {
            id: isNotEmpty(document.getElementById("edit-id")),
            name: isNotEmpty(document.getElementById('edit-name')),
            lastName: isNotEmpty(document.getElementById('edit-last-name')),
            age: isNotEmpty(document.getElementById("edit-age")),
            mail: isNotEmpty(document.getElementById("edit-mail")),
            password: isNotEmpty(document.getElementById("edit-password")),
            roles: getUserRoles(document.getElementById("edit-roles").options),
        }

        fetch(url_users, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then(r => {
            modal_edit.hide()
            getAllUsers()
            return r.json()
        }).catch(err => console.log(err))
    })
}

function getUserRoles(options) {
    let user_roles = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            for (let j = 0; j < roles_list.length; j++) {
                if (roles_list[j].name === options[i].value) {
                    user_roles.push(roles_list[j])
                }
            }
        }
    }
    return user_roles.length === 0 ? undefined : user_roles
}

function isNotEmpty(field) {
    if (field.value === '') return undefined
    return field.value
}

function fillForm(id, operation) {
    fetch(url_users + `/${id}`)
        .then(response => response.json())
        .then(user => {
            document.getElementById(`${operation}-id`).value = user.id
            document.getElementById(`${operation}-name`).value = user.name
            document.getElementById(`${operation}-last-name`).value = user.lastName
            document.getElementById(`${operation}-age`).value = user.age
            document.getElementById(`${operation}-mail`).value = user.mail
            if (operation === 'edit') document.getElementById("edit-password").value = ""
            else {
                const user_roles = user.roles
                for (let i = 0; i < user_roles.length; i++) {
                    document.getElementById('delete-roles')
                        .insertAdjacentHTML(
                            "beforeend",
                            `
                            <option>${user_roles[i].name}</option>
                            `
                        )
                }
            }
        })
    if (operation === 'edit') {
        fillRoles(operation)
    }
}

function fillRoles(operation) {
    fetch(url_roles)
        .then(response => response.json())
        .then(role => {
            role.forEach(role => {
                document.getElementById(`${operation}-roles`)
                    .insertAdjacentHTML(
                        "beforeend",
                        `
                    <option>${role.name}</option>
                `
                    )
            })
        })
}





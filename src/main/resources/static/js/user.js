url_user = 'http://localhost:8081/api/user'

getUser()

function getUser() {
    const user_table = document.getElementById('user_table')

    fetch(url_user)
        .then(response => response.json())
        .then(user => {
            user_table.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.mail}</td>
            <td>${user.roles.map(role => role.name).join(' ').replaceAll('ROLE_', '')}</td>
            `
        })
        .catch(err => console.log(err))
}
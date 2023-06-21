//store DOM elements inside variables
const registerForm = document.getElementById('register-form')
const registerUsername = document.getElementById('register-username')
const registerPassword = document.getElementById('register-password')

//base set of headers that will be included with every request
const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = 'http://localhost:8080/api/v1/users'

//function that will handle submitting the form
const handleSubmit = async(e) => {
    e.preventDefault()
    //handle values of the inputs and store them inside an object
    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }
    //make the request and handle the response
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
    .catch(err => console.error(err.message))

    const responseArr = await response.json()
    if(response.status === 200){
        window.location.replace(responseArr[0])
    }
}
//event listener
registerForm.addEventListener("submit", handleSubmit)

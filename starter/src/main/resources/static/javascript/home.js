//NEED TO READ COOKIE TO LOG IN USER---------------------------------------
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//dom elements
const submitForm = document.getElementById("note-form")
const noteContainer = document.getElementById("note-container")

//modal elements
let noteBody = document.getElementById('note-body')
let updateNoteBtn = document.getElementById('update-note-button')

const headers = {
    'Content-Type': 'application/json'
}
const baseUrl = "http://localhost:8080/api/v1/notes/"

//NEED LOGOUT METHOD THAT CLEARS COOKIE-----------------------------------------
function handleLogout(){
    let c = document.cookie.split(";")
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}
//FORM THAT SUBMITS NEW NOTES----------------------------------------------------
const handleSubmit = async(event) => {
    event.preventDefault()
    let bodyObj = {
        body: document.getElementById("note-input").value
    }
    await addNote(bodyObj);
    document.getElementById("note-input").value = ''
}
async function addNote(obj){
    const response = await fetch(`${baseUrl}user/${userId}`,{
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if(response.status == 200){
        return getNotes(userId);
    }
}
//RETRIEVE NOTES THAT ARE ASSOCIATED WITH USER, CREATE CARDS FOR THEM, APPEND THEM TO CONTAINER
async function getNotes(userId){
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createNoteCards(data))
        .catch(err => console.error(err))

}
//UPDATE A NOTE: SEPARATE GET REQUEST FOR NOTE SO MODAL CAN BE POPULATED----------------
async function getNoteById(noteId){
    await fetch(baseUrl + noteId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))

}
async function handleNoteEdit(noteId){
    let bodyObj = {
        id: noteId,
        body: noteBody.value
    }
    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getNotes(userId);
}
//BE ABLE TO DELETE A NOTE----------------------------------------------------------
async function handleDelete(noteId){
    await fetch(baseUrl + noteId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getNotes(userId);
}
//HELPER FUNCTIONS-----------------------------------------------------------------
//createNoteCards accepts an array of objects, loops through the array, creates a card for each item, and appends it to the notes container
const createNoteCards = (array) => {
    noteContainer.innerHTML = ''
    array.forEach(obj => {
        let noteCard = document.createElement("div")
        noteCard.classList.add("m-2")
        noteCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#note-edit-modal">Edit</button>
                    </div>
                </div>
            </div>
        `
        noteContainer.append(noteCard)
    })
}

//populateModal method which accepts an object and uses it to populate the field within the modal and assign a data tag to the save button element
const populateModal = (obj) => {
    noteBody.innerText = ''
    noteBody.innerText = obj.body
    updateNoteBtn.setAttribute('data-note-id', obj.id)
}

//invoke getNotes method
getNotes(userId);

//add event listeners
submitForm.addEventListener("submit", handleSubmit)

updateNoteBtn.addEventListener("click",(event) =>{
    let noteId = event.target.getAttribute('data-note-id')
    handleNoteEdit(noteId);
})









// var req = null;
// function submitForm() {
//     req = new XMLHttpRequest();
//     req.onreadystatechange = handleReq;
//     req.open("GET", "simpleResponse.txt?t=" + new Date().getTime(), true);
//     req.send(null);
// }
// function handleReq() {
//
//     if (req.readyState == 4)
//     if (req.status == 200)
//     document.ajax.dyn.value = "Received:" + req.responseText;
//     else
//     document.ajax.dyn.value = "Error code " + req.status;
// }
// var xmlHttp;
// function startRequest() {
//     createXMLHttpRequest();
//     xmlHttp.onreadystatechange = handleStateChange;
//     xmlHttp.open("GET", "innerHTML.txt", true);
//     xmlHttp.send(null);
// }
// function createXMLHttpRequest() {
//     xmlHttp= new XMLHttpRequest();
// }
// function handleStateChange() {
//     if (xmlHttp.readyState== 4 && xmlHttp.status== 200)
//     document.getElementById("schedule").innerHTML=
//     xmlHttp.responseText;
// }
//
// let usernameRequest = null;
// function validateUsername() {
//     const username = document.getElementById('username').value;
//     const validationResponse = document.getElementById('validationResponse');
//
//     usernameRequest = new XMLHttpRequest();
//     usernameRequest.onreadystatechange = usernameRequestHandle;
//     usernameRequest.open('GET', `/ajax/async?username=${username}&t=${new Date().getTime()}`, true);
//     usernameRequest.send(null);
// }
//
//     function usernameRequestHandle() {
//     const validationResponse = document.getElementById('validationResponse');
//
//     if (usernameRequest.readyState === 4) {
//     if (usernameRequest.status === 200) {
//     const response = usernameRequest.responseText;
//     validationResponse.textContent = `Username is ${response}`
// } else {
//     validationResponse.textContent = `Error: ${usernameRequest.status}`;
// }
// }
// }
//

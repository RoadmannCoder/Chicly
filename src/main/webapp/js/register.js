///////////////////////////////Handling Credit Limit Validation//////////////////////////////////////////////////////////
function checkCreditLimit() {
    var creditValue = document.getElementById("creditLimit").value;
    if (creditValue < 0) {
        document.getElementById("crediterror").innerText = "Credit limit must be more than 0";
    } else {
        document.getElementById("crediterror").innerText = "";
    }

}
// ////////////////////////////Handling Phone Number Validation//////////////////////////////////////////////////////////
var phoneNumReq;
function checkPhoneNumber() {
    var phoneNumber = document.getElementById("phoneNumber").value;

    const phoneRegex = /^01[0125][0-9]{8}$/;

    if (!phoneRegex.test(phoneNumber)){
        document.getElementById("phoneerror").innerText = "Phone number is not valid";
        return;
    }else{
        document.getElementById("phoneerror").innerText = "";
    }
    phoneNumReq = new XMLHttpRequest();
    phoneNumReq.onreadystatechange = handlePhoneNumReq;

    phoneNumReq.open("POST", "register", true);
    phoneNumReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    phoneNumReq.send("phoneNo="+phoneNumber+"&checkType=phoneCheck");
}
function handlePhoneNumReq() {
    if (phoneNumReq.readyState === 4 && phoneNumReq.status === 200) {
        document.getElementById("phoneerror").innerText = phoneNumReq.responseText;
    } else if (phoneNumReq.readyState === 4) {
        document.getElementById("phoneerror").innerText = "Error code: " + phoneNumReq.responseText;
    }
}
// ////////////////////////////Handling Email Validation//////////////////////////////////////////////////////////
var emailReq;

function checkEmail() {
    var email = document.getElementById("email").value;

    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // Test the phone number against the regex
    if (!emailRegex.test(email)){
        document.getElementById("emailerror").innerText = "Email is not valid";
        return;
    }else{
        document.getElementById("emailerror").innerText = "";
    }
    emailReq = new XMLHttpRequest();
    emailReq.onreadystatechange = handleEmailReq;
    emailReq.open("POST", "register", true);
    emailReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    emailReq.send("email="+email+"&checkType=emailcheck");
}
function handleEmailReq() {
    if (emailReq.readyState === 4 && emailReq.status === 200) {
        document.getElementById("emailerror").innerText = emailReq.responseText;
    } else if (emailReq.readyState === 4) {
        document.getElementById("emailerror").innerText = "Error code: " + emailReq.responseText;
    }
}
// ////////////////////////////Handling Username Validation//////////////////////////////////////////////////////////

var usernameReq;
function checkUserName(){
    usernameReq = new XMLHttpRequest();
    usernameReq.onreadystatechange = handleUserReq;
    yourvalue = document.getElementById("userName").value;
    console.log("value"+yourvalue);
    usernameReq.open("POST", "register", true);
    usernameReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    usernameReq.send("uName="+yourvalue+"&checkType=usernamecheck");
}
function handleUserReq() {
    if (usernameReq.readyState === 4 && usernameReq.status === 200) {
        document.getElementById("usernameerror").innerHTML = usernameReq.responseText;
    }
    else if (usernameReq.readyState === 4) {
        document.getElementById("usernameerror").innerHTML = "Error code: " + emailReq.responseText;
    }
}
// ////////////////////////////Check Form Validation//////////////////////////////////////////////////////////
function checkCondition() {
    var registerBtn = document.getElementById("registerBtn");

    if (document.getElementById('usernameerror').textContent !=="") {
        registerBtn.disabled = true; // Enable the button if the condition is met
    } else if(document.getElementById('crediterror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
    }
    else if(document.getElementById('emailerror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
    }
    else if(document.getElementById('phoneerror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
    }else{
        registerBtn.disabled = false;
    }
}
///////////////////////////////////////////////////////////////////////////////////////
let currentStep = 1;

function showStep(step) {
    document.querySelectorAll('.form-step').forEach((element) => {
        element.style.display = 'none';
    });
    document.getElementById('step-' + step).style.display = 'block';
}

function nextStep() {
    if (validateCurrentStep()) {
        currentStep++;
        if (currentStep > 4) currentStep = 4;
        showStep(currentStep);
    }
}

function prevStep() {
    currentStep--;
    if (currentStep < 1) currentStep = 1;
    showStep(currentStep);
}

function validateCurrentStep() {
    let valid = true;
    const stepElement = document.getElementById('step-' + currentStep);
    stepElement.querySelectorAll('input[required]').forEach((input) => {
        if (!input.checkValidity()) {
            valid = false;
            input.reportValidity();
        }
    });
    return valid;
}

// Initialize
showStep(currentStep);
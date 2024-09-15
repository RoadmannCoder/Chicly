document.addEventListener("DOMContentLoaded", function() {
    // List of governorates (cities)
    const governorates = [
        "Cairo",
        "Alexandria",
        "Giza",
        "Port Said",
        "Suez",
        "Mansoura",
        "Tanta",
        "Aswan",
        "Asyut",
        "Ismailia",
        "Fayoum",
        "Minya",
        "Daqahliya",
        "Kafr El Sheikh",
        "Beni Suef",
        "Zagazig",
        "Qena",
        "Luxor",
        "Matruh",
        "New Valley (Wadi El Nile)",
        "Red Sea",
        "North Sinai",
        "South Sinai",
        "Sohag",
        "Qalyubia",
        "Sharqia"
    ];

    // Get the select element
    const citySelect = document.getElementById("city");

    // Populate the city dropdown
    governorates.forEach(city => {
        const option = document.createElement("option");
        option.value = city;
        option.text = city;
        citySelect.add(option);
    });
    const selectedCity = document.getElementById("citySession").getAttribute('data-city');

    if (selectedCity) {
        citySelect.value = selectedCity;
    }
});
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
    var phoneNumberSession = document.getElementById("phoneNumberSession").getAttribute('data-phone');
    const phoneRegex = /^01[0125][0-9]{8}$/;
    if (!phoneRegex.test(phoneNumber)) {
        document.getElementById("phoneerror").innerText = "Phone number is not valid";
        return;
    }else if(phoneNumber !== phoneNumberSession.trim()) {
        phoneNumReq = new XMLHttpRequest();
        phoneNumReq.onreadystatechange = handlePhoneNumReq;
        phoneNumReq.open("POST", "phoneValidator", true);
        phoneNumReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        phoneNumReq.send("phoneNo=" + encodeURIComponent(phoneNumber));
    } else {
        document.getElementById("phoneerror").innerText = "";
    }
}
function handlePhoneNumReq() {
    if (phoneNumReq.readyState === 4) {
        if (phoneNumReq.status === 200) {
            document.getElementById("phoneerror").innerText = phoneNumReq.responseText;
        } else {
            document.getElementById("phoneerror").innerText = "Error code: " + phoneNumReq.status;
        }
    }
}

// ////////////////////////////Handling Email Validation//////////////////////////////////////////////////////////
var emailReq;

function checkEmail() {
    var email = document.getElementById("email").value;
    var emailSession = document.getElementById("emailSession").getAttribute('data-email');
    console.log(emailSession);
    // Regular expression for validating an Email
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // Test the email against the regex
    if (!emailRegex.test(email)) {
        document.getElementById("emailerror").innerText = "Email is not valid";
        return;
    }else if(email !== emailSession.trim()){
        // Create and send AJAX request
        emailReq = new XMLHttpRequest();
        emailReq.onreadystatechange = handleEmailReq;
        emailReq.open("POST", "emailValidator", true);
        emailReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        emailReq.send("email=" + encodeURIComponent(email));
    } else {
        document.getElementById("emailerror").innerText = "";
    }
}
function handleEmailReq() {
    if (emailReq.readyState === 4) {
        if (emailReq.status === 200) {
            document.getElementById("emailerror").innerText = emailReq.responseText;
        } else {
            document.getElementById("emailerror").innerText = "Error code: " + emailReq.status;
        }
    }
}

// ////////////////////////////Handling Username Validation//////////////////////////////////////////////////////////
var usernameReq;

function checkUserName() {

    var userName = document.getElementById("userName").value;
    var usernameSession = document.getElementById("userNameSession").getAttribute('data-username');
    console.log(usernameSession );
    if(userName === usernameSession.trim())
        return;
    usernameReq = new XMLHttpRequest();
    usernameReq.onreadystatechange = handleUserReq;
    console.log("value: " + userName);
    usernameReq.open("POST", "usernameValidator", true);
    usernameReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    usernameReq.send("uName=" + encodeURIComponent(userName));
}
function handleUserReq() {
    if (usernameReq.readyState === 4) {
        if (usernameReq.status === 200) {
            document.getElementById("usernameerror").innerHTML = usernameReq.responseText;
        } else {
            document.getElementById("usernameerror").innerHTML = "Error code: " + usernameReq.status;
        }
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
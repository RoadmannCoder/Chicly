// Preview the new image before submission
function previewNewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('newImagePreview');
        output.src = reader.result;
        output.style.display = 'block';
    }
    reader.readAsDataURL(event.target.files[0]);
}
function checkPrice() {
    var creditValue = document.getElementById("price").value;
    if (creditValue < 0) {
        document.getElementById("priceerror").innerText = "Price must be more than 0";
    } else {
        document.getElementById("priceerror").innerText = "";
    }

}
function checkQuantity() {
    var creditValue = document.getElementById("quantity").value;
    if (creditValue < 0) {
        document.getElementById("quantityerror").innerText = "Stock must be more than 0";
    } else {
        document.getElementById("quantityerror").innerText = "";
    }
}
function checkErrors() {
    var updateBtn = document.getElementById("updateBtn");

    if (document.getElementById('quantityerror').textContent !=="") {
        updateBtn.disabled = true; // Enable the button if the condition is met
    } else if(document.getElementById('priceerror').textContent !==""){
        updateBtn.disabled = true; // Disable the button if the condition is not met
    }else{
        updateBtn.disabled = false;
    }
}
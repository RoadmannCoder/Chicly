function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('imagePreview');
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
    var addBtn = document.getElementById("addBtn");

    if (document.getElementById('quantityerror').textContent !=="") {
        addBtn.disabled = true; // Enable the button if the condition is met
    } else if(document.getElementById('priceerror').textContent !==""){
        addBtn.disabled = true; // Disable the button if the condition is not met
    }else{
        addBtn.disabled = false;
    }
}
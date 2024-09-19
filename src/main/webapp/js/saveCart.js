window.addEventListener('beforeunload', function (event) {
    // Create a synchronous XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Open a GET request to your servlet
    xhr.open('GET', '/cartsave', false);  // false for synchronous request

    // Send the request
    xhr.send();

    // Optional: Provide a custom message if needed
    // event.returnValue = "You have unsaved changes!";
});
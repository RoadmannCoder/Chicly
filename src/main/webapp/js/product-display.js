$(document).ready(function() {

    const urlParams = new URLSearchParams(window.location.search);

    var minPrice = parseInt(urlParams.get('minPrice') || 20, 10);
    var maxPrice = parseInt(urlParams.get('maxPrice') || 1500, 10);


    // Ensure default values if parsing fails
    if (isNaN(minPrice)) minPrice = 20;
    if (isNaN(maxPrice)) maxPrice = 1500;


    $(".price-range").slider({
        range: true,
        min: 20, // minimum value
        max: 1500, // maximum value
        values: [minPrice, maxPrice],
        slide: function(event, ui) {
            $("#minamount").val(ui.values[0]);
            $("#maxamount").val(ui.values[1]);
        }
    });

    // Initialize the input fields with the slider values
    $("#minamount").val(minPrice);
    $("#maxamount").val(maxPrice);

    // Update the slider when input values are manually changed
    $("#minamount, #maxamount").on("change", function() {
        var min = parseInt($("#minamount").val(), 10);
        var max = parseInt($("#maxamount").val(), 10);

        if (!isNaN(min) && !isNaN(max)) {
            $(".price-range").slider("values", [min, max]);
        }
    });

});
document.addEventListener('DOMContentLoaded', function () {
    const radios = document.querySelectorAll('input[type=radio]');
    let lastChecked = null;

    radios.forEach(function (radio) {
        radio.addEventListener('click', function () {
            if (lastChecked === this) {
                // Uncheck the radio button if it's clicked again
                this.checked = false;
                lastChecked = null; // Reset last checked
            } else {
                // Set the current radio button as the last checked one
                lastChecked = this;
            }
        });
    });
});
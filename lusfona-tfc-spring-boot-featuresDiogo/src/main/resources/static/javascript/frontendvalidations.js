document.addEventListener('DOMContentLoaded', function() {
    const sexSelect = document.getElementById('sex');

    sexSelect.addEventListener('change', function(event) {
        const selectedValue = event.target.value;

        if (['M', 'F', 'O'].includes(selectedValue)) {
            console.log('Valid input:', selectedValue);
        } else {
            console.error('Invalid input:', selectedValue);
            // Optionally, reset the select to a default or prompt user
            event.target.value = ''; // Resetting to an empty value
            alert('Invalid selection. Please choose Male, Female, or Other.');
        }
    });
});

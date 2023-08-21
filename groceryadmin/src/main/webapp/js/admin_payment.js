const paymentStatusSelect = document.getElementById('paymentStatus');

// Get the saved color from local storage
const savedColor = localStorage.getItem('paymentStatusColor');

// If a color was found in local storage, set the background color of the select element
if (savedColor) {
  paymentStatusSelect.style.backgroundColor = savedColor;
}

paymentStatusSelect.addEventListener('change', function() {
  const selectedOptionValue = this.value;
  const selectElement = this;
  
  if (selectedOptionValue === 'paid') {
    selectElement.style.backgroundColor = '#86e49d';
    selectElement.style.color='#006b21';
  } else if (selectedOptionValue === 'unpaid') {
    selectElement.style.backgroundColor = '#d893a3';
    selectElement.style.color='#b30021';
  } else if (selectedOptionValue === 'pending') {
    selectElement.style.backgroundColor = '#ebc474';
    selectElement.style.color='black';
  } else {
    selectElement.style.backgroundColor = 'white';
  }
   // Save the color to local storage
   localStorage.setItem('paymentStatusColor', selectElement.style.backgroundColor);
});

// function updateSelectedOption() {
//     var mySelect = document.getElementById("paymentStatus");
//     mySelect.addEventListener("change", function() {
//     localStorage.setItem("selectedOption", mySelect.value);
//   });

//   var selectedOption = localStorage.getItem("selectedOption");
//   if (selectedOption) {
//     var option = mySelect.querySelector("[value='" + selectedOption + "']");
//     if (option) {
//       option.selected = true;
//     }
//   }
//   }

// Get the drop-down element
var dropdown = document.getElementById("paymentStatus");

// Load the previously selected value from local storage
var selectedValue = localStorage.getItem("selectedOption");

// Set the selected value if it exists
if (selectedValue != null) {
  dropdown.value = selectedValue;
}

// Listen for changes to the drop-down selection
dropdown.addEventListener("change", function() {
  // Store the selected value in local storage
  localStorage.setItem("selectedOption", dropdown.value);
});


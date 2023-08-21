function validate(){
    const form = document.getElementById('registration-form');
    const nameInput = document.getElementById('name');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const emailInput = document.getElementById('email');
    const contactInput = document.getElementById('contact');

    const name = nameInput.value.trim();
    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();
    const email = emailInput.value.trim();
    const contact = contactInput.value.trim();

    const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    if (name === '' || username === '' || password === '' || email === '' || contact === '') {
        alert('Please enter all fields');
        return;
      }
    // Check if email is valid   
    else if (!emailRegex.test(email)) {
      alert("Please enter a valid email address.");
      return false;
    }

    // Check if password meets minimum requirements
    else if (password.length < 5) {
      alert("Password must be at least 5 characters long.");
      return false;
    }
     // If all fields are valid, alert the user with a success message
     alert('Customer added successfully!');
}
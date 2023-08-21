function validate(){
     var name = document.getElementById("name").value;
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var email = document.getElementById("email").value;
            var contact = document.getElementById("contact").value;
            
            var nameError = document.getElementById("name-error");
            var usernameError = document.getElementById("username-error");
            var passwordError = document.getElementById("password-error");
            var emailError = document.getElementById("email-error");
            var contactError = document.getElementById("contact-error");
            
            var isValid = true;
            
            // Clear previous error messages
            nameError.innerHTML = "";
            usernameError.innerHTML = "";
            passwordError.innerHTML = "";
            emailError.innerHTML = "";
            contactError.innerHTML = "";
            
            if (name.trim() === "") {
                nameError.innerHTML = "Please enter a name";
                isValid = false;
            }
            
            if (username.trim() === "") {
                usernameError.innerHTML = "Please enter a username";
                isValid = false;
            }
            
            if (password.trim() === "") {
                passwordError.innerHTML = "Please enter a password";
                isValid = false;
            }
            
            if (email.trim() === "") {
                emailError.innerHTML = "Please enter an email address";
                isValid = false;
            }
            
            if (contact.trim() === "") {
                contactError.innerHTML = "Please enter a contact number";
                isValid = false;
            }
            
            if (name.length < 3) {
                nameError.innerHTML = "Name must be at least 3 characters long";
                isValid = false;
            }
            
            var emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
            if (!email.match(emailRegex)) {
                emailError.innerHTML = "Invalid email address";
                isValid = false;
            }
            
            return isValid; // Proceed with form submission if all validations passed
        
}
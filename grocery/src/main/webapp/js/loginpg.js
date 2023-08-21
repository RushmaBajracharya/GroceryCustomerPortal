function login(){
    const form = document.getElementById('login-form');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');

    const username = usernameInput.value.trim();
    const password = passwordInput.value.trim();

    if (username === '' || password === '') {
        alert('Please enter all fields');
        return;
      }

      if (username === 'myusername' && password === 'mypassword') {
        window.location.assign("/index.jsp");
        alert('Login successful!');
        // form.reset();
        
      } else {
        alert('Invalid username or password');
      }
}


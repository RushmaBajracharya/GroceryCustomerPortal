function changePassword() {
    const previousPasswordInput = document.getElementById('previous-password');
    const newPasswordInput = document.getElementById('new-password');
    const confirmPasswordInput = document.getElementById('confirm-password');
  
    // Validate previous password
    if (previousPasswordInput.value === '') {
      alert('Please enter your previous password');
      return;
    }
  
    // Validate new password
    if (newPasswordInput.value === '') {
      alert('Please enter a new password');
      return;
    } else if (newPasswordInput.value.length < 5) {
      alert('Password must be at least 5 characters');
      return;
    }
  
    // Validate confirm password
    if (confirmPasswordInput.value === '') {
      alert('Please confirm your new password');
      return;
    } else if (newPasswordInput.value !== confirmPasswordInput.value) {
      alert('Passwords do not match');
      return;
    }
  
    // If all fields are valid, alert the user with a success message
    alert('Password changed successfully!');
  }
  
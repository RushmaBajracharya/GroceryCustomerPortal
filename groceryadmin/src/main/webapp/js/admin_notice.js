const noticeForm = document.getElementById('notice-form');
  const noticeTextarea = document.getElementById('notice-text');

  noticeForm.addEventListener('submit', (event) => {
    event.preventDefault();

    // Save notice to local storage
    localStorage.setItem('notice', noticeTextarea.value);

    // Show success message
    alert('Notice saved successfully!');
  });
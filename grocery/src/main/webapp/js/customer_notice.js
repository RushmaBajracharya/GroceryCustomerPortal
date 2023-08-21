const noticeContainer = document.getElementById('notice-container');

  // Get notice from local storage
  const notice = localStorage.getItem('notice');

  if (notice) {
    // Show notice in notice container
    noticeContainer.innerHTML = `
      <p>${notice}</p>
    `;
  } else {
    // Show default message in notice container
    noticeContainer.innerHTML = `
      <p>There is no notice at the moment.</p>
    `;
  }
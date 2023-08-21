// Function to check if the current date is the last day of the month
/*function isLastDayOfMonth() {
    const date = new Date();
    const month = date.getMonth();
    const year = date.getFullYear();    
    const lastDayOfMonth = new Date(year, month + 1, 0);
    return date.getDate() === lastDayOfMonth.getDate();
    }

function showNotification() {
    let notificationBox = document.querySelector('.notification-box');
    let notificationBtn = document.querySelector('#notification-btn');
    notificationBox.style.display = 'block';
    notificationBtn.style.color='red';
    notificationBtn.onclick = () =>{
        notificationBox.classList.toggle('active'); 
        userAccount.classList.remove('active'); 
        navBar.classList.remove('active'); 
    }
}
// Check the current date and display a notification if it's the last day of the month
//if (isLastDayOfMonth()) {
    showNotification();
 // }*/
 




    
//account on navbar
 let userAccount = document.querySelector('.user-account');
    document.querySelector('#account-btn').onclick = () =>{
        userAccount.classList.toggle('active'); 
       /* notificationBox.classList.remove('active'); */
        navBar.classList.remove('active'); 
    }
//afer mediaquery for navbar
let navBar = document.querySelector('.navbar');
    document.querySelector('#menu-btn').onclick = () =>{
        navBar.classList.toggle('active'); 
       /* notificationBox.classList.remove('active'); */
        userAccount.classList.remove('active'); 
    }


    window.onscroll = () =>{
       /* notificationBox.classList.remove('active'); */
        userAccount.classList.remove('active'); 
        navBar.classList.remove('active'); 
    }
document.addEventListener('DOMContentLoaded', function() {
  let menuButton = document.getElementById('menuButton');
  let menu = document.querySelector('.menu__list');

  let openFormButton = document.getElementById('openFormButton');
  let closeFormButton = document.getElementById('closeContactForm');
  let form = document.querySelector('.popup_contact');

  openFormButton.addEventListener('click', function(event) {
    event.preventDefault();
    form.style.display = 'block';
  })

  
  closeFormButton.addEventListener('click', function() {
    form.style.display = 'none';
  })

  menuButton.addEventListener('click', function() {
    if (menu.style.opacity === '1') {
      menu.style.opacity = '0';
    } else {
      menu.style.opacity = '1';
    }
  });
});

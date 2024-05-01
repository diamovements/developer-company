function setApartmentCard() {
  let apartments = document.querySelectorAll('.apartment');
  let apartmentCard = document.querySelector('.popup_card');

  apartments.forEach(apartment => {
      apartment.addEventListener('click', function() {
          // Получаем данные из элемента .apartment
          let apartmentImage = apartment.querySelector('.apartment__image img').src;
          let apartmentTitle = apartment.querySelector('.apartment__complex').innerText;
          let apartmentRooms = apartment.querySelector('.apartment__rooms b').innerText;
          let apartmentArea = apartment.querySelector('.apartment__area b').innerText;
          let apartmentFloor = apartment.querySelector('.apartment__floor b').innerText;
          let apartmentPrice = apartment.querySelector('.apartment__price').innerText;

          // Устанавливаем данные в элемент .popup_card
          let apartmentCardImage = apartmentCard.querySelector('.apartmentCard__image img');
          apartmentCardImage.src = apartmentImage;
          let apartmentCardComplex = apartmentCard.querySelector('.apartmentCard__complex');
          apartmentCardComplex.innerHTML = `<b>${apartmentTitle}</b>`;
          let apartmentCardRooms = apartmentCard.querySelector('.apartmentCard__rooms b');
          apartmentCardRooms.innerText = apartmentRooms;
          let apartmentCardArea = apartmentCard.querySelector('.apartmentCard__area b');
          apartmentCardArea.innerText = apartmentArea;
          let apartmentCardFloor = apartmentCard.querySelector('.apartmentCard__floor b');
          apartmentCardFloor.innerText = apartmentFloor;
          let apartmentCardPrice = apartmentCard.querySelector('.apartmentCard__price');
          apartmentCardPrice.innerText = apartmentPrice;

          apartmentCard.style.display = 'block';

          let openFormFromCardButton = document.getElementById('openFormFromCardButton');
          openFormFromCardButton.addEventListener('click', function() {
            let contactPopup = document.querySelector('.popup_contact');
            let selectTitle = document.getElementById('title');
            selectTitle.value = apartmentTitle;
            contactPopup.style.display = 'block';
            apartmentCard.style.display = 'none';
          });
      });
  });

  let closeCardButton = document.getElementById('closeCardButton');
  closeCardButton.addEventListener('click', function() {
      apartmentCard.style.display = 'none';
  });
}

document.addEventListener('DOMContentLoaded', function() {
  document.getElementById("contactForm").addEventListener("submit", function(event) {
    event.preventDefault();
    const formData = {
      firstName: document.getElementById("firstName").value,
      lastName: document.getElementById("lastName").value,
      email: document.getElementById("email").value,
      title: document.getElementById("title").value
    };

    fetch('/apartment-request', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка при отправке данных');
        }
        return response.text();
    })
    .then(data => {
        console.log(data);
        alert(data);
    })
    .catch(error => {
        console.error('Ошибка:', error);
        alert('Ошибка при отправке данных');
    });
  });
})
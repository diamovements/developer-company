document.addEventListener('DOMContentLoaded', function() {
  let showAllApartmentsButton = document.getElementById('showApartmentsButton');
  let apartmentsContainer = document.querySelector('.apartments');
  let apartmentsContainerGradient = document.querySelector('.gradient');

  showAllApartmentsButton.addEventListener('click', function() {
    apartmentsContainer.style.maxHeight = 'fit-content';
    apartmentsContainer.style.overflowY = 'visible';
    showAllApartmentsButton.style.display = 'none';
    apartmentsContainerGradient.style.display = 'none';
  });

  let showFiltersButton = document.getElementById('showFiltersButton');
  let closeFiltersButton = document.getElementById('closeFiltersButton');
  let filters = document.querySelector('.filters');

  showFiltersButton.addEventListener('click', function() {
    filters.style.right = "0";
  })
  closeFiltersButton.addEventListener('click', function() {
    filters.style.right = "-100%";
  })

  let paramButton = document.getElementById('button_param');
  let buildingButton = document.getElementById('button_building');
  let paramFilters = document.querySelector('.param_filters');
  let buildingFilters = document.querySelector('.building_filters');

  paramButton.addEventListener('click', function() {
    if (!paramButton.classList.contains('active')) {
      paramButton.classList.add('active');
      paramButton.classList.remove('inactive');
      buildingButton.classList.remove('active');
      buildingButton.classList.add('inactive');
      buildingFilters.style.display = 'none';
      paramFilters.style.display = 'flex';
    }
  });

  buildingButton.addEventListener('click', function() {
    if (!buildingButton.classList.contains('active')) {
      buildingButton.classList.add('active');
      buildingButton.classList.remove('inactive');
      paramButton.classList.remove('active');
      paramButton.classList.add('inactive');
      paramFilters.style.display = 'none';
      buildingFilters.style.display = 'flex';
    }
  });

  let setParamFiltersButton = document.getElementById('setParamFiltersButton');
  let setBuildingFiltersButton = document.getElementById('setBuildingFiltersButton');
  let resetFiltersButton = document.getElementById('resetFiltersButton');
  

  setParamFiltersButton.addEventListener('click', function(event) {
    event.preventDefault();

    const minFloor = parseInt(document.getElementById('minFloor').value);
    const maxFloor = parseInt(document.getElementById('maxFloor').value);
    const minArea = parseInt(document.getElementById('minArea').value);
    const maxArea = parseInt(document.getElementById('maxArea').value);
    const minPrice = parseInt(document.getElementById('minPrice').value);
    const maxPrice = parseInt(document.getElementById('maxPrice').value);
    const minRooms = parseInt(document.getElementById('minRooms').value);
    const maxRooms = parseInt(document.getElementById('maxRooms').value);

    if (minFloor > maxFloor ||
        minArea > maxArea ||
        minPrice > maxPrice ||
        minRooms > maxRooms) {
        console.error('Ошибка: Минимальное значение больше максимального.');
        return;
    }

    const formData = {
        minFloor: minFloor,
        maxFloor: maxFloor,
        minArea: minArea,
        maxArea: maxArea,
        minPrice: minPrice,
        maxPrice: maxPrice,
        minRooms: minRooms,
        maxRooms: maxRooms
    };

    fetch('http://localhost:8080/apartments/filter', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Ошибка отправки запроса');
        }
        return response.json();
    })
    .then(data => {
        createApartments(data);
    })
    .catch(error => {
        console.error('Ошибка:', error);
    });
});

setBuildingFiltersButton.addEventListener('click', async function(event) {
    event.preventDefault();
    const checkboxes = buildingFilters.querySelectorAll('input');
    const selectedBuildings = [];

    checkboxes.forEach(checkbox => {
        if (checkbox.checked) {
            selectedBuildings.push(checkbox.id);
        }
    });

    const allApartments = [];

    try {
        const fetchPromises = selectedBuildings.map(buildingTitle => {
            return fetch(`http://localhost:8080/${buildingTitle}/apartments`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Ошибка при получении квартир');
                    }
                    return response.json();
                })
                .then(apartments => {
                    allApartments.push(...apartments);
                });
        });

        await Promise.all(fetchPromises);

        createApartments(allApartments);
    } catch (error) {
        console.error('Ошибка:', error);
    }
  });

  resetFiltersButton.addEventListener('click', function(event) {
    event.preventDefault();
    resetFilters();
  });
})
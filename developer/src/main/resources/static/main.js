"use strict";

let buildings;
let apartments;

document.addEventListener("DOMContentLoaded", async () => {
    try {
        let buildingsResponse = await fetch("http://localhost:8080/buildings");
        buildings = await buildingsResponse.json();
        createBuildings(buildings);
        createForm(buildings);

        let apartmentsResponse = await fetch("http://localhost:8080/apartments");
        apartments = await apartmentsResponse.json();
        createApartments(apartments);
        resetFilters(buildings, apartments);
        setApartmentCard();
    } catch (error) {
        console.error('Произошла ошибка:', error);
    }
});

function createBuildings(buildings) {
    const buildingsContainer = document.querySelector('.complexes');
    let buildingsHTML = '';

    for (const building of buildings) {
        let minutesDeclension = getMinutesDeclension(building.distance);

        buildingsHTML += `
            <div class="complex">
                <span class="complex__name">ЖК ${building.title}</span>
                <span class="complex__metro">${building.distance} ${minutesDeclension} до ${building.location}</span>
                <div class="complex__image">
                    <img src="${building.image}"/>
                </div>
            </div>
        `;
    }

    buildingsContainer.innerHTML = buildingsHTML;
}

function createForm(buildings) {
    const selectPart = document.getElementById('title');
    let selectHTML = '';

    for (const building of buildings) {
        selectHTML += `<option value="${building.title}">${building.title}</option>`;
    }

    selectPart.innerHTML = selectHTML;
}

function getMinutesDeclension(number) {
    if (number % 10 === 1 && number % 100 !== 11) {
        return "минута";
    } else if ([2, 3, 4].includes(number % 10) && ![12, 13, 14].includes(number % 100)) {
        return "минуты";
    } else {
        return "минут";
    }
}

function resetFilters() {
    let minFloor = apartments.reduce((min, apartment) => {
        return apartment.floor < min ? apartment.floor : min;
    }, Infinity);
    document.getElementById('minFloor').value = minFloor;

    let maxFloor = apartments.reduce((max, apartment) => {
        return apartment.floor > max ? apartment.floor : max;
    }, -Infinity);
    document.getElementById('maxFloor').value = maxFloor;

    let minArea = apartments.reduce((min, apartment) => {
        return apartment.area < min ? apartment.area : min;
    }, Infinity);
    document.getElementById('minArea').value = minArea;

    let maxArea = apartments.reduce((max, apartment) => {
        return apartment.area > max ? apartment.area : max;
    }, -Infinity);
    document.getElementById('maxArea').value = maxArea;

    let minPrice = apartments.reduce((min, apartment) => {
        return apartment.price < min ? apartment.price : min;
    }, Infinity);
    document.getElementById('minPrice').value = minPrice;

    let maxPrice = apartments.reduce((max, apartment) => {
        return apartment.price > max ? apartment.price : max;
    }, -Infinity);
    document.getElementById('maxPrice').value = maxPrice;

    let minRooms = apartments.reduce((min, apartment) => {
        return apartment.floor < min ? apartment.floor : min;
    }, Infinity);
    document.getElementById('minRooms').value = minRooms;

    let maxRooms = apartments.reduce((max, apartment) => {
        return apartment.floor > max ? apartment.floor : max;
    }, -Infinity);
    document.getElementById('maxRooms').value = maxRooms;

    const buildingFiltersContainer = document.querySelector('.building_filters');
    let filtersHTML = '';
    for (const building of buildings) {
        filtersHTML += `
        <label>
            <input type="checkbox" id="${building.title}" name=" ${building.title}" checked> ЖК ${building.title}
        </label>  `;
    }

    let endHtml = buildingFiltersContainer.innerHTML;
    buildingFiltersContainer.innerHTML = filtersHTML + endHtml;
}

function createApartments(apartments) {
    const apartmentsContainer = document.querySelector('.apartments__inner');
    let apartmentsHTML = '';
    if (apartments.length === 0) {
        apartmentsHTML += `
            <div class="no-apartments">
                <span>Ни одна квартира не подходит под запрос...</span>
            </div>
        `;
        document.getElementById('showApartmentsButton').style.display = 'none';
        document.querySelector('.gradient').style.display = 'none';
    } else {
        for (const apartment of apartments) {
            apartmentsHTML += `
            <div class="apartment">
                <div class="apartment__image">
                    <img src="${apartment.image}"/>
                </div>
                <div class="apartment__info">
                    <span class="apartment__complex"><b>${apartment.title}</b></span>
                    <span class="apartment__rooms">Комнаты: <b>${apartment.rooms}</b></span>
                    <span class="apartment__area">Плошадь: <b>${apartment.area} м<sup>2</sup></b></span>
                    <span class="apartment__floor">Этаж: <b>${apartment.floor}</b></span>
                    <span class="apartment__price">${apartment.price} ₽</span>
                </div>
            </div>
            `;
        }
    }

    apartmentsContainer.innerHTML = apartmentsHTML;
}

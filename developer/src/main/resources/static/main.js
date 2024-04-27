"use strict";

document.addEventListener("DOMContentLoaded", async () => {
    let buildingsResponse = await fetch("http://localhost:8080/buildings");
    let buildings = await buildingsResponse.json();
    createBuildings(buildings);

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
    
    function getMinutesDeclension(number) {
        if (number % 10 === 1 && number % 100 !== 11) {
            return "минута";
        } else if ([2, 3, 4].includes(number % 10) && ![12, 13, 14].includes(number % 100)) {
            return "минуты";
        } else {
            return "минут";
        }
    }

    let apartmentsResponse = await fetch("http://localhost:8080/apartments");
    let apartments = await apartmentsResponse.json();
    createApartments(apartments);

    function createApartments(apartments) {
        const apartmentsContainer = document.querySelector('.apartments__inner');
        let apartmentsHTML = '';
    
        for (const apartment of apartments) {
            apartmentsHTML += `
            <div class="apartment">
                <div class="apartment__image">
                    <img src="${apartment.image}"/>
                </div>
                <div class="apartment__info">
                    <span class="apartment__complex">ЖК ${apartment.building}</span>
                    <span class="apartment__rooms">Количество комнат: ${apartment.rooms}</span>
                    <span class="apartment__area">Плошадь: ${apartment.area} м<sup>2</sup></span>
                    <span class="apartment__floor">Этаж: ${apartment.floor}</span>
                    <span class="apartment__number">Номер квартиры: ${apartment.number}</span>
                    <span class="apartment__price">${apartment.price} ₽</span>
                </div>
            </div>
            `;
        }
    
        apartmentsContainer.innerHTML = apartmentsHTML;
    }
});

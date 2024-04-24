"use strict";

document.addEventListener("DOMContentLoaded", async () => {
    let buildingsResponse = await fetch("http://localhost:8080/buildings");
    let buildings = await buildingsResponse.json();
    createBuildings(buildings);
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

function getMinutesDeclension(number) {
    if (number % 10 === 1 && number % 100 !== 11) {
        return "минута";
    } else if ([2, 3, 4].includes(number % 10) && ![12, 13, 14].includes(number % 100)) {
        return "минуты";
    } else {
        return "минут";
    }
}

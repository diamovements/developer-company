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
        buildingsHTML += `
            <div class="complex">
                <span class="complex__name">ЖК ${building.title}</span>
                <span class="complex__metro">${building.distance} минут до ${building.location}</span>
                <div class="complex__image">
                    <img src="${building.image}"/>
                </div>
            </div>
        `;
    }

    buildingsContainer.innerHTML = buildingsHTML;
}

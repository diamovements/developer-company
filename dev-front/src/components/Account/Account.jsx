import React, { useEffect, useState } from 'react';
import '../../styles/global.css';
import '../../styles/main.css';


const Account = () => {
    const [user, setUser] = useState({ name: '', favoriteApartments: [] });

    useEffect(() => {
        const fetchClientData = async () => {
            try {
                const response = await fetch('/getperson');
                if (!response.ok) {
                    throw new Error('Ошибка при получении данных о клиенте');
                }
                const data = await response.json();
                setUser(data);
            } catch (error) {
                console.error('Ошибка:', error);
            }
        };

        fetchClientData();
    }, []);

    return (
        <div className="container">
            <div className="greeting">
                <h2>Добрый день, <span id="name">{user.name}</span>!</h2>
            </div>
            <div className="fav">
                <h3>Избранное</h3>
                <ul id="apartmentsList">
                    {user.favoriteApartments.map(apartment => (
                        <li key={apartment.id} className="apartment">
                            <div>
                                <img src={apartment.image} alt="Упс, пропала картинка" />
                            </div>
                            <div className="apartment-info">
                                <p><strong>Этаж:</strong> {apartment.floor}</p>
                                <p><strong>Площадь:</strong> {apartment.area} м²</p>
                                <p><strong>Цена:</strong> ${apartment.price}</p>
                                <p><strong>Номер:</strong> {apartment.number}</p>
                                <p><strong>Комнаты:</strong> {apartment.rooms}</p>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default Account;
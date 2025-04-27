import React, { useState } from 'react';
import '../../styles/global.css';
import '../../styles/main.css';
import ContactForm from "../ContactForm/ContactForm";

const ApartmentCard = ({ apartment, onClose }) => {
    const [isFormOpen, setIsFormOpen] = useState(false);

    const openForm = () => {
        setIsFormOpen(true);
        console.log("ApartmentCard открыт для:", apartment);

    };

    return (
        <div className="popup popup_card">
            <div className="apartmentCard">
                <div className="apartmentCard__image">
                    <img src={apartment.image} alt={apartment.title} />
                </div>
                <div className="apartmentCard__info">
                    <span className="apartmentCard__complex"><b>{apartment.title}</b></span>
                    <span className="apartmentCard__rooms">Комнаты: <b>{apartment.rooms}</b></span>
                    <span className="apartmentCard__area">Площадь: <b>{apartment.area}</b></span>
                    <span className="apartmentCard__floor">Этаж: <b>{apartment.floor}</b></span>
                    <span className="apartmentCard__price">{apartment.price}</span>
                    <div className="favorite">
                        <i className="fa-regular fa-heart" id="add-favorite-button"></i>
                        <p className="favorite-text">В избранное</p>
                    </div>
                    <button type="button" className="apartmentCard__button" onClick={openForm}>Отправить заявку</button>
                    <button type="button" className="apartmentCard__button" onClick={onClose}>Закрыть</button>
                </div>
            </div>
            {isFormOpen && <ContactForm onClose={() => setIsFormOpen(false)} />}
        </div>
    );
};

export default ApartmentCard;
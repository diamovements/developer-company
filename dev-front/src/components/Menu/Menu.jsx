import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../../styles/global.css';
import '../../styles/main.css';
import ContactForm from '../ContactForm/ContactForm';

const Menu = () => {
    const [isOpen, setIsOpen] = useState(false);
    const [isFormVisible, setIsFormVisible] = useState(false);

    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    const openForm = () => {
        setIsFormVisible(true);
    };

    const closeForm = () => {
        setIsFormVisible(false);
    };

    return (
        <nav className="menu">
            <button className="menu__button button_menu" onClick={toggleMenu}>
                МЕНЮ
                <div className="button_menu__close"></div>
            </button>
            <ul className={`menu__list ${isOpen ? 'open' : ''}`}>
                <li>
                    <a className="menu__href" href="#complexes">Жилые комплексы</a>
                </li>
                <li>
                    <a className="menu__href" href="#apartments">Планировки</a>
                </li>
                <li>
                    <a className="menu__href" href="#" onClick={openForm}>Оставить заявку</a>
                </li>
                <li>
                    <Link className="menu__href" to="/account">Личный кабинет</Link>
                </li>
                <li>
                    <Link className="menu__href" to="/login">Войти</Link>
                </li>
            </ul>

            {isFormVisible && <ContactForm onClose={closeForm} />}
        </nav>
    );
};

export default Menu;
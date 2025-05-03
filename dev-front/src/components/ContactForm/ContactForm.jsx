import React, { useState, useEffect } from 'react';
import '../../styles/global.css';
import '../../styles/main.css';

const ContactForm = ({ onClose }) => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        title: ''
    });

    const [complexes, setComplexes] = useState([]);

    useEffect(() => {
        const fetchComplexes = async () => {
            try {
                const response = await fetch('https://developer-company-7.onrender.com/buildings');
                const data = await response.json();

                if (Array.isArray(data)) {
                    setComplexes(data);
                } else {
                    console.error("Ошибка: данные не являются массивом", data);
                }
            } catch (error) {
                console.error("Ошибка при загрузке данных о ЖК:", error);
            }
        };

        fetchComplexes();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('https://developer-company-7.onrender.com/apartment-request', {
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
    };

    return (
        <div className="popup popup_contact">
            <form id="contactForm" onSubmit={handleSubmit}>
                <h1>СВЯЖИТЕСЬ С НАМИ</h1>
                <label htmlFor="firstName">Имя:</label>
                <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                    autoComplete="given-name"
                />
                <label htmlFor="lastName">Фамилия:</label>
                <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
                    onChange={handleChange}
                    required
                    autoComplete="family-name"
                />
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                    autoComplete="email"
                />
                <label htmlFor="title">Выбранный ЖК:</label>
                <select
                    id="title"
                    name="title"
                    value={formData.title}
                    onChange={handleChange}
                    required
                >
                    {complexes.length > 0 ? (
                        complexes.map((complex) => (
                            <option key={complex.building_id} value={complex.building_id}>
                                {complex.title}
                            </option>
                        ))
                    ) : (
                        <option disabled>Загружаются данные</option>
                    )}
                </select>
                <button type="submit" className="button_form" id="sendContactForm">
                    Отправить
                </button>
                <button
                    type="button"
                    className="button_form"
                    id="closeContactForm"
                    onClick={onClose}
                >
                    Закрыть
                </button>
            </form>
        </div>
    );
};

export default ContactForm;

import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header/Header';
import Menu from '../components/Menu/Menu';
import Footer from '../components/Footer/Footer';
import '../styles/global.css';
import '../styles/main.css';
import '../styles/login.css';

const Account = () => {
    const [userData, setUserData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const fetchUserData = async () => {
        try {
            const response = await fetch('http://localhost:8080/getperson', {
                method: 'GET',
                credentials: 'include',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (response.status === 401) {
                navigate('/login');
                return;
            }

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            setUserData(data);
        } catch (err) {
            console.error('Fetch error:', err);
            setError('Ошибка загрузки данных. Пожалуйста, войдите снова.');
            setTimeout(() => navigate('/login'), 2000);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchUserData();
    }, [navigate]);

    if (loading) {
        return (
            <div className="account-page">
                <Header />
                <Menu />
                <div className="loading">Загрузка...</div>
                <Footer />
            </div>
        );
    }

    if (error) {
        return (
            <div className="account-page">
                <Header />
                <Menu />
                <div className="error">
                    <p>{error}</p>
                </div>
                <Footer />
            </div>
        );
    }

    return (
        <div className="account-page">
            <Header />
            <Menu />
            <main className="account-container">
                <h1>Личный кабинет</h1>

                {userData && (
                    <section className="profile-section">
                        <h2>Профиль</h2>
                        <div className="profile-info">
                            <p><strong>Имя:</strong> {userData.name || 'Не указано'}</p>
                            <p><strong>Фамилия:</strong> {userData.lastName || 'Не указано'}</p>
                            <p><strong>Email:</strong> {userData.email || 'Не указан'}</p>
                        </div>
                    </section>
                )}
            </main>
            <Footer />
        </div>
    );
};

export default Account;
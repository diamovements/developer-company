import React, { useState, useEffect } from 'react';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
import Menu from '../components/Menu/Menu';
import ApartmentFilter from '../components/ApartmentFilter/ApartmentFilter';
import ApartmentCard from '../components/ApartmentCard/ApartmentCard';
import ContactForm from '../components/ContactForm/ContactForm';
import '../styles/global.css';
import '../styles/main.css';

const Home = () => {
    const [apartments, setApartments] = useState([]);
    const [selectedApartment, setSelectedApartment] = useState(null);
    const [isFormOpen, setIsFormOpen] = useState(false);
    const [complexes, setComplexes] = useState([]);
    const [isFiltersVisible, setIsFiltersVisible] = useState(false);
    const [selectedBuilding, setSelectedBuilding] = useState("");
    const [showAllApartments, setShowAllApartments] = useState(false);

    const toggleFilters = () => {
        setIsFiltersVisible(!isFiltersVisible);
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch("http://localhost:8080/apartments");
                const data = await response.json();
                console.log("Загруженные квартиры:", data);

                if (Array.isArray(data)) {
                    setApartments(data);
                } else {
                    console.error("Ожидался массив, но получено:", data);
                    setApartments([]);
                }

                const complexesResponse = await fetch("http://localhost:8080/buildings");
                const complexesData = await complexesResponse.json();
                console.log("Загруженные ЖК:", complexesData);

                if (Array.isArray(complexesData)) {
                    setComplexes(complexesData);
                } else {
                    console.error("Ожидался массив ЖК, но получено:", complexesData);
                }

            } catch (error) {
                console.error("Ошибка при загрузке данных:", error);
                setApartments([]);
            }
        };

        fetchData();
    }, []);

    const handleFilter = async (filters) => {
        try {
            const buildingTitle = encodeURIComponent(filters.building);

            let response;

            if (buildingTitle) {
                setSelectedBuilding(buildingTitle);
                response = await fetch(`http://localhost:8080/buildings/${buildingTitle}/apartments`);
            } else {
                response = await fetch("http://localhost:8080/apartments/filter", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(filters),
                });
            }

            console.log("Ответ от сервера:", response);
            const textResponse = await response.text();

            if (!response.ok) {
                throw new Error(`Ошибка: ${response.statusText}`);
            }

            const apartmentsData = textResponse ? JSON.parse(textResponse) : [];

            console.log("Данные квартир:", apartmentsData);

            if (apartmentsData && Array.isArray(apartmentsData)) {
                setApartments(apartmentsData);
            } else {
                console.error("Ответ не содержит ожидаемые данные", apartmentsData);
                setApartments([]);
            }

        } catch (error) {
            console.error("Ошибка при фильтрации квартир:", error);
            setApartments([]);
        }
    };

    const handleResetFilters = async () => {
        try {
            const response = await fetch("http://localhost:8080/apartments");
            const data = await response.json();

            if (Array.isArray(data)) {
                setApartments(data);
            } else {
                console.error("Ожидался массив, но получено:", data);
                setApartments([]);
            }
        } catch (error) {
            console.error("Ошибка при сбросе фильтров:", error);
            setApartments([]);
        }
    };

    const handleApartmentClick = (apartment) => {
        setSelectedApartment(apartment);
    };

    useEffect(() => {}, [selectedApartment]);

    const handleCloseApartmentCard = () => {
        setSelectedApartment(null);
    };

    const handleOpenForm = () => {
        setIsFormOpen(true);
    };

    const handleCloseForm = () => {
        setIsFormOpen(false);
    };

    const handleShowAllApartments = () => {
        setShowAllApartments(true);
    };

    return (
        <div className="home">
            <Header />
            <Menu />
            <main>
                <div className="company-info" id="company-info">
                    <img src="/images/company-info.jpg" alt="Один из домов в ЖК" className="company-info__image" />
                    <div className="company-info__description">
                        <p>
                            Откройте дверь в мир комфорта и современности с <span className="accent">MOSCOW BUILD</span>!
                        </p>
                        <div className="line"></div>
                        <div className="company-info__features">
                            <p className="feature"><span className="feature_bold accent">От 300 т.р.</span> за квадратный метр</p>
                            <p className="feature"><span className="feature_bold accent">Более 30-ти</span> вариантов планировок</p>
                            <p className="feature"><span className="feature_bold accent">от 22 кв.м.</span> площадь квартир</p>
                        </div>
                    </div>
                </div>

                <div className="apartment-complex" id="complexes">
                    <h1 className="apartment-complex__title">НАШИ ЖИЛЫЕ КОМПЛЕКСЫ</h1>
                    <div className="complexes">
                        {complexes.length > 0 ? (
                            complexes.map((complex) => (
                                <div key={complex.building_id} className="complex">
                                    <div className="complex__image">
                                        <img src={`/${complex.image}`} alt={complex.title || "ЖК"} />
                                    </div>
                                    <span className="complex__name">ЖК {complex.title}</span>
                                    <span className="complex__metro">
                                        {complex.distance} мин до {complex.location}
                                    </span>
                                </div>
                            ))
                        ) : (
                            <p>Нет доступных жилых комплексов</p>
                        )}
                    </div>
                </div>

                <div className="apartments" id="apartments">
                    <h1 className="apartments__title">ВЫБЕРИТЕ СВОЮ КВАРТИРУ</h1>
                    <button className="button_show_filters" type="button" onClick={toggleFilters}>
                        ФИЛЬТРЫ
                    </button>

                    <ApartmentFilter
                        onFilter={handleFilter}
                        onReset={handleResetFilters}
                        isFiltersVisible={isFiltersVisible}
                        toggleFilters={toggleFilters}
                        complexes={complexes}
                    />
                    <div className="apartments__inner"
                         style={{
                             maxHeight: showAllApartments ? "fit-content" : "500px",
                             overflowY: showAllApartments ? "visible" : "hidden",
                         }}>
                        {Array.isArray(apartments) && apartments.length > 0 ? (
                            apartments.map(apartment => (
                                <div key={apartment.id} className="apartment" onClick={() => handleApartmentClick(apartment)}>
                                    <div className="apartment__image">
                                        <img src={apartment.image || "/placeholder.jpg"} alt={apartment.title || "Квартира"} />
                                    </div>
                                    <div className="apartment__info">
                                        <span className="apartment__complex"><b>{decodeURIComponent(selectedBuilding) || apartment.title || "Неизвестный ЖК"}</b></span>
                                        <span className="apartment__rooms">Комнаты: <b>{apartment.rooms || "—"}</b></span>
                                        <span className="apartment__area">Площадь: <b>{apartment.area ? `${apartment.area} м²` : "—"}</b></span>
                                        <span className="apartment__floor">Этаж: <b>{apartment.floor || "—"}</b></span>
                                        <span className="apartment__price">{apartment.price ? `${apartment.price} ₽` : "Цена не указана"}</span>
                                    </div>
                                </div>
                            ))
                        ) : (
                            <p>Нет доступных квартир</p>
                        )}
                    </div>
                    {!showAllApartments && <div className="gradient"></div>}
                    {!showAllApartments && (
                        <button className="button_show" type="button" onClick={handleShowAllApartments}>
                            ПОКАЗАТЬ ВСЕ
                        </button>
                    )}
                </div>

                {selectedApartment && (
                    <ApartmentCard apartment={selectedApartment} onClose={handleCloseApartmentCard} />
                )}

                {isFormOpen && <ContactForm onClose={handleCloseForm} />}
            </main>
            <Footer />
        </div>
    );
};

export default Home;

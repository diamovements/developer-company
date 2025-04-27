import React, { useState, useEffect } from 'react';
import '../../styles/global.css';
import '../../styles/main.css';

const ApartmentFilter = ({ onFilter, onReset, isFiltersVisible, toggleFilters, complexes }) => {
    const [filters, setFilters] = useState({
        minFloor: '',
        maxFloor: '',
        minArea: '',
        maxArea: '',
        minPrice: '',
        maxPrice: '',
        minRooms: '',
        maxRooms: '',
        building: ''
    });

    const [activeTab, setActiveTab] = useState('param');

    useEffect(() => {
        if (complexes && complexes.length > 0) {
            const buildingFilters = complexes.map((complex) => ({
                title: complex.title,
                isChecked: false
            }));
            setFilters((prevFilters) => ({
                ...prevFilters,
                buildings: buildingFilters
            }));
        }
    }, [complexes]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'building') {
            setFilters({ ...filters, building: value });
        } else {
            setFilters({ ...filters, [name]: value });
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const filterData = {
            ...filters,
            buildings: filters.building ? [filters.building] : []
        };

        onFilter(filterData);
    };

    const handleReset = () => {
        setFilters({
            minFloor: '',
            maxFloor: '',
            minArea: '',
            maxArea: '',
            minPrice: '',
            maxPrice: '',
            minRooms: '',
            maxRooms: '',
            building: ''
        });

        onReset();
    };

    const handleTabClick = (tab) => {
        setActiveTab(tab);
    };


    return (
        <div className="filters" style={{ right: isFiltersVisible ? '0' : '-100vw' }}>
            <span className="filters__title">
                ФИЛЬТРЫ
                <button className="button_close_filters" type="button" onClick={toggleFilters}>
                    {isFiltersVisible ? '➡' : '⬅'}
                </button>
            </span>
            {isFiltersVisible && (
                <>
                    <div className="filters__buttons">
                        <button
                            type="button"
                            className={`filter__button ${activeTab === 'param' ? 'active' : 'inactive'}`}
                            id="button_param"
                            onClick={() => handleTabClick('param')}
                        >
                            По параметрам
                        </button>
                        <button
                            type="button"
                            className={`filter__button ${activeTab === 'building' ? 'active' : 'inactive'}`}
                            id="button_building"
                            onClick={() => handleTabClick('building')}
                        >
                            По ЖК
                        </button>
                    </div>

                    {activeTab === 'param' && (
                        <form className="param_filters" onSubmit={handleSubmit}>
                            <div className="filter">
                                <span className="filter__name">ЭТАЖ:</span>
                                <label htmlFor="minFloor">от</label>
                                <input
                                    type="number"
                                    id="minFloor"
                                    name="minFloor"
                                    value={filters.minFloor}
                                    onChange={handleChange}
                                    required
                                />
                                <label htmlFor="maxFloor">до</label>
                                <input
                                    type="number"
                                    id="maxFloor"
                                    name="maxFloor"
                                    value={filters.maxFloor}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="filter">
                                <span className="filter__name">ПЛОЩАДЬ:</span>
                                <label htmlFor="minArea">от</label>
                                <input
                                    type="number"
                                    id="minArea"
                                    name="minArea"
                                    value={filters.minArea}
                                    onChange={handleChange}
                                    required
                                />
                                <label htmlFor="maxArea">до</label>
                                <input
                                    type="number"
                                    id="maxArea"
                                    name="maxArea"
                                    value={filters.maxArea}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="filter">
                                <span className="filter__name">ЦЕНА:</span>
                                <label htmlFor="minPrice">от</label>
                                <input
                                    type="number"
                                    id="minPrice"
                                    name="minPrice"
                                    value={filters.minPrice}
                                    onChange={handleChange}
                                    required
                                />
                                <label htmlFor="maxPrice">до</label>
                                <input
                                    type="number"
                                    id="maxPrice"
                                    name="maxPrice"
                                    value={filters.maxPrice}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <div className="filter">
                                <span className="filter__name">КОМНАТЫ:</span>
                                <label htmlFor="minRooms">от</label>
                                <input
                                    type="number"
                                    id="minRooms"
                                    name="minRooms"
                                    value={filters.minRooms}
                                    onChange={handleChange}
                                    required
                                />
                                <label htmlFor="maxRooms">до</label>
                                <input
                                    type="number"
                                    id="maxRooms"
                                    name="maxRooms"
                                    value={filters.maxRooms}
                                    onChange={handleChange}
                                    required
                                />
                            </div>
                            <button className="filter__button" type="submit">
                                ПРИМЕНИТЬ
                            </button>
                        </form>
                    )}

                    {activeTab === 'building' && (
                        <form className="building_filters" onSubmit={handleSubmit}>
                            <h3>Выберите жилой комплекс</h3>
                            {filters.buildings && filters.buildings.length > 0 ? (
                                filters.buildings.map((building) => (
                                    <label key={building.title}>
                                        <input
                                            type="radio"
                                            name="building"
                                            value={building.title}
                                            checked={filters.building === building.title}
                                            onChange={handleChange}
                                        />
                                        {building.title}
                                    </label>
                                ))
                            ) : (
                                <p>Нет доступных жилых комплексов</p>
                            )}
                            <button className="filter__button" type="submit">
                                ПРИМЕНИТЬ
                            </button>
                        </form>
                    )}
                    <button className="filter__button" onClick={handleReset}>
                        СБРОСИТЬ
                    </button>
                </>
            )}
        </div>
    );
};

export default ApartmentFilter;

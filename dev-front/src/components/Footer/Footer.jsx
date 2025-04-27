import React from 'react';
import '../../styles/global.css';
import '../../styles/main.css';


const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer__logo">
                <img src="../../../public/images/icon.png" alt="Moscow Build" />
            </div>
            <div className="footer__links">
                <a href="#company-info">О нас</a>
                <a href="#complexes">Проекты</a>
                <a href="#apartments">Квартиры</a>
            </div>
            <div className="footer__social">
                <a href="mailto:info@moscowbuild.com">info@moscowbuild.com</a>
                <a href="#"><i className="fab fa-facebook-f"></i></a>
                <a href="#"><i className="fab fa-twitter"></i></a>
                <a href="#"><i className="fab fa-instagram"></i></a>
            </div>
        </footer>
    );
};

export default Footer;
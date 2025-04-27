import React from 'react';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
import Menu from '../components/Menu/Menu';
import RegisterForm from '../components/Register/Register';
import '../styles/global.css';
import '../styles/main.css';

const Register = () => {
    return (
        <div className="register">
            <Header />
            <Menu />
            <main>
                <RegisterForm />
            </main>
            <Footer />
        </div>
    );
};

export default Register;
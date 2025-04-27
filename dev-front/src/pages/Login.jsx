import React from 'react';
import Header from '../components/Header/Header';
import Footer from '../components/Footer/Footer';
import Menu from '../components/Menu/Menu';
import LoginForm from '../components/Login/Login';
import '../styles/global.css';
import '../styles/main.css';

const Login = () => {
    return (
        <div className="login">
            <Header />
            <Menu />
            <main>
                <LoginForm />
            </main>
            <Footer />
        </div>
    );
};

export default Login;
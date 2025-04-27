import React, { useState } from 'react';
import '../../styles/global.css';
import '../../styles/main.css';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        const result = await response.json();
        if (response.ok) {
            localStorage.setItem('token', result.token);
            window.location.href = '/index.html';
        } else {
            alert(result.message || 'Ошибка при входе');
        }
    };

    return (
        <div className="container">
            <h2>Login</h2>
            <form id="loginForm" onSubmit={handleSubmit}>
                <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
                <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
                <button type="submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
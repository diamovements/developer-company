document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.querySelector("#loginForm");
    const registerForm = document.querySelector("#registerForm");

    if (loginForm) {
        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const formData = new FormData(loginForm);
            const data = Object.fromEntries(formData);

            const response = await fetch(`/api/auth/login`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            const result = await response.json();
            const messageDiv = document.querySelector("#loginMessage");

            if (response.ok) {
                messageDiv.textContent = "Login successful!";
                messageDiv.style.color = "green";
                localStorage.setItem('token', result.token);
                // Redirect to a protected page if needed
                window.location.href = '/protected.html';
            } else {
                messageDiv.textContent = result.message || "An error occurred!";
            }
        });
    }

    if (registerForm) {
        registerForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            const formData = new FormData(registerForm);
            const data = Object.fromEntries(formData);

            const response = await fetch(`/api/auth/register`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            const messageDiv = document.querySelector("#registerMessage");

            if (response.ok) {
                messageDiv.textContent = "Registration successful!";
                messageDiv.style.color = "green";
            } else {
                messageDiv.textContent = "Registration failed!";
            }
        });
    }
});

const BASE_URL_USER = "http://localhost:8081/api/users";

/**
 * ✅ Function to Handle User Signup
 */
function addUser() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const mobileNumber = document.getElementById("mobileNumber").value;
    const password = document.getElementById("password").value;
    const validatePassword = document.getElementById("validatePassword").value;

    if (password !== validatePassword) {
        alert("Passwords do not match!");
        return false;
    }

    fetch(`${BASE_URL_USER}/add`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, mobileNumber, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("User registration failed");
        }
        return response.json();
    })
    .then(data => {
        alert("User registered successfully!");
        window.location.href = "/login";
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred: " + error.message);
    });

    return false; // Prevent default form submission
}

/**
 * ✅ Function to Handle User Login
 */
function userLogin() {
    const username = document.getElementById("user").value;
    const password = document.getElementById("pass").value;

    fetch(`${BASE_URL_USER}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Invalid username or password");
        }
        return response.json();
    })
    .then(data => {
        alert("User login successful!");
        localStorage.setItem("userToken", data.token);
        window.location.href = "/user-dashboard";
    })
    .catch(error => {
        console.error("Login error:", error);
        alert(error.message);
    });

    return false; // Prevent default form submission
}

/**
 * ✅ Function to Reset User Password
 */
function resetUserPassword() {
    const username = prompt("Enter your username:");
    if (!username) return false;

    fetch(`${BASE_URL_USER}/reset-password`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Error resetting password");
        }
        return response.text();
    })
    .then(message => {
        alert(message);
    })
    .catch(error => {
        console.error("Reset password error:", error);
        alert(error.message);
    });

    return false; // Prevent link navigation
}

/**
 * ✅ Toggle Signup Form
 */
function toggleSignup() {
    let loginForm = document.querySelector(".login-form");
    let signupForm = document.getElementById("signup-form");
    if (signupForm.style.display === "none") {
        loginForm.style.display = "none";
        signupForm.style.display = "block";
    } else {
        loginForm.style.display = "block";
        signupForm.style.display = "none";
    }
}

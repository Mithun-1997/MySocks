const BASE_URL_ADMIN = "http://localhost:8081/api/admin";

/**
 * ✅ Function to Handle Admin Login
 */
function adminLogin() {
    const username = document.getElementById("admin-user").value;
    const password = document.getElementById("admin-pass").value;

    fetch("/login", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Invalid admin username or password");
        }
        return response.text();
    })
    .then(() => {
        alert("Admin login successful!");
        window.location.href = "/admin-dashboard";
    })
    .catch(error => {
        console.error("Admin login error:", error);
        alert(error.message);
    });

    return false; // Prevent default form submission
}

/**
 * ✅ Function to Reset Admin Password
 */
function resetAdminPassword() {
    const username = prompt("Enter your admin username:");
    if (!username) return false;

    fetch(`${BASE_URL_ADMIN}/reset-password`, {
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

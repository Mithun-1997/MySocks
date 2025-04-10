function sendTemporaryPassword() {
    let userInput = document.getElementById("userInput").value;

    if (!userInput) {
        alert("Please enter your email or mobile number.");
        return false;
    }

    // Generate a random 10-digit temporary password
    let tempPassword = generateTempPassword();

    // Send request to backend
    fetch("/forgot-password", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ userInput: userInput, tempPassword: tempPassword }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("A temporary password has been sent to your email or mobile number.");
        } else {
            alert("User not found. Please check your details and try again.");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
    });

    return false; // Prevent form submission
}

// Function to generate a random 10-digit temporary password
function generateTempPassword() {
    let tempPass = "";
    for (let i = 0; i < 10; i++) {
        tempPass += Math.floor(Math.random() * 10); // Random digit (0-9)
    }
    return tempPass;
}

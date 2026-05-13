const registerForm = document.getElementById("registerForm");
const message = document.getElementById("message");

registerForm.addEventListener("submit", function(e) {
    e.preventDefault();

    const family = {
        familyName: document.getElementById("familyName").value.trim(),
        email: document.getElementById("email").value.trim(),
        password: document.getElementById("password").value,
        phoneNumber: document.getElementById("phoneNumber").value.trim(),
        city: document.getElementById("city").value.trim(),
        address: document.getElementById("address").value.trim(),
        bio: document.getElementById("bio").value.trim(),
        numberOfMembers: parseInt(document.getElementById("numberOfMembers").value)
    };

    fetch("/api/auth/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(family)
    })
    .then(response => {
        if (response.ok) {
            showMessage("Account created successfully. Redirecting to login...", "success");

            setTimeout(() => {
                window.location.href = "/login.html";
            }, 1000);
        } else {
            showMessage("Could not register. Email may already exist.", "error");
        }
    })
    .catch(error => {
        console.log(error);
        showMessage("Something went wrong. Please try again.", "error");
    });
});

function showMessage(text, type) {
    message.style.display = "block";
    message.className = "message " + type;
    message.textContent = text;
}
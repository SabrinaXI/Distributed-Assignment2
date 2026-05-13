const helpRequestForm = document.getElementById("helpRequestForm");
const message = document.getElementById("message");
const logoutBtn = document.getElementById("logoutBtn");

let familyId = null;

// Get currently logged-in family
fetch("/api/family/me")
    .then(response => response.json())
    .then(family => {
        familyId = family.familyId;
    })
    .catch(error => {
        console.log(error);
        message.style.display = "block";
        message.className = "message error";
        message.textContent = "Could not load logged-in family.";
    });

helpRequestForm.addEventListener("submit", function(e) {
    e.preventDefault();

    if (familyId === null) {
        message.style.display = "block";
        message.className = "message error";
        message.textContent = "Family information is not loaded yet.";
        return;
    }

    const helpRequest = {
        title: document.getElementById("title").value.trim(),
        description: document.getElementById("description").value.trim(),
        category: document.getElementById("category").value,
        city: document.getElementById("city").value.trim(),
        neededDate: document.getElementById("neededDate").value
    };

    fetch(`/api/help-requests/family/${familyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(helpRequest)
    })
    .then(response => {
        if (response.ok) {
            message.style.display = "block";
            message.className = "message success";
            message.textContent = "Help request submitted successfully.";

            setTimeout(() => {
                window.location.href = "/dashboard.html";
            }, 1000);
        } else {
            message.style.display = "block";
            message.className = "message error";
            message.textContent = "Could not submit help request.";
        }
    })
    .catch(error => {
        console.log(error);
        message.style.display = "block";
        message.className = "message error";
        message.textContent = "Something went wrong. Please try again.";
    });
});

logoutBtn.addEventListener("click", () => {
    fetch("/logout", {
        method: "POST"
    })
    .then(response => {
        window.location.href = "/login.html";
    })
    .catch(error => {
        console.log(error);
    });
});
const helpOfferForm = document.getElementById("helpOfferForm");
const message = document.getElementById("message");
const logoutBtn = document.getElementById("logoutBtn");

let familyId = null;

fetch("/api/family/me")
    .then(response => response.json())
    .then(family => {
        familyId = family.familyId;
    })
    .catch(error => {
        console.log(error);
        showMessage("Could not load logged-in family.", "error");
    });

helpOfferForm.addEventListener("submit", function(e) {
    e.preventDefault();

    if (familyId === null) {
        showMessage("Family information is not loaded yet.", "error");
        return;
    }

    const helpOffer = {
        title: document.getElementById("title").value.trim(),
        description: document.getElementById("description").value.trim(),
        category: document.getElementById("category").value,
        city: document.getElementById("city").value.trim()
    };

    fetch(`/api/help-offers/family/${familyId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(helpOffer)
    })
    .then(response => {
        if (response.ok) {
            showMessage("Help offer posted successfully.", "success");

            setTimeout(() => {
                window.location.href = "/dashboard.html";
            }, 1000);
        } else {
            showMessage("Could not post help offer.", "error");
        }
    })
    .catch(error => {
        console.log(error);
        showMessage("Something went wrong. Please try again.", "error");
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

function showMessage(text, type) {
    message.style.display = "block";
    message.className = "message " + type;
    message.textContent = text;
}
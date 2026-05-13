const logoutBtn = document.getElementById("logoutBtn");
const helpRequestsList = document.getElementById("helpRequestsList");
const helpOffersList = document.getElementById("helpOffersList");

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

loadPendingHelpRequests();
loadHelpOffers();

function loadPendingHelpRequests() {
    fetch("/api/help-requests/pending")
        .then(response => response.json())
        .then(helpRequests => {

            helpRequestsList.innerHTML = "";

            if (helpRequests.length === 0) {
                helpRequestsList.innerHTML = `<p class="empty-message">No pending help requests right now.</p>`;
                return;
            }

            helpRequests.forEach(request => {

                const card = document.createElement("div");
                card.classList.add("request-card");

                card.innerHTML = `
                    <h3>${request.title}</h3>
                    <p><strong>City:</strong> ${request.city}</p>
                    <p><strong>Needed Date:</strong> ${formatDate(request.neededDate)}</p>
                    <button class="view-btn" onclick="viewRequest(${request.requestId})">View</button>
                `;

                helpRequestsList.appendChild(card);
            });
        })
        .catch(error => {
            console.log(error);
            helpRequestsList.innerHTML = `<p class="empty-message">Could not load help requests.</p>`;
        });
}

function loadHelpOffers() {
    fetch("/api/help-offers")
        .then(response => response.json())
        .then(helpOffers => {

            helpOffersList.innerHTML = "";

            if (helpOffers.length === 0) {
                helpOffersList.innerHTML = `<p class="empty-message">No help offers posted yet.</p>`;
                return;
            }

            helpOffers.forEach(offer => {

                const card = document.createElement("div");
                card.classList.add("request-card");

                card.innerHTML = `
                    <h3>${offer.title}</h3>
                    <p><strong>Category:</strong> ${offer.category}</p>
                    <p><strong>City:</strong> ${offer.city}</p>
                `;

                helpOffersList.appendChild(card);
            });
        })
        .catch(error => {
            console.log(error);
            helpOffersList.innerHTML = `<p class="empty-message">Could not load help offers.</p>`;
        });
}

function viewRequest(requestId) {
    window.location.href = `/help-request-details.html?requestId=${requestId}`;
}

function formatDate(dateValue) {
    if (!dateValue) {
        return "Not specified";
    }

    const date = new Date(dateValue);
    return date.toLocaleDateString();
}														
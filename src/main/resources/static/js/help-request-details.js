const logoutBtn = document.getElementById("logoutBtn");
const backBtn = document.getElementById("backBtn");
const acceptBtn = document.getElementById("acceptBtn");
const message = document.getElementById("message");

const recommendedOffersList =
    document.getElementById("recommendedOffersList");

const urlParams = new URLSearchParams(window.location.search);
const requestId = urlParams.get("requestId");

let currentFamilyId = null;

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

backBtn.addEventListener("click", () => {
    window.location.href = "/dashboard.html";
});

// First get logged-in family
fetch("/api/family/me")
    .then(response => response.json())
    .then(family => {

        currentFamilyId = family.familyId;

        loadRequestDetails();
    })
    .catch(error => {

        console.log(error);

        showMessage(
            "Could not load family information.",
            "error"
        );
    });

function loadRequestDetails() {

    fetch(`/api/help-requests/${requestId}`)
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "Could not load request details"
                );
            }

            return response.json();
        })
        .then(request => {

            document.getElementById("requestTitle")
                .textContent = request.title;

            document.getElementById("requestCategory")
                .textContent = request.category;

            document.getElementById("requestCity")
                .textContent = request.city;

            document.getElementById("requestedDate")
                .textContent = formatDate(
                    request.requestedDate
                );

            document.getElementById("neededDate")
                .textContent = formatDate(
                    request.neededDate
                );

            document.getElementById("requestDescription")
                .textContent = request.description;


            if (request.requesterFamily.familyId === currentFamilyId) {
                acceptBtn.style.display = "none";
                showMessage("This is your own help request.","error");
				loadRecommendedOffers(request.requestId);
            }

            if (request.status !== "PENDING") {

                acceptBtn.style.display = "none";

                showMessage(
                    "This help request is no longer available.",
                    "error"
                );
            }
        })
        .catch(error => {

            console.log(error);

            showMessage(
                "Could not load help request details.",
                "error"
            );
        });
}

acceptBtn.addEventListener("click", () => {

    fetch(`/api/tasks/accept-request/${requestId}`, {
        method: "POST"
    })
    .then(response => {

        if (response.ok) {

            showMessage(
                "Help request accepted successfully.",
                "success"
            );

            setTimeout(() => {

                window.location.href =
                    "/dashboard.html";

            }, 1000);

        } else {

            showMessage(
                "Could not accept help request.",
                "error"
            );
        }
    })
    .catch(error => {

        console.log(error);

        showMessage(
            "Something went wrong. Please try again.",
            "error"
        );
    });
});

function loadRecommendedOffers(requestId) {

    fetch(
        `/api/help-offers/recommendations/request/${requestId}`
    )
    .then(response => response.json())
    .then(helpOffers => {

        recommendedOffersList.innerHTML = "";

        if (helpOffers.length === 0) {

            recommendedOffersList.innerHTML = `
                <p class="empty-message">
                    No matching help offers found.
                </p>
            `;

            return;
        }

        helpOffers.forEach(offer => {

            const card = document.createElement("div");

            card.classList.add("request-card");

            card.innerHTML = `
                <h3>${offer.title}</h3>

                <p>
                    <strong>Category:</strong>
                    ${offer.category}
                </p>

                <p>
                    <strong>City:</strong>
                    ${offer.city}
                </p>

                <p>${offer.description}</p>
            `;

            recommendedOffersList.appendChild(card);
        });
    })
    .catch(error => {

        console.log(error);

        recommendedOffersList.innerHTML = `
            <p class="empty-message">
                Could not load recommendations.
            </p>
        `;
    });
}

function showMessage(text, type) {

    message.style.display = "block";

    message.className = "message " + type;

    message.textContent = text;
}

function formatDate(dateValue) {

    if (!dateValue) {
        return "Not specified";
    }

    const date = new Date(dateValue);

    return date.toLocaleDateString();
}
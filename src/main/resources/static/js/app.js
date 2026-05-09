function showMessage(text){
    document.getElementById("message").innerText = text;
}

/* REGISTER */

document.addEventListener("DOMContentLoaded", function(){

    const registerForm =
        document.getElementById("registerForm");

    if(registerForm){

        registerForm.addEventListener(
            "submit",
            registerFamily
        );
    }
});

function registerFamily(event){

    event.preventDefault();

    const family = {

        familyName:
            document.getElementById("familyName").value,

        email:
            document.getElementById("email").value,

        password:
            document.getElementById("password").value,

        phoneNumber:
            document.getElementById("phoneNumber").value,

        city:
            document.getElementById("city").value,

        address:
            document.getElementById("address").value,

        bio:
            document.getElementById("bio").value,

        numberOfMembers:
            document.getElementById("numberOfMembers").value
    };

    fetch("/api/auth/register",{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(family)

    })

    .then(response => response.json())

    .then(data => {

        document.getElementById(
            "registerMessage"
        ).innerText =
            "Family registered successfully.";

    })

    .catch(error => {

        document.getElementById(
            "registerMessage"
        ).innerText =
            "Registration failed.";

    });
}

/* UPDATE FAMILY */

function updateFamily(){

    const familyId = document.getElementById("familyId").value;

    const family = {
        familyName: document.getElementById("familyNameUpdate").value,
        phoneNumber: document.getElementById("phoneNumberUpdate").value,
        city: document.getElementById("cityUpdate").value,
        address: document.getElementById("addressUpdate").value,
        bio: document.getElementById("bioUpdate").value,
        numberOfMembers: document.getElementById("membersUpdate").value
    };

    fetch("/api/family/" + familyId + "/profile", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(family)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Update failed with status " + response.status);
        }
        return response.json();
    })
    .then(data => {
        showMessage("Family updated successfully.");
        console.log(data);
    })
    .catch(error => {
        showMessage("Failed to update family: " + error.message);
        console.log(error);
    });
}

/* HELP OFFER */

function createHelpOffer(){

    const familyId = document.getElementById("offerFamilyId").value;

    const helpOffer = {
        title: document.getElementById("offerTitle").value,
        description: document.getElementById("offerDescription").value,
        category: document.getElementById("offerCategory").value,
        availability: document.getElementById("offerAvailability").value
    };

    fetch("/helpOffers/" + familyId, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(helpOffer)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Request failed with status " + response.status);
        }
        return response.json();
    })
    .then(data => {
        showMessage("Help offer created successfully.");
        console.log(data);
    })
    .catch(error => {
        showMessage("Failed to create help offer: " + error.message);
        console.log(error);
    });
}

function getHelpOffers(){

    fetch("/helpOffers")

    .then(response => response.json())

    .then(data => {

        const list =
            document.getElementById("offersList");

        list.innerHTML = "";

        data.forEach(offer => {

            list.innerHTML += `

            <div class="item">

                <strong>ID:</strong> ${offer.id}<br>

                <strong>Title:</strong> ${offer.title}<br>

                <strong>Description:</strong> ${offer.description}<br>

                <strong>Category:</strong> ${offer.category}<br>

                <strong>Availability:</strong> ${offer.availability}

            </div>
            `;
        });

    });
}

function updateHelpOffer(){

    const offerId =
        document.getElementById("updateOfferId").value;

    const helpOffer = {

        title:
            document.getElementById("updateOfferTitle").value,

        description:
            document.getElementById("updateOfferDescription").value,

        category:
            document.getElementById("updateOfferCategory").value,

        availability:
            document.getElementById("updateOfferAvailability").value
    };

    fetch("/helpOffers/" + offerId,{

        method:"PUT",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(helpOffer)

    })

    .then(response => response.json())

    .then(data => {

        showMessage(
            "Help offer updated successfully."
        );

    });
}

function deleteHelpOffer(){

    const offerId =
        document.getElementById("deleteOfferId").value;

    fetch("/helpOffers/" + offerId,{

        method:"DELETE"

    })

    .then(response => response.text())

    .then(data => {

        showMessage(data);

    });
}

/* EMERGENCY */

function createEmergencyRequest(){

    const familyId =
        document.getElementById("emergencyFamilyId").value;

    const emergencyRequest = {

        title:
            document.getElementById("emergencyTitle").value,

        location:
            document.getElementById("emergencyLocation").value
    };

    fetch("/emergencyRequests/" + familyId,{

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify(emergencyRequest)

    })

    .then(response => response.json())

    .then(data => {

        showMessage(
            "Emergency request created successfully."
        );

    });
}

function getAllEmergencies(){

    fetch("/emergencyRequests")

    .then(response => response.json())

    .then(data => {

        displayEmergencies(data);

    });
}

function getOpenEmergencies(){

    fetch("/emergencyRequests/open")

    .then(response => response.json())

    .then(data => {

        displayEmergencies(data);

    });
}

function displayEmergencies(data){

    const list =
        document.getElementById("emergencyList");

    list.innerHTML = "";

    data.forEach(request => {

        list.innerHTML += `

        <div class="item emergencyItem">

            <strong>ID:</strong> ${request.id}<br>

            <strong>Title:</strong> ${request.title}<br>

            <strong>Location:</strong> ${request.location}<br>

            <strong>Status:</strong> ${request.status}<br>

            <strong>Priority:</strong> ${request.priorityLevel}

        </div>
        `;
    });
}

function resolveEmergencyRequest(){

    const requestId =
        document.getElementById("resolveEmergencyId").value;

    fetch("/emergencyRequests/" + requestId + "/resolve",{

        method:"PUT"

    })

    .then(response => response.json())

    .then(data => {

        showMessage(
            "Emergency request resolved."
        );

    });
}
const logoutBtn = document.getElementById("logoutBtn");

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

fetch("/api/family/me")
    .then(response => {
        if (!response.ok) {
            throw new Error("Could not load profile");
        }

        return response.json();
    })
    .then(family => {

        document.getElementById("familyName").textContent = family.familyName;
        document.getElementById("email").textContent = family.email;
        document.getElementById("phoneNumber").textContent = family.phoneNumber;
        document.getElementById("city").textContent = family.city;
        document.getElementById("address").textContent = family.address;
        document.getElementById("bio").textContent = family.bio;
        document.getElementById("numberOfMembers").textContent = family.numberOfMembers;

        document.getElementById("averageRating").textContent =
            family.averageRating != null
                ? family.averageRating.toFixed(1)
                : "0";

        document.getElementById("completedTasks").textContent =
            family.completedTasks != null
                ? family.completedTasks
                : "0";

        document.getElementById("trustScore").textContent =
            family.trustScore != null
                ? family.trustScore.toFixed(1)
                : "0";
    })
    .catch(error => {
        console.log(error);
        alert("Could not load profile.");
    });
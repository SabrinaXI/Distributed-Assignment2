const feedbackForm = document.getElementById("feedbackForm");
const message = document.getElementById("message");
const logoutBtn = document.getElementById("logoutBtn");
const backBtn = document.getElementById("backBtn");

const urlParams = new URLSearchParams(window.location.search);
const taskId = urlParams.get("taskId");

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
    window.location.href = "/my-tasks.html";
});

feedbackForm.addEventListener("submit", function(e) {
    e.preventDefault();

    const feedback = {
        rating: parseInt(document.getElementById("rating").value),
        comment: document.getElementById("comment").value.trim()
    };

    fetch(`/api/feedback/task/${taskId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(feedback)
    })
    .then(response => {
        if (response.ok) {
            showMessage("Feedback submitted successfully.", "success");

            setTimeout(() => {
                window.location.href = "/my-tasks.html";
            }, 1000);
        } else {
            showMessage("Could not submit feedback. Maybe feedback was already submitted.", "error");
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
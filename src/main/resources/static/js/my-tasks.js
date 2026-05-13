const logoutBtn = document.getElementById("logoutBtn");
const tasksList = document.getElementById("tasksList");

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

// First get the logged-in family, then load tasks
fetch("/api/family/me")
    .then(response => response.json())
    .then(family => {
        currentFamilyId = family.familyId;
        loadTasks();
    })
    .catch(error => {
        console.log(error);
        tasksList.innerHTML = `<p class="empty-message">Could not load family information.</p>`;
    });

function loadTasks() {
    fetch("/api/tasks/me")
        .then(response => {
            if (!response.ok) {
                throw new Error("Could not load tasks");
            }

            return response.json();
        })
        .then(tasks => {
            tasksList.innerHTML = "";

            if (tasks.length === 0) {
                tasksList.innerHTML = `<p class="empty-message">You do not have any tasks yet.</p>`;
                return;
            }

            tasks.forEach(task => {
                const card = document.createElement("div");
                card.classList.add("task-card");

                card.innerHTML = `
                    <h3>${task.helpRequest.title}</h3>

                    <p><strong>Category:</strong> ${task.helpRequest.category}</p>
                    <p><strong>City:</strong> ${task.helpRequest.city}</p>
                    <p><strong>Needed Date:</strong> ${formatDate(task.helpRequest.neededDate)}</p>

                    <p><strong>Requester Family:</strong> ${task.requesterFamily.familyName}</p>
                    <p><strong>Helper Family:</strong> ${task.helperFamily.familyName}</p>

                    <span class="status ${getStatusClass(task.status)}">${task.status}</span>

                    ${task.status === "IN_PROGRESS" && task.requesterFamily.familyId === currentFamilyId ? `
                        <button class="complete-btn" onclick="completeTask(${task.taskId})">Mark Completed</button>
                    ` : ""}

                    ${task.status === "IN_PROGRESS" ? `
                        <button class="cancel-btn" onclick="cancelTask(${task.taskId})">Cancel Task</button>
                    ` : ""}

                    ${task.status === "COMPLETED" && task.requesterFamily.familyId === currentFamilyId ? `
                        <button class="feedback-btn" id="feedbackBtn-${task.taskId}" onclick="leaveFeedback(${task.taskId})">Leave Feedback</button>
                    ` : ""}
                `;

                tasksList.appendChild(card);

                if (task.status === "COMPLETED" && task.requesterFamily.familyId === currentFamilyId) {
                    checkFeedbackExists(task.taskId);
                }
            });
        })
        .catch(error => {
            console.log(error);
            tasksList.innerHTML = `<p class="empty-message">Could not load tasks.</p>`;
        });
}

function completeTask(taskId) {
    fetch(`/api/tasks/${taskId}/complete`, {
        method: "PUT"
    })
    .then(response => {
        if (response.ok) {
            loadTasks();
        } else {
            alert("Only the requester family can complete this task.");
        }
    })
    .catch(error => {
        console.log(error);
        alert("Something went wrong.");
    });
}

function cancelTask(taskId) {
    fetch(`/api/tasks/${taskId}/cancel`, {
        method: "PUT"
    })
    .then(response => {
        if (response.ok) {
            loadTasks();
        } else {
            alert("Could not cancel task.");
        }
    })
    .catch(error => {
        console.log(error);
        alert("Something went wrong.");
    });
}

function checkFeedbackExists(taskId) {
    fetch(`/api/feedback/task/${taskId}/exists`)
        .then(response => response.json())
        .then(exists => {
            const feedbackBtn = document.getElementById(`feedbackBtn-${taskId}`);

            if (exists && feedbackBtn) {
                feedbackBtn.style.display = "none";
            }
        })
        .catch(error => {
            console.log(error);
        });
}

function leaveFeedback(taskId) {
    window.location.href = `/feedback.html?taskId=${taskId}`;
}

function getStatusClass(status) {
    if (status === "COMPLETED") {
        return "status-completed";
    }

    if (status === "CANCELLED") {
        return "status-cancelled";
    }

    return "status-in-progress";
}

function formatDate(dateValue) {
    if (!dateValue) {
        return "Not specified";
    }

    const date = new Date(dateValue);
    return date.toLocaleDateString();
}
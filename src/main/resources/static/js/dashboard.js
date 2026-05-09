const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", () => {
   
	fetch("/logout", {																					//fetch is an async function. Fetch returns promises. We use promises to keep async functions in order
       method: "POST"
   })
   .then(response => {																					//.then because fetch returns a promise and if promise resolved like backend sends the response then .then would be executed. However need to check in the .then if res=200 or 404, 401 etc
       window.location.href = "/login.html"
   })
   .catch(error => {																					//if promise not resolved then catch executes means so no reply from server
		console.log(error)
   })										
})														
document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("loginForm");
  const inputs = document.querySelectorAll("#loginForm input");
  const closeButton = document.querySelector(".modal-close");
  const errorMessage = document.getElementById("errorMsg");

  if (closeButton) {
    closeButton.addEventListener("click", () => {
      inputs.forEach((input) => (input.value = "")); // clear inputs
      if (errorMessage) {
        errorMessage.textContent = "  "; // clear error message
        errorMessage.style.display = "none"; // hide it
      }
    });
  }

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const loginData = {
      email: document.getElementById("userEmail").value,
      password: document.getElementById("userPassword").value,
    };

    fetch("/api/users/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(loginData),
    })
      .then(async (res) => {
        const data = await res.json(); // parse JSON
        if (!res.ok) throw new Error(data.error || "Login failed");
        return data;
      })
      .then((user) => {
        alert("Login successful! Welcome " + user.username);
        window.location.href = "/dashboard"; // redirect to dashboard/home
      })
      .catch((err) => {
        document.getElementById("errorMsg").innerText = err.message;
      });
  });
});

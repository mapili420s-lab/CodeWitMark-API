document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("signupForm");
  const phoneInput = document.getElementById("phoneNumber");

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const phone = phoneInput.value.trim(); // remove extra spaces

    if (!phone || phone.length !== 10 || phone[0] !== "9") {
      document.getElementById("errorMsg").innerText =
        "Phone number must be 10 digits starting with 9";
      return; // stop form submission
    }
    const userData = {
      firstName: document.getElementById("firstName").value,
      lastName: document.getElementById("lastName").value,
      username: document.getElementById("username").value,
      phoneNumber: "+63" + phone,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
    };

    fetch("/api/users/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    })
      .then(async (res) => {
        if (!res.ok) {
          const errorMsg = await res.text();
          throw new Error(errorMsg);
        }
        return res.json();
      })
      .then((data) => {
        alert("Registration successful!");
        window.location.href = "/login";
      })
      .catch((err) => {
        document.getElementById("errorMsg").innerText = err.message;
      });
  });
});

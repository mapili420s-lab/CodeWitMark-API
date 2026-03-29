document
  .getElementById("createAppointment")
  .addEventListener("submit", function (e) {
    e.preventDefault();

    const appointment = {
      name: document.getElementById("name").value,
      email: document.getElementById("email").value,
      service: document.getElementById("service").value,
      date: document.getElementById("date").value,
      time: document.getElementById("time").value,
    };

    fetch("api/appointments/createAppointment", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(appointment),
    })
      .then((response) => response.json())
      .then((data) => {
        alert("Appointment created successfully!");
        console.log(data);
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });

document.getElementById("login").addEventListener("submit", async function(event) {
    event.preventDefault();

    let username = document.getElementById("usermail").value.trim();
    let password = document.getElementById("userpassword").value.trim();

    let logindata = { userMail: username, userPassword: password };

    try {
        let response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(logindata),
            credentials: "include" 
        });

        if (response.ok) {
            let data = await response.json();
            if (data.role === "ROLE_ADMIN") window.location.href = "admin_dashboard.html";
            else if (data.role === "ROLE_USER") window.location.href = "userdashboard.html";
            else alert("Unknown role!");
        } else {
            alert("Invalid Credentials");
        }
    } catch (error) {
        alert("Service Unreachable");
        console.error(error);
    }
});

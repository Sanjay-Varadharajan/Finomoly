document.getElementById("signupForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const userName = document.getElementById("username").value.trim();
    const userMail = document.getElementById("usermail").value.trim();
    const userPassword = document.getElementById("userpassword").value.trim();

    if (!userName || !userMail || !userPassword) {
        alert("Bro… fill all the fields 😅");
        return;
    }

    if (userPassword.length < 6) {
        alert("Password should be at least 6 characters 🛡️");
        return;
    }

    const userData = {
        userName: userName,
        userMail: userMail,
        userPassword: userPassword
    };

    try {
        const response = await fetch("http://localhost:8080/auth/user/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            alert("Account created successfully");
            window.location.href = "/HTML/login.html";
        } else {
            const error = await response.text();
            alert(error || "Signup failed ");
        }

    } catch (err) {
        console.error(err);
        alert("Server not responding ");
    }
});

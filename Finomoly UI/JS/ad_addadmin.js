document.getElementById("signupForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const adminData = {
        userName: document.getElementById("username").value.trim(),
        userMail: document.getElementById("usermail").value.trim(),
        userPassword: document.getElementById("userpassword").value.trim()
    };

    try {
        const response = await fetch("http://localhost:8080/api/admin/admin/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include", 
            body: JSON.stringify(adminData)
        });

        const message = await response.text();

        if (!response.ok) {
            throw new Error(message);
        }

        alert(" Admin created successfully!");
        document.getElementById("signupForm").reset();

    } catch (error) {
        console.error("Add admin error:", error);
        alert("Failed to create admin");
    }
});

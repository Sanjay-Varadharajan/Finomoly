    async function loadTotalExpenses() {
        try {
            let response = await fetch("http://localhost:8080/api/user/expense/total", {
                method: "GET",
                credentials: "include"
            });

            if (response.ok) {
                let total = await response.json(); 
                document.getElementById("totalExpenses").textContent = `₹ ${total.toFixed(2)}`;
            } else {
                document.getElementById("totalExpenses").textContent = "Failed to load total expenses 😵";
            }
        } catch (error) {
            document.getElementById("totalExpenses").textContent = "Service unreachable";
            console.error(error);
        }
    }

    window.addEventListener("DOMContentLoaded", loadTotalExpenses);

document.addEventListener("DOMContentLoaded", () => {

    const container = document.querySelector(".stats-container");
    const cards = container.querySelectorAll(".stats-card .stats-value");

    cards.forEach(card => card.textContent = "Loading...");

    fetch("http://localhost:8080/api/admin/stats/viewall", {
        method: "GET",
        credentials: "include", 
        headers: {
            "Content-Type": "application/json"
        }
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to fetch stats: " + response.status);
        return response.json();
    })
    .then(data => {
        console.log("Stats data received:", data);

        cards[0].textContent = data.totalUsers ?? 0;
        cards[1].textContent = "₹" + (data.totalIncome ?? 0).toFixed(2);
        cards[2].textContent = "₹" + (data.totalExpenses ?? 0).toFixed(2);
    })
    .catch(err => {
        console.error("Fetch error:", err);
        cards.forEach(card => card.textContent = "Error");
    });

});

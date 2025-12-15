
document.addEventListener("DOMContentLoaded", () => {
    const container = document.getElementById("anomalyCardsContainer");
    container.innerHTML = ""; 

    fetch("http://localhost:8080/api/admin/anomaly/view", {
        method: "GET",
        credentials: "include" 
    })
    .then(response => {
        if (!response.ok) throw new Error("Failed to fetch anomaly data: " + response.status);
        return response.json();
    })
    .then(data => {
        if (!data || data.length === 0) {
            container.innerHTML = "<p style='color:#2ecc71; text-align:center;'>No anomalies detected 🎉</p>";
            return;
        }

        data.forEach(anomaly => {
            const card = document.createElement("div");
            card.className = "anomaly-card";

            card.innerHTML = `
                <h4>Anomaly Detected ⚠️</h4>
                <p><strong>Expense ID:</strong> ${anomaly.expenseId}</p>
                <p><strong>Category:</strong> ${anomaly.expenseCategory}</p>
                <p><strong>Amount:</strong> ₹${anomaly.expenseAmount.toFixed(2)}</p>
                <p><strong>Date:</strong> ${anomaly.expenseDate}</p>
                <p><strong>Reason:</strong> ${anomaly.anomalyReason}</p>
            `;

            container.appendChild(card);
        });
    })
    .catch(err => {
        console.error("Fetch error:", err);
        container.innerHTML = "<p style='color:red; text-align:center;'>Error loading anomaly data ⚠️</p>";
    });
});

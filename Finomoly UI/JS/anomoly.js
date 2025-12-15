document.addEventListener("DOMContentLoaded", () => {

    const container = document.getElementById("anomalyCardsContainer");
    container.innerHTML = "";

    fetch("http://localhost:8080/api/user/anomaly", {
        method: "GET",
        credentials: "include"
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to fetch anomaly data");
        }
        return response.json();
    })
    .then(data => {

        const summaryCard = document.createElement("div");
        summaryCard.className = "anomaly-card";
        summaryCard.innerHTML = `
            <h3>Summary</h3>
            <p><strong>Total Income:</strong> ₹${data.totalIncome}</p>
            <p><strong>Total Expense:</strong> ₹${data.totalExpense}</p>
            <p><strong>Total Savings:</strong> ₹${data.totalSavings}</p>
            <p><strong>Highest Expense:</strong> ₹${data.highestExpense}</p>
            <p><strong>Most Spent Category:</strong> ${data.mostSpentCategory}</p>
        `;
        container.appendChild(summaryCard);

        if (data.anomalies && data.anomalies.length > 0) {
            data.anomalies.forEach(anomaly => {
                const card = document.createElement("div");
                card.className = "anomaly-card";
                card.innerHTML = `
                    <h4>Anomaly Detected ⚠️</h4>
                    <p><strong>Expense ID:</strong> ${anomaly.expenseId}</p>
                    <p><strong>Category:</strong> ${anomaly.expenseCategory}</p>
                    <p><strong>Amount:</strong> ₹${anomaly.expenseAmount}</p>
                    <p><strong>Date:</strong> ${anomaly.expenseDate}</p>
                    <p><strong>Reason:</strong> ${anomaly.anomalyReason}</p>
                `;
                container.appendChild(card);
            });
        } else {
            const noAnomaly = document.createElement("p");
            noAnomaly.textContent = "No anomalies detected 🎉";
            noAnomaly.style.color = "#2ecc71";
            container.appendChild(noAnomaly);
        }
    })
    .catch(err => {
        console.error(err);
        container.innerHTML =
            "<p style='color:red;'>Error loading anomaly data ⚠️</p>";
    });
});

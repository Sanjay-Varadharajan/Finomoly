
document.getElementById("deleteIncomeForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const incomeId = document.getElementById("expenseId").value;
    const message = document.getElementById("message");

    try {
        const response = await fetch(
            `http://localhost:8080/api/user/income/delete?incomeId=${incomeId}`,
            {
                method: "DELETE",
                credentials: "include"
            }
        );

        if (response.ok) {
            message.textContent = "Income deleted successfully 💸";
            message.style.color = "#00ffcc";
            document.getElementById("deleteIncomeForm").reset();
        } else {
            message.textContent = "Invalid Income ID or delete failed 😵";
            message.style.color = "#ffdddd";
        }
    } catch (error) {
        message.textContent = "Service unreachable 🚧";
        message.style.color = "#ffdddd";
        console.error(error);
    }
});

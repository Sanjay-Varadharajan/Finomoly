document.getElementById("deleteExpenseForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const expenseId = document.getElementById("expenseId").value;

    try {
        let response = await fetch(`http://localhost:8080/api/user/expense/delete?expenseId=${expenseId}`, {
            method: "DELETE",
            credentials: "include"
        });

        const messageEl = document.getElementById("message");
        if (response.ok) {
            messageEl.textContent = `Expense ${expenseId} deleted successfully `;
            messageEl.style.color = "green";
        } else if (response.status === 401 || response.status === 403) {
            messageEl.textContent = "You are not logged in or don’t have access ";
            messageEl.style.color = "red";
        } else {
            messageEl.textContent = "Failed to delete expense 😵";
            messageEl.style.color = "red";
        }
    } catch (error) {
        document.getElementById("message").textContent = "Could not reach the server ";
        console.error(error);
    }
});

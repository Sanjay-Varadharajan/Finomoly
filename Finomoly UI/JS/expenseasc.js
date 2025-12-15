async function loadExpenses() {
    try {
        let response = await fetch("http://localhost:8080/api/user/expenses/sortedbyasc", {
            method: "GET",
            credentials: "include" 
        });

        if (response.ok) {
            let expenses = await response.json(); 
            let tbody = document.querySelector("#expenseTable tbody");
            tbody.innerHTML = ""; 

            expenses.forEach(expense => {
                let row = document.createElement("tr");
                
                let idTd = document.createElement("td");
                idTd.textContent = expense.expenseId;
                row.appendChild(idTd);

                let categoryTd = document.createElement("td");
                categoryTd.textContent = expense.expenseCategory;
                row.appendChild(categoryTd);

                let amountTd = document.createElement("td");
                amountTd.textContent = expense.expenseAmount.toFixed(2);
                row.appendChild(amountTd);

                let dateTd = document.createElement("td");
                dateTd.textContent = new Date(expense.expenseDate).toLocaleString();
                row.appendChild(dateTd);

                tbody.appendChild(row);
            });
        } else {
            alert("Failed to load expenses 😵");
        }
    } catch (error) {
        alert("Service Unreachable ");
        console.error(error);
    }
}

window.addEventListener("DOMContentLoaded", loadExpenses);

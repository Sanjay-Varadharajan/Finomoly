
async function loadIncomes() {
    try {
        let response = await fetch("http://localhost:8080/api/user/income/sortedbyasc", {
            method: "GET",
            credentials: "include" 
        });

        if (response.ok) {
            let incomes = await response.json();
            let tbody = document.querySelector("#expenseTable tbody");
            tbody.innerHTML = ""; 

            incomes.forEach(income => {
                let row = document.createElement("tr");

                let idTd = document.createElement("td");
                idTd.textContent = income.incomeId;
                row.appendChild(idTd);

                let amountTd = document.createElement("td");
                amountTd.textContent = income.incomeAmount.toFixed(2);
                row.appendChild(amountTd);

                let dateTd = document.createElement("td");
                dateTd.textContent = new Date(income.incomeDate).toLocaleString();
                row.appendChild(dateTd);

                tbody.appendChild(row);
            });
        } else if (response.status === 401 || response.status === 403) {
            alert("You are not logged in or don’t have access");
        } else {
            alert("Failed to load incomes ");
        }
    } catch (error) {
        alert("Could not reach the server ");
        console.error(error);
    }
}

window.addEventListener("DOMContentLoaded", loadIncomes);

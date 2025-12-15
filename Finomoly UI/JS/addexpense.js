document.getElementById("form").addEventListener("submit", async function(event) {
    event.preventDefault();

    let category = document.getElementById("category").value.trim();
    let amount = parseFloat(document.getElementById("amount").value);

    let addexpense = {
        expenseCategory: category,
        expenseAmount: amount
    };

    try {
        let response = await fetch("http://localhost:8080/api/user/expenses/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(addexpense),
                        credentials: "include" 

        });

     let text = await response.text();
if (response.ok) {
    alert("" + text);
    document.getElementById("form").reset();
} else {
    alert("Failed to add expense\n" + text);
}

    } catch (error) {
        alert("Service Unreachable");
        console.error(error);
    }
});

document.getElementById("form").addEventListener("submit", async function(event) {
    event.preventDefault();

    let amount = parseFloat(document.getElementById("amount").value);

    let addexpense = {
        incomeAmount: amount
    };

    try {
        let response = await fetch("http://localhost:8080/api/user/income/add", {
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
    alert("Failed to add income\n" + text);
}

    } catch (error) {
        alert("Service Unreachable");
        console.error(error);
    }
});

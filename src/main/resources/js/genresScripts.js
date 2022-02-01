function refreshTable(){
    fetch("http://localhost:8080/genres", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },

    })
        .then((response) => {
            var outputHTML = "";
            for (const genre in response.json()) {
               outputHTML+="<tr> " +
                   "<td>" +
                   genre['name'] +
                   "</td>" +
                   "</tr>";
            }
            document.getElementById("genresTable").innerHTML =outputHTML;
        })
}
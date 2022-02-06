document.onload = refreshTable()

function refreshTable(){
    var url= "http://localhost:8080/genres";
    fetch(url,{
        method: 'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    }).then(response => response.json())
        .then(data =>{
        var outputHTML = "";
        for (var i = 0; i < data.length; i++) {
            outputHTML += "<tr>" +
                `<td><input type="checkbox" id="${data[i]['name']}"/><label for="nameGenre">${data[i]['name']}</label></td>`
                + "</tr>";
        }
        document.getElementById("genresData").innerHTML=outputHTML;
    })
}

function getFormAddGenre(){
    document.forms.namedItem('addGenreForm').hidden = false;
}
function addGenre(){
    var url= "http://localhost:8080/genres/addGenres/";
    url+=document.getElementById("nameGenreInput").value
    fetch(url,{
        method:'POST'
    }).then(function (res){refreshTable()})

}

function deleteGenre(){
    var url= "http://localhost:8080/genres/removeGenres/";
    document.querySelectorAll('input[type=checkbox]:checked').forEach(
        (checkbox)=>{
            url+=`${checkbox.id},`
        }
    )
    fetch(url,{
        method:'DELETE'
    }).then(function (res){refreshTable()})
}
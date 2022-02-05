document.onload = refreshTable()

function refreshTable(){
    var request = new XMLHttpRequest()
    var url= "http://localhost:8080/genres";
    request.onreadystatechange = function (){
        var myArr = JSON.parse(this.responseText);
        var outputHTML = "";
        for (var i =0;i<myArr.length;i++) {
            outputHTML+="<tr>"+
                `<td><input type="checkbox" id="${myArr[i]['name']}"/><label for="costDress">${myArr[i]['name']}</label></td>`
                +"</tr>";
        }
        document.getElementById("genresData").innerHTML=outputHTML;

    }
    request.open("GET",url)
    request.setRequestHeader('Content-Type','application/json')
    request.send()
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
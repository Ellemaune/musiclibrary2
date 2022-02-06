document.onload = refreshTable()
//document.addEventListener("mousedown",checkFormOnWork())

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
                `<td><label><input type="checkbox" id="${data[i]['name']}"/>${data[i]['name']}</label></td>`
                + "</tr>";
        }
        document.getElementById("genresData").innerHTML=outputHTML;
    })
}


function checkFormOnWork(event){
    //1) Проверяем на отрытую форму. Если да, проверяем дальше
    //2) Проверяем на место нажатия. Если это форма - ничего, иначе
    //      Скрываем форму, и очищаем её поля
    //if (!isFormHidden()) {
    //    if (event.target.id !== 'addGenreFormID') {
    //        setFormAddGenreHiden();
    //        event.stopPropagation();
    //    }
    //}
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

function isFormHidden(){
    return document.forms.namedItem('addGenreForm').hidden;
}
function setFormAddGenreHiden(){
    document.forms.namedItem('addGenreForm').hidden = true;
    document.getElementById("nameGenreInput").innerText = "";
}
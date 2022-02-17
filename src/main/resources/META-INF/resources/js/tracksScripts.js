document.onload = refreshTable()

function refreshTable(){
    var url= "http://localhost:8080/tracks";
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
                `<td><label><input type="checkbox" id="${data[i]['name']}"/>${data[i]['name']  } </label></td>`
                  + `<td><label>${data[i]['singer']}</label></td>`
                  + `<td><label>${data[i]['album']}</label></td>`
                  + `<td><label>${data[i]['recordLength']}</label></td>`
                  + `<td><label>${data[i]['genre']['name']}</label></td>`// исправил***


                + "</tr>";
        }
        document.getElementById("tracksData").innerHTML=outputHTML;
    })
}


function getFormAddTrack(){
    document.forms.namedItem('addTrackForm').hidden = false;
}

function addTrack(){
    var url= "http://localhost:8080/tracks/addTracks/";
    url+=document.getElementById("nameTrackInput").value +","
    + document.getElementById("singerTrackInput").value +","
    + document.getElementById("nameAlbumTrackInput").value +","
    + document.getElementById("recordLengthTrackInput").value +","
    + document.getElementById("GenreOfTrackInput").value
    fetch(url,{
        method:'POST'
    }).then(function (res){refreshTable()})

}

function deleteTrack(){
    var url= "http://localhost:8080/tracks/removeTracks/";
    document.querySelectorAll('input[type=checkbox]:checked').forEach(
        (checkbox)=>{
            url+=`${checkbox.id},`
        }
    )
    fetch(url,{
        method:'DELETE'
    }).then(function (res){refreshTable()})
}

function setFormAddTrackHiden(){
    document.forms.namedItem('addTrackForm').hidden = true;
    document.getElementById("nameTrackInput").value = "";
}
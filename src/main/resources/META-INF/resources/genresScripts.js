function refreshTable(){
    var request = new XMLHttpRequest()
    var url= "http://localhost:8080/genres";
    request.onreadystatechange = function (){
        var myArr = JSON.parse(this.responseText);
        var outputHTML = "";
        for (var i =0;i<myArr.length;i++) {
            outputHTML+="<tr>" +
                "<td>" +
                myArr[i]['name'] +
                "</td>" +
                "</tr>";
        }
        document.getElementById("genresTable").innerHTML=outputHTML;

    }
    request.open("GET",url)
    request.setRequestHeader('Content-Type','application/json')
    request.send()

}

function addGenre(){

}

function deleteGenre(){

}
/**
 * Created by Den on 16.09.2016.
 */

function httpGetAll() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", 'http://localhost:8080/rest/user/getAll', false); // false for synchronous request
    xmlHttp.send(null);
    var data = JSON.parse(xmlHttp.responseText);
    var table = '<table class="table table-hover">';
    table += '<tr> <th>ID</th>  <th>Name</th> <th>Permission</th> </tr>';
    data.forEach(function (item, i, data) {
        (item.role == 1)?item.role = "Admin": item.role = "Simple user";
        table += "<tr> <td>" + item.id + "</td>  <td>" + item.name + "</td> <td>" + item.role + "</td> </tr>";
    });
    table += '</table>';
    document.getElementById("JsonResult").innerHTML = table;
    console.log(xmlHttp.status);
    // console.log(xmlHttp.responseText);
    // return xmlHttp.responseText;
}

function httpGet() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", 'http://localhost:8080/rest/user/get/' + document.getElementById("get_id").value, false); // false for synchronous request
    xmlHttp.send(null);
    var data = JSON.parse(xmlHttp.responseText);
    var table = '<table class="table table-hover">';
    (item.role == 1)?item.role = "Admin": item.role = "Simple user";
    table += '<tr> <th>ID</th>  <th>Name</th> <th>Permission</th> </tr>';

    table += "<tr> <td>" + data.id + "</td>  <td>" + data.name + "</td> <td>" + data.role + "</td> </tr>";

    table += '</table>';
    document.getElementById("JsonResult").innerHTML = table;

}

function httpGetUserByRole(role) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", 'http://localhost:8080/rest/user/getByRole/' + role, false); // false for synchronous request
    xmlHttp.send(null);
    var data = JSON.parse(xmlHttp.responseText);
    var table = '<table class="table table-hover">';
    table += '<tr> <th>ID</th>  <th>Name</th> <th>Permission</th> </tr>';
    data.forEach(function (item, i, data) {
        (item.role == 1)?item.role = "Admin": item.role = "Simple user";
        table += "<tr> <td>" + item.id + "</td>  <td>" + item.name + "</td> <td>" + item.role + "</td> </tr>";
    });
    table += '</table>';
    document.getElementById("JsonResult").innerHTML = table;
    console.log(xmlHttp.status);

}
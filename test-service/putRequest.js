/**
 * Created by Den on 16.09.2016.
 */
function httpPut(){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "PUT", 'http://localhost:8080/rest/user/update/' + document.getElementById("put_id").value, false); // false for synchronous request
    xmlHttp.setRequestHeader('Content-type', 'application/json');
    var string = document.getElementById("put_name").value;
    var json = JSON.stringify({
        "id": 0,
        "name": string,
        "role": + document.getElementById("put_role").value
    });
    console.log(document.getElementById("put_name").value);
    xmlHttp.send(json);
    httpGetAll();
}

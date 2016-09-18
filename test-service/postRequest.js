/**
 * Created by Den on 16.09.2016.
 */

function httpPost(){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", 'http://localhost:8080/rest/user/add', true); // false for synchronous request
    xmlHttp.setRequestHeader('Content-type', 'application/json');
    var string = document.getElementById("post_name").value;
    var json = JSON.stringify({
        "id": 0,
        "name": string,
        "role": + document.getElementById("post_role").value
    });
    console.log(string);
    xmlHttp.send(json);
    httpGetAll();
    // document.getElementById("JsonResult").innerHTML = xmlHttp.responseText;
}

/**
 * Created by Den on 16.09.2016.
 */

function httpDelete(){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "DELETE", 'http://localhost:8080/rest/user/delete/'
        + document.getElementById("delete_id").value, false); // false for synchronous request
    xmlHttp.setRequestHeader('Content-type', 'application/json');
    xmlHttp.send( null );
    console.log(xmlHttp.responseText);
    httpGetAll();
    // document.getElementById("JsonResult").innerHTML = xmlHttp.responseText;
}

// function httpDelete() {
//     $.ajax({
//         url: 'http://localhost:8080/rest/user/delete/' + $('#delete_id').val(),
//         type: 'DELETE',
//         success: function (data) {
//             console.log(data)
//             httpGetAll();
//         }
//     })

// }

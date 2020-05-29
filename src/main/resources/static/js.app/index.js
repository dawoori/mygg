function searchSummoner() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("summonerName").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "/tier/", true);
    xhttp.send();
}
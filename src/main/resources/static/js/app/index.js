const btn = document.getElementById("searchButton");
btn.addEventListener('click', searchSummoner);

const input = document.getElementById("summonerName");
input.addEventListener('keydown', function (e) {
    if (e.key === 'Enter') {
        e.preventDefault();
        console.log("enter");
        searchSummoner();
    }
}, true);

function searchSummoner() {
    const summonerName = document.getElementById("summonerName").value;
    const searchUri = "/tier/" + summonerName;
    console.log(document.location.origin + searchUri);
    window.location = document.location.origin + searchUri;
}

function eEnter(e) {
    if (e.key === 13) {
        console.log("enter");
        searchSummoner();
    }
}
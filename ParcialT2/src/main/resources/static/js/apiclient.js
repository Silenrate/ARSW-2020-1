var apiclient = (function () {
    var url='https://covid19infectedcases.herokuapp.com/infected/';
    //var url='http://localhost:8080/infected/';
    function buscar(name){
        axios({
            method: 'get',
            url: url+name,
        })
            .then(response => updateDataAndConnect(response.data.stats,name))
            .catch(error => console.log(error));
    }
    function updateDataAndConnect(data,name){
        updateData(data);
        fetch(url+name)
            .then(function(response){return response.json()})
            .then(gmap.initializeMaps(data));
    }
    function updateData(data){
        var tabla = $("table");
        var body = $("tbody");
        if(body!=null){
            body.remove();
        }
        tabla.append("<tbody>");
        var tblBody = $("tbody");
        data.map(function(place){
            var fila = '<tr><td>'+place.province+'</td><td>'+place.recovered+'</td><td>'+place.confirmed+'</td><td>'+place.deaths+'</td></tr>';
            tblBody.append(fila);
        })
        tabla.append(tblBody);
        tabla.append("</tbody>");
        var total = $("#total");
        var infected = data.reduce(function(total, value) { return total + value.confirmed; }, 0);
        total.text("Total of Infected: "+ infected);
    }
    return {
        buscar:buscar
    };
})();
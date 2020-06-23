var apiclient = (function () {
    var url='https://danielwalterosarswt2.herokuapp.com/airports/';
    //var url='http://localhost:8080/airports/';
    function buscar(name){
        //const axios = require('axios').default;
        axios({
            method: 'get',
            url: url+name,
        })
            .then(response => updateDataAndConnect(response.data,name))
            .catch(error => showError(error));//alert("There is no airports with the name "+name+" or the connection with rapidAPI"));
    }
    function showError(error){
        alert("There is no airports with that name");
    }
    function updateDataAndConnect(data,name){
        app.updateData(data);
        initMap(name);
    }
    function initMap(name){
          fetch(url+name)
            .then(function(response){return response.json()})
            .then(app.plotMarkers);
    }
    return {
        buscar:buscar,
        initMap:initMap
    };
})();
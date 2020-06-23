document.addEventListener('DOMContentLoaded', function () {
  if (document.querySelectorAll('#map').length > 0)
  {
    if (document.querySelector('html').lang)
      lang = document.querySelector('html').lang;
    else
      lang = 'en';

    var js_file = document.createElement('script');
    js_file.type = 'text/javascript';
    js_file.src = 'https://maps.googleapis.com/maps/api/js?&signed_in=true&language=' + lang;
    document.getElementsByTagName('head')[0].appendChild(js_file);
  }
});
var app = (function () {
    function buscar(name){
        clearData();
        clearMap();
        if(name==null || name==""){
            alert("The Place is Obligatory");
        }else{
            apiclient.buscar(name);
        }

    }
    function updateData(data){
        var tabla = $("table");
        var body = $("tbody");
        if(body!=null){
            body.remove();
        }
        tabla.append("<tbody>");
        var tblBody = $("tbody");
        data.map(function(aeropuerto){
            var fila = '<tr><td>'+aeropuerto.airportId+'</td><td>'+aeropuerto.name+'</td><td>'+aeropuerto.city+'</td><td>'+aeropuerto.countryCode+'</td></tr>';
            tblBody.append(fila);
        })
        tabla.append(tblBody);
        tabla.append("</tbody>");
    }
    function clearData(){
        var tabla = $("table");
        var body = $("tbody");
        if(body!=null){
            body.remove();
        }
        tabla.append("<tbody>");
        var tblBody = $("tbody");
        tabla.append(tblBody);
        tabla.append("</tbody>");
    }
    var map;



    var markers;
    var bounds;

    function plotMarkers(m){
      map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 8
      });
      markers = [];
      bounds = new google.maps.LatLngBounds();
      m.map(function (marker) {
        var mark = marker.location;
        var position = new google.maps.LatLng(mark.latitude, mark.longitude);

        markers.push(
          new google.maps.Marker({
            position: position,
            map: map,
            animation: google.maps.Animation.DROP
          })
        );

        bounds.extend(position);
      });
      map.fitBounds(bounds);
    }
    function clearMap(){
        if (markers){
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
        }
    }

    return {
        buscar:buscar,
        plotMarkers:plotMarkers,
        updateData:updateData
    };
})();
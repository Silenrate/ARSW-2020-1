document.addEventListener('DOMContentLoaded', function () {
  if (document.querySelectorAll('#map_canvas').length > 0)
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
var gmap = (function (){
    var map;
    var bounds;
    var markers;
    function initializeMaps(data) {
         map = new google.maps.Map(document.getElementById('map_canvas'), {
                center: {lat: -34.397, lng: 150.644},
                zoom: 16
         });
         markers = [];
         bounds = new google.maps.LatLngBounds();
         data.map(function(point) {
         	 	generateIcon(point.confirmed, function(src) {
         		  var pos = new google.maps.LatLng(point.latitude, point.longitude);
         	  	bounds.extend(pos);

         		  new google.maps.Marker({
         		  	position: pos,
         			  map: map,
         			  icon: src
         		  });
         		});
         	});
         map.fitBounds(bounds);
    }
    var generateIconCache = {};

    function generateIcon(number, callback) {
      if (generateIconCache[number] !== undefined) {
        callback(generateIconCache[number]);
      }

      var fontSize = 16,
        imageWidth = imageHeight = 35;

      if (number >= 1000) {
        fontSize = 10;
        imageWidth = imageHeight = 55;
      } else if (number < 1000 && number > 100) {
        fontSize = 14;
        imageWidth = imageHeight = 45;
      }

      var svg = d3.select(document.createElement('div')).append('svg')
        .attr('viewBox', '0 0 54.4 54.4')
        .append('g')

      var circles = svg.append('circle')
        .attr('cx', '27.2')
        .attr('cy', '27.2')
        .attr('r', '21.2')
        .style('fill', '#2063C6');

      var path = svg.append('path')
        .attr('d', 'M27.2,0C12.2,0,0,12.2,0,27.2s12.2,27.2,27.2,27.2s27.2-12.2,27.2-27.2S42.2,0,27.2,0z M6,27.2 C6,15.5,15.5,6,27.2,6s21.2,9.5,21.2,21.2c0,11.7-9.5,21.2-21.2,21.2S6,38.9,6,27.2z')
        .attr('fill', '#FFFFFF');

      var text = svg.append('text')
        .attr('dx', 27)
        .attr('dy', 32)
        .attr('text-anchor', 'middle')
        .attr('style', 'font-size:' + fontSize + 'px; fill: #FFFFFF; font-family: Arial, Verdana; font-weight: bold')
        .text(number);

      var svgNode = svg.node().parentNode.cloneNode(true),
        image = new Image();

      d3.select(svgNode).select('clippath').remove();

      var xmlSource = (new XMLSerializer()).serializeToString(svgNode);

      image.onload = (function(imageWidth, imageHeight) {
        var canvas = document.createElement('canvas'),
          context = canvas.getContext('2d'),
          dataURL;

        d3.select(canvas)
          .attr('width', imageWidth)
          .attr('height', imageHeight);

        context.drawImage(image, 0, 0, imageWidth, imageHeight);

        dataURL = canvas.toDataURL();
        generateIconCache[number] = dataURL;

        callback(dataURL);
      }).bind(this, imageWidth, imageHeight);

      image.src = 'data:image/svg+xml;base64,' + btoa(encodeURIComponent(xmlSource).replace(/%([0-9A-F]{2})/g, function(match, p1) {
        return String.fromCharCode('0x' + p1);
      }));
    }

    return {
        initializeMaps:initializeMaps
    };
})();
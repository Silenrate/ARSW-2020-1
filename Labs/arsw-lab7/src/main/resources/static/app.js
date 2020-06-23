var app = (function () {
    var stompClient = null;
    class Point{
        constructor(x,y){
            this.x=x;
            this.y=y;
        }
    }
    function init() {
        var canvas = document.getElementById("canvas"),
        context = canvas.getContext("2d");
        var offset  = getOffset(canvas);
        if(window.PointerEvent) {
            canvas.addEventListener("pointerdown", draw, false);
        }else {
            //Provide fallback for user agents that do not support Pointer Events
            canvas.addEventListener("mousedown", draw, false);
        }
    }
    // Event handler called for each pointerdown event:
    function draw(event) {
        var idDibujo = $('#dibujo').val();
        if(idDibujo==null || idDibujo==""){
            alert("El Identificador del Dibujo es obligatorio")
        }else{
            var canvas = document.getElementById("canvas");
            var offset  = getOffset(canvas);
            var pt=new Point(event.pageX-offset.left, event.pageY-offset.top);
            console.info("publishing point at ("+pt.x+","+pt.y+")");
            //publicar el evento
            stompClient.send("/app/newpoint."+idDibujo, {}, JSON.stringify(pt));
        }
    }
    //Helper function to get correct page offset for the Pointer coords
    function getOffset(obj) {
        var offsetLeft = 0;
        var offsetTop = 0;
        do {
            if (!isNaN(obj.offsetLeft)) {
                offsetLeft += obj.offsetLeft;
            }
            if (!isNaN(obj.offsetTop)) {
                offsetTop += obj.offsetTop;
            }
        } while(obj = obj.offsetParent );
            return {left: offsetLeft, top: offsetTop};
    }
    var addPointToCanvas = function (point) {        
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.beginPath();
        ctx.arc(point.x, point.y, 1, 0, 2 * Math.PI);
        ctx.stroke();
    };
    var getMousePosition = function (evt) {
        canvas = document.getElementById("canvas");
        var rect = canvas.getBoundingClientRect();
        return {
            x: evt.clientX - rect.left,
            y: evt.clientY - rect.top
        };
    };
    function drawPolygon(puntos){
        var canvas = document.getElementById("canvas");
        var c = canvas.getContext("2d");
        var firstPoint = puntos[0];
        c.clearRect(0, 0, canvas.width, canvas.height);
        c.beginPath();
        c.moveTo(firstPoint.x,firstPoint.y);
        puntos.map(function(punto,index){
            if(index>0){
                c.lineTo(punto.x,punto.y);
            }
        });
        c.closePath();
        c.fill();
        c.stroke();
    }
    var connectAndSubscribe = function (idDibujo) {
        console.info('Connecting to WS...');
        var socket = new SockJS('/stompendpoint');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/newpoint.'+idDibujo, function (message) {
                var punto =JSON.parse(message.body);
                addPointToCanvas(punto);
            });
            stompClient.subscribe('/topic/newpolygon.'+idDibujo, function (message) {
                var puntos =JSON.parse(message.body);
                drawPolygon(puntos);
            });
        });
        init();
    };
    return {
        draw:draw,
        conectar: function (idDibujo) {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            if(idDibujo==null || idDibujo==""){
                alert("El Identificador del Dibujo es obligatorio")
            }else{
                var canvas = document.getElementById("canvas");
                var c = canvas.getContext("2d");
                c.clearRect(0, 0, canvas.width, canvas.height);
                c.beginPath();
                //websocket connection
                connectAndSubscribe(idDibujo);
            }
        }
    };

})();
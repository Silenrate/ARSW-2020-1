var app = (function (){
    function clearBoard(){
        var bpname = $("#bpname");
        bpname.text("Current Blueprint:");
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
    }
    function updateAuthor(author){
        var name = $("#name");
        name.text(author+"'s Blueprints");
    }
    function updateName(name){
        var bpname = $("#bpname");
        bpname.text("Current Blueprint:"+name);
    }
    function updateCanvas(blueprint){
        var points = blueprint.points;
        var firstPoint = points[0];
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
        ctx.moveTo(firstPoint.x,firstPoint.y);
        points.map(function(punto,index){
            if(index>0){
                ctx.lineTo(punto.x,punto.y);
            }
        })
        ctx.stroke();
    }
    function updateData(data){
        var tabla = $("table");
        var body = $("tbody");
        if(body!=null){
            body.remove();
        }
        tabla.append("<tbody>");
        var tblBody = $("tbody");
        map(data).map(function(plano){
            var onclick = 'app.getBlueprintsByNameAndAuthor("'+plano.bpname+'")';
            var boton = "</td><td><input type='button' class='show' value='Open' onclick="+onclick+"></input></td>";
            var fila = '<tr><td>'+plano.bpname+'</td><td>' + plano.numberOfPoints + boton + '</tr>';
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
    function updateTotal(data){
        var amount = $("#total");
        var total = map(data).reduce(function(total, value) { return total + value.numberOfPoints; }, 0);
        amount.text("Total user points:"+total);
    }
    function clearTotal(){
        var amount = $("#total");
        amount.text("Total user points:0");
    }
    function map(data){
        var mapData = data.map(function(val){
            return {bpname:val.name, numberOfPoints:val.points.length};
        })
        return mapData;
    }
    function getBlueprintsByAuthor(){
        var autor = $("#autor").val();
        if(autor=="" || autor==null){
            alert("El parámetro del autor es obligatorio");
        }else{
            getBlueprintsInformation(null,autor);
            updateAuthor(autor);
        }
    }
    function getBlueprintsByNameAndAuthor(name){
        var autor = $("#autor").val();
        if(autor=="" || autor==null){
            alert("El parámetro del autor es obligatorio");
        }else if(name=="" || name==null){
            alert("El parámetro del nombre es obligatorio");
        }else{
            getBlueprintsInformation(name,autor);
            updateName(name);
        }
    }
    function getBlueprintsInformation(name,author){
            apiclient.getData(name,author);
    }
    return{
        getBlueprintsByNameAndAuthor:getBlueprintsByNameAndAuthor,
        getBlueprintsByAuthor:getBlueprintsByAuthor,
        clearTotal:clearTotal,
        clearData:clearData,
        clearBoard:clearBoard,
        updateTotal:updateTotal,
        updateData:updateData,
        updateCanvas:updateCanvas
    };
})();
var app = (function (){

    var canDraw=false;
    var authorSelected=false;
    var isCreating=false;
    var points = [];
    var selectedBlueprint = "";
    var myModule="js/apiclient.js"

    function init() {
        if ($("#createname").val()== ""){ alert("No hay un nombre especificado");}
        else if(!authorSelected){alert("No hay autor seleccionado");}
        else{
            clearBoard();
            canDraw=true;
            points = [];
            addPoints();
        }
    }
    function addPoints(){
        var canvas = document.getElementById("canvas");
        var ctx = canvas.getContext("2d");
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.beginPath();
        if(canDraw && authorSelected){
            getBlueprintName();
            var canvas = document.getElementById("canvas"),
            context = canvas.getContext("2d");
            var offset  = getOffset(canvas);
            if(window.PointerEvent) {
                canvas.addEventListener("pointerdown", draw, false);
            }else{
                canvas.addEventListener("mousedown", draw, false);
             }
        }
    }
    function getBlueprintName(){
        if(selectedBlueprint=="" && $("#createname").val()!=null){
            isCreating = true;
            selectedBlueprint = $("#createname").val();
        }
        if(selectedBlueprint=="" && $("#createname").val()==null){
            isCreating = false;
            alert("No hay un nombre especificado");
        }
    }
    function setAuthorSelected(newAuthorSelected){
        authorSelected = newAuthorSelected;
        if(authorSelected==false){
            points=[];
        }
    }
    function setCreating(nowCreating){
        isCreating = nowCreating;
    }
    function setManual(nowManual){
        isManual = nowManual;
    }
    // Event handler called for each pointerdown event:
    function draw(event) {
        if(canDraw && authorSelected){
            var canvas = document.getElementById("canvas"),
            context = canvas.getContext("2d");
            var offset  = getOffset(canvas);
            points.push({"x":event.pageX-offset.left,"y":event.pageY-offset.top});
            if(points.length>0){
                context.lineTo(event.pageX-offset.left,event.pageY-offset.top);
                context.stroke();
            }
            context.fillRect(event.pageX-offset.left, event.pageY-offset.top, 1, 1);
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
        canDraw=false;
        points=[];
        var puntos = blueprint.points;
        if(puntos.length>0){
            var firstPoint = puntos[0];
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.beginPath();
            ctx.moveTo(firstPoint.x,firstPoint.y);
            puntos.map(function(punto,index){
                if(index>0){
                    ctx.lineTo(punto.x,punto.y);
                }
            })
            ctx.stroke();
        }
    }
    function updateData(data){
        canDraw=false;
        selectedBlueprint = "";
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
        canDraw=false;
        points=[];
        selectedBlueprint = "";
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
    function updateBlueprint(author,bpname){
        if(bpname==null || bpname==""){
            throw new Error("Se debe seleccionar un plano para actualizar");
        }
        $.getScript(myModule, function(){
            api.updateBlueprint(author,bpname,points);
        });
    }
    function createBlueprint(author,bpname){
        if(bpname==null || bpname==""){
            alert("El nombre del plano es obligatorio");
        }
        $.getScript(myModule, function(){
            api.createBlueprint(author,bpname,points);
        });
    }
    function saveOrUpdate(){
        clearBoard();
        canDraw=false;
        var autor = $("#autor").val();
        if(isCreating && $("#createname").val()!= ""){
            createBlueprint(autor,selectedBlueprint);
            document.getElementById('createname').value = '';
        }
        else{
            updateBlueprint(autor,selectedBlueprint)
        }
        selectedBlueprint = "";
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
    function deleteBlueprint(){
        var isValidDelete = true;
        var autor = $("#autor").val();
        if(autor=="" || autor==null){
            isValidDelete = false;
            alert("El parámetro del autor es obligatorio");
        }
        if(isValidDelete && (selectedBlueprint==null || selectedBlueprint=="")){
            isValidDelete = false;
            alert("El nombre del plano es obligatorio");
        }
        if(isValidDelete){
            $.getScript(myModule, function(){

                api.deleteBlueprint(autor,selectedBlueprint);
            });
        }
    }
    function editBluePrint(){
        canDraw=true;
        addPoints();
    }
    function getBlueprintsByAuthor(ismanual){
        canDraw=false;
        points=[];
        var autor = $("#autor").val();
        if(autor=="" || autor==null){
            alert("El parámetro del autor es obligatorio");
        }else{
            getBlueprintsInformation(null,autor,ismanual);
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
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            ctx.beginPath();
            selectedBlueprint = name;
            getBlueprintsInformation(name,autor);
            updateName(name);
        }
    }
    function getBlueprintsInformation(name,author,ismanual){
        $.getScript(myModule, function(){
            api.getData(name,author,ismanual);
        });
    }
    return{
        getBlueprintsByNameAndAuthor:getBlueprintsByNameAndAuthor,
        getBlueprintsByAuthor:getBlueprintsByAuthor,
        clearTotal:clearTotal,
        clearData:clearData,
        clearBoard:clearBoard,
        updateTotal:updateTotal,
        updateData:updateData,
        updateCanvas:updateCanvas,
        init:init,
        draw:draw,
        editBluePrint:editBluePrint,
        setAuthorSelected:setAuthorSelected,
        setCreating:setCreating,
        setManual:setManual,
        deleteBlueprint:deleteBlueprint,
        saveOrUpdate:function(){
            var autor = $("#autor").val();
            if(autor=="" || autor==null){
                alert("El parámetro del autor es obligatorio");
            }
            else{
                const prom = new Promise((resolve,reject) => {
                    try{
                        saveOrUpdate();
                        resolve("Accion realizada exitosamente");
                    }catch(e){
                        reject(e.message);
                    }
                });
                prom.then(res =>{
                    alert(res);
                })
                .catch(error => {
                    alert(error);
                });
            }
        }
    };
})();
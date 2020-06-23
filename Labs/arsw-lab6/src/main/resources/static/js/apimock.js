var api = (function () {
    var mockdata = [];
    mockdata["JhonConnor"] = [
        {"author":"JhonConnor","name":"House","points":[{"x":250,"y":100},{"x":250,"y":200},{"x":350,"y":200},{"x":350,"y":100},{"x":250,"y":100},{"x":300,"y":50},{"x":350,"y":100}]},
        {"author":"JhonConnor","name":"Bike","points":[{"x":74,"y":55},{"x":31,"y":74}]},
        {"author":"JhonConnor","name":"Geshan","points":[{"x":70,"y":4},{"x":50,"y":38},{"x":80,"y":61}]},
        {"author":"JhonConnor","name":"Tripoli","points":[{"x":54,"y":18},{"x":85,"y":84},{"x":93,"y":55},{"x":73,"y":9}]},
        {"author":"JhonConnor","name":"Providencia","points":[{"x":33,"y":26},{"x":16,"y":98},{"x":92,"y":58}]},
        {"author":"JhonConnor","name":"Arvidsjaur","points":[{"x":35,"y":61},{"x":40,"y":45},{"x":27,"y":71},{"x":47,"y":49}]},
        {"author":"JhonConnor","name":"Bimbo","points":[{"x":53,"y":14},{"x":19,"y":88}]},
        {"author":"JhonConnor","name":"Lencois","points":[{"x":76,"y":72},{"x":76,"y":97},{"x":86,"y":54}]},
        {"author":"JhonConnor","name":"Hetang","points":[{"x":29,"y":23},{"x":35,"y":78},{"x":52,"y":13},{"x":89,"y":93},{"x":55,"y":47}]},
        {"author":"JhonConnor","name":"Masiga","points":[{"x":21,"y":77},{"x":68,"y":78}]}
    ]
    mockdata['LexLuthor'] = [
        {"author":'LexLuthor',"name":'kryptonite',"points":[{"x":60,"y":65},{"x":70,"y":75}]}
    ]
    function updateBlueprint(author,bpname,puntos){
        blueprint = mockdata[author].find(function(blueprint) {
            return blueprint.name == bpname
        });
        if(blueprint!=null){
            blueprint.points = puntos;
            app.getBlueprintsByAuthor();
        } else {
            alert("Error al actualizar el plano "+bpname);
        }
    }
    function createBlueprint(author,bpname,points){
        blueprint = mockdata[author].find(function(blueprint) {
            return blueprint.name == bpname
        });
        if(blueprint==null){
            mockdata[author].push({"author":author,"name":bpname,"points":points})
            app.getBlueprintsByAuthor();
            app.setCreating(false);
        } else {
            alert("Error al crear el plano "+bpname);
            app.setCreating(false);
        }
    }
    function deleteBlueprint(author,bpname){
        blueprint = mockdata[author].find(function(blueprint) {
            return blueprint.name == bpname
        });
        if(blueprint!=null){
            var pos = mockdata[author].indexOf(blueprint);
            mockdata[author].splice(pos,pos+1);
            alert("El plano "+bpname+" fue borrado exitosamente");
            app.getBlueprintsByAuthor(false);
            app.setManual(true);
        } else {
            alert("Error al borrar el plano:\n"+bpname+"\nEs posible que no exista");
        }
    }

    function updateData(data){
        app.updateData(data);
        app.updateTotal(data);
    }
    function clearData(){
        app.clearData();
        app.clearTotal();
    }

    function getBlueprints(error,data){
        if(error){
            alert(error);
            app.clearData();
            app.clearTotal();
        }else{
            app.updateData(data);
            app.updateTotal(data);
        }
    }
    function getBlueprint(error,data){
        if(error){
            alert(error);
        }else{
            app.updateCanvas(data);
        }
    }
    function getBlueprintsByAuthor(author,callback){
        var blueprints = mockdata[author];
        app.clearBoard();
        if(blueprints){
            app.setAuthorSelected(true);
            callback(null,mockdata[author]);
        }else{
            app.setAuthorSelected(false);
            callback("No existen planos con el autor "+author,null);
        }
    }
    function getBlueprintsByNameAndAuthor(name,author,callback){
        blueprint = mockdata[author].find(function(blueprint) {
            return blueprint.name == name
        });
        if(blueprint){
            callback(null,blueprint);
        }else{
            callback("No existe ese plano",null);
        }
    }
    return {
        updateBlueprint:updateBlueprint,
        createBlueprint:createBlueprint,
        deleteBlueprint:deleteBlueprint,
        getData: function(name,author){
            if(name!=null && author!=null){
                getBlueprintsByNameAndAuthor(name,author,getBlueprint);
            }
            else if(author != null){
                getBlueprintsByAuthor(author,getBlueprints);
            }else{
                getBlueprints("Parameters error",null);
            }
        }
    }

})();
var apiclient = (function () {
    function updateData(data){
        app.updateData(data);
        app.updateTotal(data);
    }
    function clearData(){
        app.clearData();
        app.clearTotal();
    }
    function getBlueprints(error,data){
        console.log(data);
        if(error){
            throw new Error(error);
        }
    }
    function getBlueprint(error,data){
        console.log(data);
        if(error){
            alert(error);
        }else{
            app.updateCanvas(data);
        }
    }
    function getBlueprintsByAuthor(author){
        app.clearBoard();
        var p1 = new Promise(
            function(getBlueprints, reject) {
              $.getJSON("http://localhost:8080/blueprints/"+author,function(data){
                getBlueprints(null,data);
                updateData(data);
              })
              .catch(
                function() {
                    clearData();
                    alert("No existen planos con el autor "+author);
                });

            }
        );
    }
    function getBlueprintsByNameAndAuthor(name,author){
        var p1 = new Promise(
            function(getBlueprints, reject) {
                $.getJSON("http://localhost:8080/blueprints/"+author+"/"+name,function(data){
                    getBlueprints(null,data);
                    app.updateCanvas(data);
                })
                .catch(
                    function() {
                        alert("No existe ese plano");
                    });
            }
        );
    }
    return {
        getData: function(name,author){
            if(name!=null && author!=null){
                getBlueprintsByNameAndAuthor(name,author);
            }
            else if(author != null){
                getBlueprintsByAuthor(author);
            }else{
                getBlueprints("Parameters error",null);
            }
        }
    }
})();
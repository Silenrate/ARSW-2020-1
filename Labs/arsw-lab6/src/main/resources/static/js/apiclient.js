var api = (function () {
    function updateBlueprint(author,bpname,points){
        //Promesa Forma 1
        var data =  {"author":author,"name":bpname,"points":points};
        var promise = $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/blueprints/'+autor+'/'+bpname,
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
        promise.then(
            function () {
                app.getBlueprintsByAuthor();
            },
            function () {
                alert("Error al actualizar el plano "+bpname);
            }
        );
        return promise;
        /*
        //Promesa Forma 2
        const promise = new Promise((resolve,reject) => {
            $.ajax({
                type: 'PUT',
                url: 'http://localhost:8080/blueprints/'+autor+'/School_blueprint',
                contentType: 'application/json',
                data: JSON.stringify(data), // access in body
            }).done(function () {
                resolve('SUCCESS');
            }).fail(function (msg) {
                reject('FAIL');
            });
        });
        promise.then(res =>{
            alert(res);
        })
        .catch(error => {
            alert(error);
        });
        */
    }
    function createBlueprint(author,bpname,points){
        var data =  {"author":author,"name":bpname,"points":points};
        var promise = $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/blueprints',
            contentType: 'application/json',
            data: JSON.stringify(data),
        });
        promise.then(
            function () {
                app.getBlueprintsByAuthor();
                app.setCreating(false);
            },
            function () {
                alert("Error al crear el plano "+bpname);
                app.setCreating(false);
            }
        );
        return promise;
    }
    function deleteBlueprint(author,bpname){
        var promise = $.ajax({
            url: 'http://localhost:8080/blueprints/'+author+'/'+bpname,
            type: 'DELETE'
        });
        promise.then(
            function () {
                alert("El plano "+bpname+" fue borrado exitosamente");
                app.getBlueprintsByAuthor(false);
                app.setManual(true);
            },
            function () {
                alert("Error al borrar el plano:\n"+bpname+"\nEs posible que no exista");
            }
        );
        return promise;
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
            throw new Error(error);
        }
    }
    function getBlueprint(error,data){
        if(error){
            alert(error);
        }else{
            app.updateCanvas(data);
        }
    }
    function getBlueprintsByAuthor(author,isManual){
        app.clearBoard();
        var promise = $.getJSON("http://localhost:8080/blueprints/"+author);
        promise.then(
            function (data) {
                getBlueprints(null,data);
                app.setAuthorSelected(true);
                updateData(data);
            },
            function () {
                app.setAuthorSelected(false);
                clearData();
                if(isManual==true){
                    alert("No existen planos con el autor "+author);
                }
            }
        );
        return promise;
    }
    function getBlueprintsByNameAndAuthor(name,author){
        var promise = $.getJSON("http://localhost:8080/blueprints/"+author+"/"+name);
        promise.then(
            function (data) {
                getBlueprints(null,data);
                app.updateCanvas(data);
            },
            function () {
                alert("No existe ese plano");
            }
        );
        return promise;
    }
    return {
        updateBlueprint:updateBlueprint,
        createBlueprint:createBlueprint,
        deleteBlueprint:deleteBlueprint,
        getData: function(name,author,isManual){
            if(name!=null && author!=null){
                getBlueprintsByNameAndAuthor(name,author);
            }
            else if(author != null){
                getBlueprintsByAuthor(author,isManual);
            }else{
                getBlueprints("Parameters error",null);
            }
        }
    }
})();
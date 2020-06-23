var app = (function () {
    function buscar(name){
        clearData();
        if(name==null || name==""){
            alert("The Country is Obligatory");
        }else{
            apiclient.buscar(name);
        }
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
    return {
        buscar:buscar,
    };
})();
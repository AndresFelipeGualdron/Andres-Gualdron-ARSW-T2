var module = (function(){

    paises = [];





    obtenerPaisesCorrecto = function(data) {
        console.log(data);
        paises = data;
        data.forEach(pais => {
            console.log(pais);
            $("#contenidoPaises").append("<td>"+pais.nombre+"</td>");
            $("#contenidoPaises").append("<td>"+pais.muertos+"</td>");
            $("#contenidoPaises").append("<td>"+pais.contagiados+"</td>");
            $("#contenidoPaises").append("<td>"+pais.recuperados+"</td>");
        })
    }

    obtenerPaisesIncorrecto = function(error) {
        console.log(error);
    }

    return {
        init: function(){
            var url = '/countries';
            apiclient.consultarPaises(obtenerPaisesCorrecto,obtenerPaisesIncorrecto, url);
        }
    }
})();
var module = (function(){

    paises = [];


    clickPais = function(nombre){
        console.log(nombre);
    }


    obtenerPaisesCorrecto = function(data) {
        paises = data;
        data.forEach(pais => {
            $("#contenidoPaises").append("<tr onclick='module.clickPais(\""+pais.nombre+"\")' class='seleccionarPais'> <td>"+pais.nombre+"</td><td>"+pais.muertos+"</td><td>"+pais.contagiados+"</td><td>"+pais.recuperados+"</td></tr>");
        })
    }

    obtenerPaisesIncorrecto = function(error) {
        console.log(error);
    }

    return {
        init: function(){
            var url = '/countries';
            apiclient.consultarPaises(obtenerPaisesCorrecto,obtenerPaisesIncorrecto, url);
        },
        clickPais : clickPais
    }
})();
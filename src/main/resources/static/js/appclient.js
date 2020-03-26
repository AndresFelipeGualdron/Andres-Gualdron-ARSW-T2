var apiclient = (function(){

    function consultarPaises(correcto, incorrecto, url){
        $.ajax({
            type: 'GET',
            url: url,
            success : function(data) {
                correcto(data);
            },
            error : function(error){
                incorrecto(error)
            }
        });
    }

    function getUbicacion(nombre, correcto, incorrecto){
        $.ajax({
            type: "GET",
            url: "https://restcountries-v1.p.rapidapi.com/name/"+nombre,
            headers: {
                'x-rapidapi-host': 'restcountries-v1.p.rapidapi.com',
                'x-rapidapi-key' : '6b4a736760msh2090ce99f78b9b3p1f43b2jsn8f383dbae7be'
            },
            success: function(data){
                correcto(data, nombre);
            },
            error: function (error) {
                incorrecto(error);
            }
        });
    }


    return {
        consultarPaises : consultarPaises,
        getUbicacion : getUbicacion
    }
})();
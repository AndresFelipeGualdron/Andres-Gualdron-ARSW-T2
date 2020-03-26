var apiclient = (function(){

    function consultarPaises(correcto, incorrecto, url){
        $.ajax({
            type: 'GET',
            url: url,
            headers: {
                'x-rapidapi-host': 'covid-19-coronavirus-statistics.p.rapidapi.com',
                'x-rapidapi-key':'6b4a736760msh2090ce99f78b9b3p1f43b2jsn8f383dbae7be'
            },
            success : function(data) {
                correcto(data);
            },
            error : function(error){
                incorrecto(error)
            }
        });
    }


    return {
        consultarPaises : consultarPaises
    }
})();
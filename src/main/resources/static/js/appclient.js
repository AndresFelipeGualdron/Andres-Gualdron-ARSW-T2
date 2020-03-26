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


    return {
        consultarPaises : consultarPaises
    }
})();
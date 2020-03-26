var module = (function(){





    obtenerPaisesCorrecto = function(data) {
        console.log(data);
    }

    obtenerPaisesIncorrecto = function(error) {
        console.log(error);
    }

    return {
        init: function(){
            var url = 'https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats';
            apiclient.consultarPaises(obtenerPaisesCorrecto,obtenerPaisesIncorrecto, url);
        }
    }
})();
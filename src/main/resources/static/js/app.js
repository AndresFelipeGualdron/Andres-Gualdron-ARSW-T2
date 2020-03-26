var module = (function(){





    obtenerPaisesCorrecto = function(data) {
        console.log(data);
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
document.addEventListener('DOMContentLoaded', function () {
    if (document.querySelectorAll('#map').length > 0)
    {
        if (document.querySelector('html').lang)
            lang = document.querySelector('html').lang;
        else
            lang = 'en';

        var js_file = document.createElement('script');
        js_file.type = 'text/javascript';
        js_file.src = 'https://maps.googleapis.com/maps/api/js?callback=initMap&signed_in=true&language=' + lang;
        document.getElementsByTagName('head')[0].appendChild(js_file);
    }
});

var map;

function initMap()
{
    map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: -34.397, lng: 150.644},
        zoom: 8
    });

}

var markers;
var bounds;

function plotMarkers(m)
{
    initMap();
    markers = [];
    bounds = new google.maps.LatLngBounds();

    m.forEach(function (marker) {
        var position = new google.maps.LatLng(marker.latlng[0], marker.latlng[1]);

        markers.push(
            new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            })
        );

        bounds.extend(position);
    });

    map.fitBounds(bounds);
}


var module = (function(){

    paises = [];

    pais = {};

    correctoUbicacion = function(data, nombreCorrecto){
        console.log(data);
        d = data.filter(country => {
            if(country.name == nombreCorrecto || country.alpha2Code == nombreCorrecto || country.alpha3Code == nombreCorrecto) return country;
        });
        pais = d[0];
        ubicacion = {
            lat : pais.latlng[0],
            lng : pais.latlng[1]
        };
    }

    incorrectoUbicacion = function(error){
        console.log(error);
    }


    clickPais = function(nombre){
        console.log(nombre);
        apiclient.getUbicacion(nombre, correctoUbicacion, incorrectoUbicacion);
    }


    obtenerPaisesCorrecto = function(data) {
        paises = data;
        data.forEach(pais => {
            $("#contenidoPaises").append("<tr onclick='module.clickPais(\""+pais.nombre+"\")' class='seleccionarPais'> <td>"+pais.nombre+"</td><td>"+pais.muertos+"</td><td>"+pais.contagiados+"</td><td>"+pais.recuperados+"</td></tr>");
        });
    }

    obtenerPaisesIncorrecto = function(error) {
        console.log(error);
    }

    print = function(nombre){
        plotMarkers(nombre)
    }

    return {
        init: function(){
            var url = '/countries';
            apiclient.consultarPaises(obtenerPaisesCorrecto,obtenerPaisesIncorrecto, url);
        },
        clickPais : clickPais,
        print: print
    }
})();
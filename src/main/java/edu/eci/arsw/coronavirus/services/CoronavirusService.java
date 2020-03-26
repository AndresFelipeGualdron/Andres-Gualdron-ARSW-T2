package edu.eci.arsw.coronavirus.services;


import edu.eci.arsw.coronavirus.model.Country;

import java.util.List;

public interface CoronavirusService {

    List<Country> obtenerPaises();

    Country obtenerPais(String  nombre);

}

package edu.eci.arsw.coronavirus.persistencia;


import edu.eci.arsw.coronavirus.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Persistencia {

    @Autowired
    ActualizaService actualizaService;


    public List<Country> obtenerPaises(){
        return actualizaService.getPaises();
    }

    public Country obtenerPais(String pais){
        List<Country> lista = actualizaService.getPaises();
        AtomicReference<Country> country = new AtomicReference<>(new Country());
        lista.forEach(item -> {
            if(item.getNombre().equals(pais)) country.set(item);
        });
        return country.get();
    }

    
}

package edu.eci.arsw.coronavirus.persistencia;


import edu.eci.arsw.coronavirus.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Persistencia {

    @Autowired
    ActualizaService actualizaService;

    public List<Country> obtenerPaises(){
        return actualizaService.getPaises();
    }

    
}

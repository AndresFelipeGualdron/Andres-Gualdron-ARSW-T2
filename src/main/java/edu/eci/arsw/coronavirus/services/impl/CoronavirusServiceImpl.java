package edu.eci.arsw.coronavirus.services.impl;

import edu.eci.arsw.coronavirus.model.Country;
import edu.eci.arsw.coronavirus.persistencia.Persistencia;
import edu.eci.arsw.coronavirus.services.CoronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoronavirusServiceImpl implements CoronavirusService {

    @Autowired
    Persistencia persistencia;

    @Override
    public List<Country> obtenerPaises() {
        return persistencia.obtenerPaises();
    }
}

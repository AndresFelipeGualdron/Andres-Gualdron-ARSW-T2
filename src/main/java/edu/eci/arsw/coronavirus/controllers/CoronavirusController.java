package edu.eci.arsw.coronavirus.controllers;

import edu.eci.arsw.coronavirus.services.CoronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoronavirusController {

    @Autowired
    CoronavirusService coronavirusService;

    @GetMapping("/countries")
    public ResponseEntity<?> obtenerPaises(){
        return new ResponseEntity<>(coronavirusService.obtenerPaises(), HttpStatus.OK);
    }

    @GetMapping("/country/{name}")
    public ResponseEntity<?> obtenerPais(@PathVariable("name") String name){
        return new ResponseEntity<>(coronavirusService.obtenerPais(name), HttpStatus.OK);
    }


}

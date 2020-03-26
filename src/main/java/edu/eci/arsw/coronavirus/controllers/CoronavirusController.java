package edu.eci.arsw.coronavirus.controllers;

import edu.eci.arsw.coronavirus.services.CoronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blueprints")
public class CoronavirusController {

    @Autowired
    CoronavirusService coronavirusService;


}

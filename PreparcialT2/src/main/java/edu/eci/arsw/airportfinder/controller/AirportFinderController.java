package edu.eci.arsw.airportfinder.controller;

import edu.eci.arsw.airportfinder.service.AirportFinderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador API REST de la aplicacion AirportFinder
 */
@RestController
@RequestMapping(value = "/airports")
public class AirportFinderController {

    @Autowired
    AirportFinderServices afs = null;

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     *
     * @param name El nombre de los aeropuertos a buscar
     * @return Una entidad de respuesta
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> GetAirportsByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(afs.getAirportsByName(name), HttpStatus.ACCEPTED);
        } catch (AirportFinderException e) {
            Logger.getLogger(AirportFinderController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


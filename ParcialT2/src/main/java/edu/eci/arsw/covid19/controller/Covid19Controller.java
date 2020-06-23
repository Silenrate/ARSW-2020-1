package edu.eci.arsw.covid19.controller;

import edu.eci.arsw.covid19.service.Covid19Services;
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
 * Controlador API REST de la aplicacion Covid19 Cases Tool
 */
@RestController
@RequestMapping(value = "/infected")
public class Covid19Controller {

    @Autowired
    Covid19Services cfs = null;

    /**
     * Obtiene los casos de covid19 de un pais
     * @param country Nombre del pais
     * @return Una entidad de respuesta con respecto al resulatdo del servicio, 202 ACCEPTED si existe y 404 NOT FOUND si no
     */
    @RequestMapping(value = "/{country}",method = RequestMethod.GET)
    public ResponseEntity<?> getCasesByCountry(@PathVariable("country") String country) {
        try {
            return new ResponseEntity<>(cfs.getCovid19FromCountry(country), HttpStatus.ACCEPTED);
        } catch (Covid19Exception e) {
            Logger.getLogger(Covid19Controller.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}


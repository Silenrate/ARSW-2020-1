package edu.eci.arsw.airportfinder.service;

import edu.eci.arsw.airportfinder.controller.AirportFinderException;

/**
 * Interfaz de los servicios de la aplicacion AirportFinder
 */
public interface AirportFinderServices {
    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    String getAirportsByName(String name) throws AirportFinderException;
}

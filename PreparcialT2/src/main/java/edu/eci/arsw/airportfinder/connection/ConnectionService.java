package edu.eci.arsw.airportfinder.connection;

import edu.eci.arsw.airportfinder.controller.AirportFinderException;

/**
 * Interfaz de la conexion con un tercer aplicativo
 */
public interface ConnectionService {

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    String getAirportsByName(String name) throws AirportFinderException;
}

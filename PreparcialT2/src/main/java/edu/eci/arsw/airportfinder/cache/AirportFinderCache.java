package edu.eci.arsw.airportfinder.cache;

import edu.eci.arsw.airportfinder.controller.AirportFinderException;

/**
 * Interfaz del cache de aeropuertos
 */
public interface AirportFinderCache {
    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    String getAirportsByName(String name) throws AirportFinderException;

    /**
     * Agrega al cache un aeropuerto con su respectivo nombre
     * @param name El nombre de los aeropuertos
     * @param airport Los aeropuertos que tienen ese nombre
     * @throws AirportFinderException - Cuando ya existen esos aeropuertos con ese nombre
     */
    void putAirportInCache(String name,String airport) throws AirportFinderException;

    /**
     * Elimina los aeropuertos con cierto nombre del cache
     * @param name El nombre de los aeropuertos
     * @throws AirportFinderException
     */
    void cleanAirportCache(String name) throws AirportFinderException;

    /**
     * Averigua si existen en el cache aeropuertos con un nombre
     * @param name El nombre de los aeropuertos
     * @return El valor booleano que determina si existe en el cache aeropuertos con ese nombre
     */
    boolean isAirportInCache(String name);

}

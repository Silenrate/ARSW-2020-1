package edu.eci.arsw.airportfinder.cache;

import edu.eci.arsw.airportfinder.controller.AirportFinderException;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que implementa la Interfaz del cache de aeropuertos
 */
@Service
public class AirportFinderCacheImpl implements AirportFinderCache {

    private ConcurrentHashMap<String, String> airports = new ConcurrentHashMap<>();

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    @Override
    public String getAirportsByName(String name) throws AirportFinderException {
        //System.out.println(airports);
        String airport = airports.get(name);
        if (airport == null) {
            throw new AirportFinderException("No se encuentra ese aeropuerto en cache");
        }
        return airport;
    }

    /**
     * Averigua si existen en el cache aeropuertos con un nombre
     * @param name El nombre de los aeropuertos
     * @return El valor booleano que determina si existe en el cache aeropuertos con ese nombre
     */
    @Override
    public boolean isAirportInCache(String name) {
        return airports.containsKey(name);
    }

    /**
     * Agrega al cache un aeropuerto con su respectivo nombre
     * @param name El nombre de los aeropuertos
     * @param airport Los aeropuertos que tienen ese nombre
     * @throws AirportFinderException - Cuando ya existen esos aeropuertos con ese nombre
     */
    @Override
    public void putAirportInCache(String name, String airport) throws AirportFinderException {
        if (isAirportInCache(name)) {
            throw new AirportFinderException("Ese aeropuerto ya esta en cache");
        }
        airports.put(name, airport);
    }

    /**
     * Elimina los aeropuertos con cierto nombre del cache
     * @param name El nombre de los aeropuertos
     * @throws AirportFinderException
     */
    @Override
    public void cleanAirportCache(String name) throws AirportFinderException {
        if (isAirportInCache(name)) {
            airports.remove(name);
        } else {
            throw new AirportFinderException("Ese aeropuerto no existe en cache");
        }
    }
}

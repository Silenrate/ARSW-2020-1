package edu.eci.arsw.airportfinder.service;

import edu.eci.arsw.airportfinder.cache.AirportFinderCache;
import edu.eci.arsw.airportfinder.concurrent.AirportFinderThread;
import edu.eci.arsw.airportfinder.connection.ConnectionService;
import edu.eci.arsw.airportfinder.controller.AirportFinderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clse que implementa la Interfaz de los servicios de la aplicacion AirportFinder
 */
@Service
public class AirportFinderServicesImpl implements AirportFinderServices {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private AirportFinderCache airportFinderCache;

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    @Override
    public String getAirportsByName(String name) throws AirportFinderException {
        String airports = null;
        try {
            if (airportFinderCache.isAirportInCache(name)) {
                //System.out.println("Entre al cache");
                airports = airportFinderCache.getAirportsByName(name);
            } else {
                //System.out.println("Hice la conexion");
                airports = connectionService.getAirportsByName(name);
                airportFinderCache.putAirportInCache(name, airports);
                AirportFinderThread nuevoThread = new AirportFinderThread(name, airportFinderCache);
                nuevoThread.start();
            }
        } catch (AirportFinderException e) {
            throw new AirportFinderException("Error al obtener aeropuertos",e);
        }
        //System.out.println(airports);
        return airports;
    }
}

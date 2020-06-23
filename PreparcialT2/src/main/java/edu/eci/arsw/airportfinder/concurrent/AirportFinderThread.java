package edu.eci.arsw.airportfinder.concurrent;

import edu.eci.arsw.airportfinder.cache.AirportFinderCache;
import edu.eci.arsw.airportfinder.controller.AirportFinderException;

public class AirportFinderThread extends Thread {

    //Tiempo de espera para borrar un dato del cache
    public static final long timeToClear = 300000;
    private String name;
    private AirportFinderCache airportFinderCache;

    /**
     * Consturctor de la clase AirportFinderThread
     * @param name El nombre de los aeropuertos
     * @param airportFinderCache El cache donde esta guardado el valor de los aeropuertos
     */
    public AirportFinderThread(String name, AirportFinderCache airportFinderCache) {
        super();
        this.name = name;
        this.airportFinderCache = airportFinderCache;
    }

    /**
     * Espera el tiempo determinado y luego borra el valor de los aeropuertos del cache
     */
    @Override
    public void run() {
        //System.out.println("Iniciando Thread....");
        try {
            Thread.sleep(timeToClear);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("Borrando elemento "+name);
        try {
            airportFinderCache.cleanAirportCache(name);
        } catch (AirportFinderException e) {
            e.printStackTrace();
        }
    }
}

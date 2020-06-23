package edu.eci.arsw.airportfinder.controller;

/**
 * Excepcioon Personalizada de la busqueda de aeropuertos
 */
public class AirportFinderException extends Exception {
    /**
     * Constructor de AirportFinderException
     * @param msg El mensaje de la excepcion
     */
    public AirportFinderException(String msg){
        super(msg);
    }

    /**
     * Constructor de AirportFinderException
     * @param msg El mensaje de la excepcion
     * @param cause La causa de la excepcion
     */
    public AirportFinderException(String msg, Throwable cause){
        super(msg,cause);
    }
}

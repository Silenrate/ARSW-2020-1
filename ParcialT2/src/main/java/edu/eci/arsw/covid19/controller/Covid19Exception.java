package edu.eci.arsw.covid19.controller;

/**
 * Excepcion Personalizada de la busqueda de infectados por el covid19
 */
public class Covid19Exception extends Exception {
    /**
     * Constructor de covid19Exception
     * @param msg El mensaje de la excepcion
     */
    public Covid19Exception(String msg){
        super(msg);
    }

    /**
     * Constructor de covid19Exception
     * @param msg El mensaje de la excepcion
     * @param cause La causa de la excepcion
     */
    public Covid19Exception(String msg, Throwable cause){
        super(msg,cause);
    }
}

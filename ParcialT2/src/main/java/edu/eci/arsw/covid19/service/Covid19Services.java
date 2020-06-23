package edu.eci.arsw.covid19.service;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;

/**
 * Interfaz de los servicios de la aplicacion Covid19 Cases Tool
 */
public interface Covid19Services {
    /**
     * Obtiene los casos de infectados de covid19 por pais
     * @param country Nombre del pais
     * @return Un string de los casos de infectados de covid19 por pais
     * @throws Covid19Exception - Cusndo hay error de conexion o no existe ese pais
     */
    Covid19ByCountry getCovid19FromCountry(String country) throws Covid19Exception;
}

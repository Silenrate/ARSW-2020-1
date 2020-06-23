package edu.eci.arsw.covid19.connection;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;

/**
 * Interfaz de la conexion con un tercer aplicativo
 */
public interface ConnectionCovidService {

    Covid19ByCountry getCaseByCountry(String country) throws Covid19Exception;
}

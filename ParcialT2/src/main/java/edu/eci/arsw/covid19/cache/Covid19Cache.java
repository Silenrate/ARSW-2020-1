package edu.eci.arsw.covid19.cache;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;

/**
 * Interfaz del cache de casos del covid19
 */
public interface Covid19Cache {
    /**
     * Obtiene los casos de covid19 de un pais especifico
     * @param country El nombre del pais a buscar
     * @return Un string de los casos de covid19
     * @throws Covid19Exception - Cuando no existe ese pais
     */
    Covid19ByCountry getCovid19FromCountry(String country) throws Covid19Exception;

    /**
     * Agrega al cache los casos de covid19 de un pais
     * @param country El nombre del pais
     * @param covid19ByCountry Los casos de cpvid19 del pais
     * @throws Covid19Exception - Cuando ya existen casos de covid19 del pais en el cache
     */
    void putCaseOfCountryInCache(String country, Covid19ByCountry covid19ByCountry) throws Covid19Exception;

    /**
     * Elimina los casos de covid19 de un pais
     * @param country El nombre del pais
     * @throws Covid19Exception - Cuando no existe ese pais en el cache
     */
    void cleanCaseOfCache(String country) throws Covid19Exception;

    /**
     * Averigua si existen en el cache casos de covid19 de un pais
     * @param country El nombre del pais
     * @return El valor booleano que determina si existe en el cache casos de covid19 de un pais
     * @throws Covid19Exception - Si hubo un error al validar la existencia de ese caso en cache
     */
    boolean isCountryInCache(String country) throws Covid19Exception;

}

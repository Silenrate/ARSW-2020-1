package edu.eci.arsw.covid19.cache;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que implementa la Interfaz del cache de casos de covid19
 */
@Service
public class Covid19CacheImpl implements Covid19Cache {

    private static final long MINUTES_IN_CACHE = 5;
    private ConcurrentHashMap<String, Covid19ByCountry> casos = new ConcurrentHashMap<>();

    /**
     * Obtiene los casos de covid19 de un pais especifico
     *
     * @param country El nombre del pais a buscar
     * @return Un string de los casos de covid19
     * @throws Covid19Exception - Cuando no existe ese pais
     */
    @Override
    public Covid19ByCountry getCovid19FromCountry(String country) throws Covid19Exception {
        Covid19ByCountry caso = casos.get(country);
        if (caso == null) {
            throw new Covid19Exception("No se encuentra ese caso en cache");
        }
        return caso;
    }

    /**
     * Averigua si existen en el cache casos de covid19 de un pais
     *
     * @param country El nombre del pais
     * @return El valor booleano que determina si existe en el cache casos de covid19 de un pais
     */
    @Override
    public boolean isCountryInCache(String country) throws Covid19Exception {
        Covid19ByCountry caso = casos.get(country);
        boolean isInCache = true;
        if (caso == null) {
            isInCache = false;
        } else if (LocalDateTime.now().isAfter(caso.getTime().plusMinutes(MINUTES_IN_CACHE))) {
            cleanCaseOfCache(country);
            isInCache = false;
        }
        return isInCache;
    }

    /**
     * Agrega al cache los casos de covid19 de un pais
     *
     * @param country          El nombre del pais
     * @param covid19ByCountry Los casos de cpvid19 del pais
     * @throws Covid19Exception - Cuando ya existen casos de covid19 del pais en el cache
     */
    @Override
    public void putCaseOfCountryInCache(String country, Covid19ByCountry covid19ByCountry) throws Covid19Exception {
        if (isCountryInCache(country)) {
            throw new Covid19Exception("Ese pais ya esta en cache");
        }
        casos.put(country, covid19ByCountry);
    }

    /**
     * Elimina los casos de covid19 de un pais
     *
     * @param country El nombre del pais
     * @throws Covid19Exception - Cuando no existe ese pais en el cache
     */
    @Override
    public void cleanCaseOfCache(String country) throws Covid19Exception {
        casos.remove(country);
    }
}

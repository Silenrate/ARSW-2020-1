package edu.eci.arsw.covid19.service;

import edu.eci.arsw.covid19.cache.Covid19Cache;
import edu.eci.arsw.covid19.connection.ConnectionCoordinatesService;
import edu.eci.arsw.covid19.connection.ConnectionCovidService;
import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;
import edu.eci.arsw.covid19.model.Stat;
import edu.eci.arsw.covid19.model.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Clse que implementa la Interfaz de los servicios de la aplicacion AirportFinder
 */
@Service
public class Covid19ServicesImpl implements Covid19Services {

    @Autowired
    private ConnectionCovidService connectioncovidService;

    @Autowired
    private ConnectionCoordinatesService connectionCoordinatesService;

    @Autowired
    private Covid19Cache covid19Cache;

    /**
     * Obtiene los casos de infectados de covid19 por pais
     *
     * @return Un string de los casos de infectados de covid19 por pais
     * @throws Covid19Exception - Cusndo hay error de conexion o no existe ese pais
     */
    @Override
    public Covid19ByCountry getCovid19FromCountry(String country) throws Covid19Exception {
        Covid19ByCountry covid19ByCountry;
        try {
            if (covid19Cache.isCountryInCache(country)) {
                covid19ByCountry = covid19Cache.getCovid19FromCountry(country);
            } else {
                covid19ByCountry = connectioncovidService.getCaseByCountry(country);
                for (Stat stat : covid19ByCountry.getStats()) {
                    String place = stat.getProvince();
                    if ("".equals(place)) {
                        place = country;
                    } else {
                        place += ", " + country;
                    }
                    Pair<Long, Long> coordinates = connectionCoordinatesService.getCoordinatesOfPlace(place);
                    stat.setLongitude(coordinates.getKey());
                    stat.setLatitude(coordinates.getValue());
                }
                covid19Cache.putCaseOfCountryInCache(country, covid19ByCountry);
            }
        } catch (Covid19Exception e) {
            throw new Covid19Exception("Error al obtener casos", e);
        }
        //System.out.println(covid19ByCountry);
        return covid19ByCountry;
    }


}

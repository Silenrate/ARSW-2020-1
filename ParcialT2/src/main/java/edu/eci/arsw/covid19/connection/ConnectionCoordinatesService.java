package edu.eci.arsw.covid19.connection;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Pair;

/**
 * Interfaz de servicios de obtencion de coordenadas de un lugar
 */
public interface ConnectionCoordinatesService {
    /**
     * Obtiene las coordenas de un lugar
     * @param place Lugar a buscar
     * @return Un par con la longotud y latitud del lugar
     * @throws Covid19Exception - Cuando hubo un error de conexon o no existe ese lugar
     */
    Pair<Long, Long> getCoordinatesOfPlace(String place) throws Covid19Exception;
}

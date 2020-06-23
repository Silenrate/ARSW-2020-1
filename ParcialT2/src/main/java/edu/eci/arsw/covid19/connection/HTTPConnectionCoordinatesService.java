package edu.eci.arsw.covid19.connection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Pair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la interfaz de servicios de obtencion de coordenadas de un lugar
 */
@Service
public class HTTPConnectionCoordinatesService implements ConnectionCoordinatesService, URLparser {

    private String url;
    private String key;

    /**
     * Constructor de la conexion por HTTP
     */
    public HTTPConnectionCoordinatesService() {
        url = "https://api.opencagedata.com/geocode/v1/json?q=";
        key = "609f13ebeac04c6d834a4deb7c9b751a";
    }

    /**
     * Obtiene las coordenas de un lugar
     *
     * @param place Lugar a buscar
     * @return Un par con la longotud y latitud del lugar
     * @throws Covid19Exception - Cuando hubo un error de conexon o no existe ese lugar
     */
    @Override
    public Pair<Long, Long> getCoordinatesOfPlace(String place) throws Covid19Exception {
        HttpResponse<JsonNode> response;
        JSONObject myObj;
        JSONArray results;
        JSONObject coordinates;
        try {
            response = Unirest.get(url + getStringInUrl(place) + "&key=" + key)
                    .asJson();
            myObj = response.getBody().getObject();
            results = myObj.getJSONArray("results");
            coordinates = ((JSONObject) results.get(0)).getJSONObject("geometry");
        } catch (UnirestException e) {
            throw new Covid19Exception("Error de conexion con RapidAPI");
        } catch (JSONException ex) {
            throw new Covid19Exception("No existe el lugar con casos de covid19");
        }
        return new Pair<>(coordinates.getLong("lng"), coordinates.getLong("lat"));
    }
}

package edu.eci.arsw.covid19.connection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;
import edu.eci.arsw.covid19.model.Stat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la Interfaz de la conexion con un tercer aplicativo
 */
@Service
public class HttpConnectionCovidService implements ConnectionCovidService,URLparser {

    private String url;
    private String host;
    private String key;

    /**
     * Constructor de la conexion por HTTP
     */
    public HttpConnectionCovidService() {
        url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=";
        host = "covid-19-coronavirus-statistics.p.rapidapi.com";
        key = "128072f538mshaaf72ff3c2156b6p1fe46djsn7892b4c14f51";
    }

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     *
     * @param country El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws Covid19Exception - Cuando no existen aeropuertos con ese nombre
     */
    @Override
    public Covid19ByCountry getCaseByCountry(String country) throws Covid19Exception {
        HttpResponse<JsonNode> response;
        try {
            response = Unirest.get(url + getStringInUrl(country))
                    .header("x-rapidapi-host", host)
                    .header("x-rapidapi-key", key)
                    .asJson();
        } catch (UnirestException e) {
            throw new Covid19Exception("Error de conexion con RapidAPI");
        }
        JSONObject myObj = response.getBody().getObject();
        if (myObj.getString("message").equals("Country not found. Returning all stats. Please use a country name found in the data property.")) {
            throw new Covid19Exception("No existe el pais " + country);
        }
        JSONArray stats = myObj.getJSONObject("data").getJSONArray("covid19Stats");
        List<Stat> statsByCountry = new ArrayList<>();
        for (int i = 0; i < stats.length(); i++) {
            JSONObject province = stats.getJSONObject(i);
            statsByCountry.add(new Stat(province.getString("province"), province.getInt("recovered"), province.getInt("confirmed"), province.getInt("deaths")));
        }
        return new Covid19ByCountry(statsByCountry, country);
    }

}

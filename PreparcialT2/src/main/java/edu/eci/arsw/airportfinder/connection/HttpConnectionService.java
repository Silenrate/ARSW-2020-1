/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.airportfinder.connection;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.airportfinder.controller.AirportFinderException;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

/**
 * Clase que implementa la Interfaz de la conexion con un tercer aplicativo
 */
@Service
public class HttpConnectionService implements ConnectionService {

    private String url;
    private String host;
    private String key;

    /**
     * Constructor de la conexion por HTTP
     */
    public HttpConnectionService() {
        url = "https://cometari-airportsfinder-v1.p.rapidapi.com/api/airports/by-text?text=";
        host = "cometari-airportsfinder-v1.p.rapidapi.com";
        key = "34f05cff54msh30ba6f36c91c183p166499jsn555917ef62b8";
    }

    /**
     * Obtiene los aeropuertos que tienen cierto nombre
     * @param name El nombre de los aeropuertos a buscar
     * @return Un string de los aeropuertos a buscar
     * @throws AirportFinderException - Cuando no existen aeropuertos con ese nombre
     */
    @Override
    public String getAirportsByName(String name) throws AirportFinderException {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url + name)
                    .header("x-rapidapi-host", host)
                    .header("x-rapidapi-key", key)
                    .asJson();
        } catch (UnirestException e) {
            throw new AirportFinderException("Error de conexion con RapidAPI");
        }
        //System.out.println(response.getBody());
        JSONArray myObj = response.getBody().getArray();
        if(myObj.length()==0){
            throw new AirportFinderException("No existen aeropuertos con ese nombre");
        }
        return myObj.toString();
    }

}

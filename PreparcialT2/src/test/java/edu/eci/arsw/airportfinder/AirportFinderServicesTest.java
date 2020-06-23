package edu.eci.arsw.airportfinder;

import edu.eci.arsw.airportfinder.controller.AirportFinderException;
import edu.eci.arsw.airportfinder.service.AirportFinderServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportFinderServicesTest {

    @Autowired
    AirportFinderServices airportFinderServices;

    @Test
    public void shouldGetAirportsByName() throws AirportFinderException {
        String response = airportFinderServices.getAirportsByName("London");
        assertNotNull(response);
        assertTrue(response.contains("London"));
    }

    @Test
    public void shouldNotGetAirportsByNameIfDoesntExist(){
        try {
            String response = airportFinderServices.getAirportsByName("galleta");
            fail("Debio fallar por consultar aeropuertos por un nombre inexistente");
        } catch (AirportFinderException e) {
            assertEquals("Error al obtener aeropuertos",e.getMessage());
        }
    }
}

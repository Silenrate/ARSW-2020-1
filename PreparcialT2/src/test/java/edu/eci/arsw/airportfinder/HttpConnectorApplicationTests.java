package edu.eci.arsw.airportfinder;

import edu.eci.arsw.airportfinder.connection.ConnectionService;
import edu.eci.arsw.airportfinder.controller.AirportFinderException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpConnectorApplicationTests {

	@Autowired
	private ConnectionService connectionService;

	@Test
	public void shouldGetAirportsByName() throws AirportFinderException {
		String response = connectionService.getAirportsByName("London");
		assertNotNull(response);
		assertTrue(response.contains("London"));
	}

	@Test
	public void shouldNotGetAirportsByNameIfDoesntExist(){
		try {
			String response = connectionService.getAirportsByName("galleta");
			fail("Debio fallar por consultar aeropuertos por un nombre inexistente");
		} catch (AirportFinderException e) {
			assertEquals("No existen aeropuertos con ese nombre",e.getMessage());
		}
	}

}

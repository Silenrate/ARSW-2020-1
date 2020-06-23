package edu.eci.arsw.covid19;

import edu.eci.arsw.covid19.connection.ConnectionCovidService;
import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpConnectorApplicationTests {


    @Autowired
    private ConnectionCovidService connectionCovidService;


    @Test
    public void shouldGetCovid19CasesByCountryName() throws Covid19Exception {
        Covid19ByCountry response = connectionCovidService.getCaseByCountry("Colombia");
        assertNotNull(response);
        assertEquals("Colombia", response.getCountry());
    }

    @Test
    public void shouldNotGetCovid19CasesByCountryNameIfDoesntExist() {
        try {
            connectionCovidService.getCaseByCountry("galleta");
            fail("Debio fallar por consultar aeropuertos por un nombre inexistente");
        } catch (Covid19Exception e) {
            assertEquals("No existe el pais galleta", e.getMessage());
        }
    }


}

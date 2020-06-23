package edu.eci.arsw.covid19;

import edu.eci.arsw.covid19.controller.Covid19Exception;
import edu.eci.arsw.covid19.model.Covid19ByCountry;
import edu.eci.arsw.covid19.service.Covid19Services;
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
public class Covid19ServicesTest {

    @Autowired
    Covid19Services covid19Services;

    @Test
    public void shouldGetCovid19CasesByCountryName() throws Covid19Exception {
        Covid19ByCountry response = covid19Services.getCovid19FromCountry("Colombia");
        assertNotNull(response);
        assertEquals("Colombia", response.getCountry());
    }

    @Test
    public void shouldNotGetCovid19CasesByCountryNameIfDoesntExist() {
        try {
            covid19Services.getCovid19FromCountry("galleta");
            fail("Debio fallar por consultar aeropuertos por un nombre inexistente");
        } catch (Covid19Exception e) {
            assertEquals("Error al obtener casos", e.getMessage());
        }
    }


}

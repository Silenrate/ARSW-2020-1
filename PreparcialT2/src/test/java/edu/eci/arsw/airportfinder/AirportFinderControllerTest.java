package edu.eci.arsw.airportfinder;

import edu.eci.arsw.airportfinder.controller.AirportFinderController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AirportFinderController.class)
public class AirportFinderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetAirportsByName() throws Exception {
        mvc.perform(get("/airports/London")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void shouldNotGetAirportsByNameIfDoesntExist() throws Exception {
        mvc.perform(get("/airports/galleta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

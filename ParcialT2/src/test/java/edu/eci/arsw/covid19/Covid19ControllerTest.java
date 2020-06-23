package edu.eci.arsw.covid19;

import edu.eci.arsw.covid19.controller.Covid19Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Covid19Controller.class)
public class Covid19ControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCovid19CasesByCountryName() throws Exception {
        mvc.perform(get("/infected/Colombia")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void shouldNotGetCovid19CasesByCountryNameIfDoesntExist() throws Exception {
        mvc.perform(get("/airports/galleta")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}

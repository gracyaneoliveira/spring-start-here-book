package com.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetFrance() throws Exception {
    var mapper = new ObjectMapper();

    Country c = new Country();
    c.setName("France");
    c.setPopulation(67);

    var expected = mapper.writeValueAsString(c);

    mockMvc.perform(get("/france"))
        .andExpect(status().isOk())
        .andExpect(content().json(expected));
  }

  @Test
  public void testGetAllCountries() throws Exception {
    var mapper = new ObjectMapper();

    Country c1 = new Country();
    c1.setName("France");
    c1.setPopulation(67);

    Country c2 = new Country();
    c2.setName("Spain");
    c2.setPopulation(47);

    var expected = mapper.writeValueAsString(List.of(c1,c2));

    mockMvc.perform(get("/all"))
        .andExpect(status().isOk())
        .andExpect(content().json(expected));
  }
}

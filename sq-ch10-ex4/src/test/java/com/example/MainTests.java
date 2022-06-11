package com.example;

import com.example.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        .andExpect(status().isAccepted())
        .andExpect(content().json(expected))
        .andExpect(header().string("continent", "Europe"))
        .andExpect(header().string("capital", "Paris"))
        .andExpect(header().string("favorite_food", "cheese and wine"));
  }

}

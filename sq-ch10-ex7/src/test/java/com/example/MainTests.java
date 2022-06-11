package com.example;

import com.example.model.PaymentDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testPayment() throws Exception {
    var mapper = new ObjectMapper();

    PaymentDetails p = new PaymentDetails();
    p.setAmount(1000);

    var expected = mapper.writeValueAsString(p);

    mockMvc.perform(post("/payment")
          .content(expected)
          .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isAccepted())
        .andExpect(content().json(expected));
  }

}

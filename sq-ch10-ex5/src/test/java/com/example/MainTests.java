package com.example;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.exceptions.NotEnoughMoneyException;
import com.example.model.ErrorDetails;
import com.example.model.PaymentDetails;
import com.example.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PaymentService paymentService;

  @Test
  void testMakePaymentSuccessful() throws Exception {
    var mapper = new ObjectMapper();

    PaymentDetails p = new PaymentDetails();
    p.setAmount(1000);

    when(paymentService.processPayment())
        .thenReturn(p);

    var expected = mapper.writeValueAsString(p);

    mockMvc.perform(post("/payment"))
        .andExpect(status().isAccepted())
        .andExpect(content().json(expected));
  }

  @Test
  void testMakePaymentNotEnoughMoney() throws Exception {
    var mapper = new ObjectMapper();

    ErrorDetails e = new ErrorDetails();
    e.setMessage("Not enough money to make the payment.");

    when(paymentService.processPayment())
        .thenThrow(new NotEnoughMoneyException());

    var expected = mapper.writeValueAsString(e);

    mockMvc.perform(post("/payment"))
        .andExpect(status().isBadRequest())
        .andExpect(content().json(expected));
  }

}

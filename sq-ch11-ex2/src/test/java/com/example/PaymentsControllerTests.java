package com.example;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentsControllerTests {

  @Autowired
  private MockMvc mockMvc;

  private static WireMockServer wireMockServer;

  @BeforeAll
  static void init() {
    wireMockServer = new WireMockServer(new WireMockConfiguration().port(8080));
    wireMockServer.start();
    WireMock.configureFor("localhost", 8080);
  }

  @Test
  void testPaymentEndpoint() throws Exception {
    Payment request = new Payment();
    request.setAmount(1000);

    ObjectMapper mapper = new ObjectMapper();

    stubFor(WireMock.post(urlMatching("/payment"))
        .willReturn(aResponse()
            .withBody(mapper.writeValueAsString(request))
            .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
            .withStatus(OK.value())));

    mockMvc.perform(post("/payment")
            .content(mapper.writeValueAsString(request))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.amount").value(1000));
  }

}

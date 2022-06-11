package com.example;

import com.example.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
@AutoConfigureWebTestClient
class PaymentsControllerTests {

  @Autowired
  private WebTestClient webTestClient;

  private static WireMockServer wireMockServer;

  @BeforeAll
  static void init() {
    wireMockServer = new WireMockServer(new WireMockConfiguration().port(8080));
    wireMockServer.start();
    WireMock.configureFor("localhost", 8080);
  }

  @Test
  void testPaymentEndpoint() throws Exception {
    Payment requestBody = new Payment();
    requestBody.setAmount(1000);

    ObjectMapper mapper = new ObjectMapper();
    stubFor(WireMock.post(urlMatching("/payment"))
        .willReturn(aResponse()
            .withBody(mapper.writeValueAsString(requestBody))
            .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
            .withStatus(OK.value())));

    webTestClient.post()
        .uri("/payment")
        .bodyValue(requestBody)
        .exchange()
        .expectStatus().isOk();
  }

}

package com.example.proxy;

import com.example.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PaymentsProxy {

  private final WebClient webClient;

  @Value("${name.service.url}")
  private String url;

  public PaymentsProxy(WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<Payment> createPayment(String requestId, Payment payment) {
    return webClient.post()
              .uri(url + "/payment")
              .header("requestId", requestId)
              .body(Mono.just(payment), Payment.class)
              .retrieve()
              .bodyToMono(Payment.class);
  }
}

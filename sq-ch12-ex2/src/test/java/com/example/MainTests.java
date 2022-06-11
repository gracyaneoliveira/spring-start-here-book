package com.example;

import com.example.model.Purchase;
import com.example.repositories.PurchaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PurchaseRepository purchaseRepository;

  @Test
  void storePurchaseTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Purchase p = new Purchase();
    p.setProduct("Spring Quickly");
    p.setPrice(BigDecimal.TEN);

    mockMvc.perform(post("/purchase")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(p))
    ).andExpect(status().isOk());

    verify(purchaseRepository).storePurchase(p);
  }

  @Test
  void getPurchases() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    Purchase p = new Purchase();
    p.setProduct("Spring Quickly");
    p.setPrice(BigDecimal.TEN);

    List<Purchase> purchases = List.of(p);

    when(purchaseRepository.findAllPurchases()).thenReturn(purchases);

    mockMvc.perform(get("/purchase"))
        .andExpect(status().isOk())
        .andExpect(content().json(mapper.writeValueAsString(purchases)));
  }

}

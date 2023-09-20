package org.thales.transaction.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.thales.Application;
import org.thales.transaction.seach.SearchCriteria;
import org.thales.transaction.seach.SearchOperation;
import org.thales.transaction.seach.Type;
import org.thales.transaction.dto.TransactionDTO;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class TransactionControllerImplIT {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void whenCreateTransactionsAndSearchAndDelete_shouldSaveAndSearchAndRemove() throws Exception {
    // given
    final List<TransactionDTO> transactions = createTransactions();
    final List<SearchCriteria> searchCriteria = createSearchCriteria();

    // when and then
    performBulkSaves(transactions);

    performSearch(searchCriteria);

    performBulkDeletes();
  }

  private void performBulkDeletes() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.delete("/transactions"))
        .andExpectAll(status().isNoContent());
  }

  private void performSearch(List<SearchCriteria> searchCriteria) throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/transactions/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(searchCriteria)))
        .andExpectAll(
            status().isOk(),
            jsonPath("$", hasSize(1)),
            jsonPath("$[0].type", is("Sell")),
            jsonPath("$[0].actor", is("Jane Doe")));
  }

  private void performBulkSaves(List<TransactionDTO> transactions) throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/transactions/bulk")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactions)))
        .andExpectAll(
            status().isCreated(),
            jsonPath("$", hasSize(3)),
            jsonPath("$[0].type", is("Sell")),
            jsonPath("$[1].type", is("Purchase")),
            jsonPath("$[2].type", is("Purchase")));
  }

  private List<SearchCriteria> createSearchCriteria() {
    return List.of(
        new SearchCriteria("transactionData.amount", Type.MAP, SearchOperation.EQUALITY, "100"),
        new SearchCriteria(
            "timestamp",
            Type.LOCAL_DATE_TIME,
            SearchOperation.LESS_THAN,
            LocalDateTime.of(LocalDate.of(2023, 9, 20), LocalTime.MAX)));
  }

  private List<TransactionDTO> createTransactions() {
    return List.of(
        new TransactionDTO.Builder()
            .type("Sell")
            .actor("Jane Doe")
            .timestamp(LocalDateTime.of(LocalDate.of(2023, 9, 19), LocalTime.MAX))
            .transactionData(Map.of("amount", "100"))
            .build(),
        new TransactionDTO.Builder()
            .type("Purchase")
            .actor("John Doe")
            .timestamp(LocalDateTime.of(LocalDate.of(2023, 9, 20), LocalTime.MAX))
            .transactionData(Map.of("amount", "100"))
            .build(),
        new TransactionDTO.Builder()
            .type("Purchase")
            .actor("John Doe")
            .timestamp(LocalDateTime.of(LocalDate.of(2023, 9, 21), LocalTime.MAX))
            .transactionData(Map.of("amount", "200"))
            .build());
  }
}

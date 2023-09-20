package org.thales.transaction.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.thales.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;
import org.thales.transaction.model.domain.Transaction;

@RestController
public interface TransactionController {
  @GetMapping("/{id}")
  ResponseEntity<TransactionDTO> getTransactionById(@PathVariable final Long id);

  @PostMapping
  ResponseEntity<TransactionDTO> createTransaction(
      @NonNull @RequestBody final TransactionDTO transaction);

  @PutMapping("/{id}")
  ResponseEntity<Void> updateTransaction(
      @PathVariable final Long id, @RequestBody final TransactionDTO updatedTransaction);

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteTransaction(@PathVariable final Long id);

  @PostMapping("/search")
  @ResponseBody
  ResponseEntity<List<Transaction>> findAllBySpecification(@RequestBody final List<SearchCriteria> criteria);

}

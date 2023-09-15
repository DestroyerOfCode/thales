package org.thales.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thales.transaction.dto.TransactionDTO;

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
}

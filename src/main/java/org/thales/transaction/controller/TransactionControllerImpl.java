package org.thales.transaction.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.thales.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;
import org.thales.transaction.model.domain.Transaction;
import org.thales.transaction.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionControllerImpl implements TransactionController {
  private final TransactionService service;

  @Autowired
  public TransactionControllerImpl(final TransactionService service) {
    super();
    this.service = service;
  }

  @Override
  public final ResponseEntity<TransactionDTO> getTransactionById(final Long id) {
    final TransactionDTO transaction = service.getTransactionById(id);

    return ResponseEntity.ok(transaction);
  }

  @Override
  public final ResponseEntity<TransactionDTO> createTransaction(
      @NonNull @RequestBody final TransactionDTO transaction) {
    final TransactionDTO dto = service.create(transaction);
    return ResponseEntity.created(URI.create("transactions")).body(dto);
  }

  @Override
  public final ResponseEntity<Void> updateTransaction(
      final Long id, final TransactionDTO updatedTransaction) {
    service.updateTransaction(id, updatedTransaction);
    return ResponseEntity.noContent().build();
  }

  @Override
  public final ResponseEntity<Void> deleteTransaction(final Long id) {
    service.deleteTransactionById(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public final ResponseEntity<List<Transaction>> findAllBySpecification(
      @RequestBody final List<SearchCriteria> criteria) {
    final List<Transaction> transactions = service.findAllBySpecification(criteria);
    return ResponseEntity.ok(transactions);
  }
}

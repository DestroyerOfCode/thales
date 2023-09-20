package org.thales.transaction.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thales.transaction.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;
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
  public final ResponseEntity<TransactionDTO> create(final TransactionDTO transaction) {
    final TransactionDTO dto = service.create(transaction);
    return ResponseEntity.created(URI.create("transactions")).body(dto);
  }

  @Override
  public ResponseEntity<List<TransactionDTO>> create(final List<TransactionDTO> transactions) {
    final List<TransactionDTO> createdTransactions = service.create(transactions);
    return ResponseEntity.created(URI.create("transactions")).body(createdTransactions);
  }

  @Override
  public final ResponseEntity<Void> update(final Long id, final TransactionDTO updatedTransaction) {
    service.update(id, updatedTransaction);
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> delete() {
    service.delete();
    return ResponseEntity.noContent().build();
  }

  @Override
  public final ResponseEntity<Void> delete(final Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  public final ResponseEntity<List<TransactionDTO>> search(final List<SearchCriteria> criteria) {
    final List<TransactionDTO> transactions = service.search(criteria);
    return ResponseEntity.ok(transactions);
  }
}

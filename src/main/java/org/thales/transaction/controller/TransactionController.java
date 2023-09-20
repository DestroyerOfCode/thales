package org.thales.transaction.controller;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.thales.transaction.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;

@RestController
public interface TransactionController {
  @GetMapping("/{id}")
  ResponseEntity<TransactionDTO> getTransactionById(@PathVariable final Long id);

  @PostMapping
  ResponseEntity<TransactionDTO> create(@NonNull @RequestBody final TransactionDTO transaction);

  @PostMapping("/bulk")
  ResponseEntity<List<TransactionDTO>> create(
      @NotEmpty @RequestBody final List<TransactionDTO> transaction);

  @PutMapping("/{id}")
  ResponseEntity<Void> update(
      @PathVariable final Long id, @RequestBody final TransactionDTO updatedTransaction);

  @DeleteMapping
  ResponseEntity<Void> delete();

  @DeleteMapping("/{id}")
  ResponseEntity<Void> delete(@PathVariable final Long id);

  @PostMapping("/search")
  @ResponseBody
  ResponseEntity<List<TransactionDTO>> search(@RequestBody final List<SearchCriteria> criteria);
}

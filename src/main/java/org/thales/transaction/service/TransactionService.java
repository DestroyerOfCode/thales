package org.thales.transaction.service;

import java.util.List;
import org.thales.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;
import org.thales.transaction.model.domain.Transaction;

public interface TransactionService {
  TransactionDTO getTransactionById(final Long id);

  TransactionDTO create(final TransactionDTO transaction);

  void deleteTransactionById(final Long id);

  void updateTransaction(final Long id, final TransactionDTO updatedTransaction);

  List<Transaction> findAllBySpecification(final List<SearchCriteria> criteria);
}

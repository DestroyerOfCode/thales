package org.thales.transaction.service;

import java.util.List;

import org.thales.transaction.dto.TransactionDTO;

public interface TransactionService {
  TransactionDTO getTransactionById(final Long id);

  TransactionDTO create(final TransactionDTO transaction);

  void deleteTransactionById(final Long id);

  void updateTransaction(final Long id, final TransactionDTO updatedTransaction);

}

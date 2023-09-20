package org.thales.transaction.service;

import java.util.List;
import org.thales.transaction.seach.SearchCriteria;
import org.thales.transaction.dto.TransactionDTO;

public interface TransactionService {
  TransactionDTO getTransactionById(final Long id);

  TransactionDTO create(final TransactionDTO transaction);

  List<TransactionDTO> create(final List<TransactionDTO> transactions);

  void delete();

  void delete(final Long id);

  void update(final Long id, final TransactionDTO updatedTransaction);

  List<TransactionDTO> search(final List<SearchCriteria> criteria);
}

package org.thales.transaction.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thales.transaction.converter.TransactionMapper;
import org.thales.transaction.dto.TransactionDTO;
import org.thales.transaction.exception.TransactionRequestException;
import org.thales.transaction.model.domain.Transaction;
import org.thales.transaction.model.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
  private final TransactionRepository repository;
  private final TransactionMapper mapper;

  @Autowired
  public TransactionServiceImpl(
      final TransactionRepository repository, final TransactionMapper mapper) {
    super();
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public final TransactionDTO getTransactionById(final Long id) {
    final Optional<Transaction> transactionOpt = repository.findById(id);

    if (transactionOpt.isPresent()) {
      return transactionOpt
          .map(mapper::toDTO)
          .orElseThrow(
              () -> {
                LOGGER.error("Error mapping transaction with ID: {}", id);
                return new TransactionRequestException("Error mapping transaction to DTO");
              });
    }

    LOGGER.error("Transaction {} was not present", id);

    throw new TransactionRequestException(
        String.format("Could not find transaction with id %s", id));
  }

  @Override
  public final TransactionDTO create(final TransactionDTO dto) {
    try {
      final Transaction transaction = mapper.toEntity(dto);
      final Transaction savedTransaction = repository.saveAndFlush(transaction);

      return mapper.toDTO(savedTransaction);
    } catch (Exception e) {
      LOGGER.error("Error trying to save transaction", e);
    }

    throw new TransactionRequestException("Error occurred creating transaction");
  }

  @Override
  public final void deleteTransactionById(final Long id) {
    try {
      final Optional<Transaction> transactionOpt = repository.findById(id);

      if (transactionOpt.isPresent()) {
        repository.deleteById(id);
        return;
      }
    } catch (Exception e) {
      LOGGER.error("Error trying to delete transaction", e);
    }

    throw new TransactionRequestException(String.format("Transaction with ID %s not present", id));
  }

  @Override
  public void updateTransaction(final Long id, final TransactionDTO updatedDTO) {
    try {
      if (repository.findById(id).isPresent()) {
        final Transaction transaction = mapper.toEntity(updatedDTO);
        repository.saveAndFlush(transaction);
        return;
      }
    } catch (Exception e) {
      LOGGER.error("Error trying to update transaction", e);
    }
    throw new TransactionRequestException(String.format("Transaction with ID %s not present", id));
  }
}

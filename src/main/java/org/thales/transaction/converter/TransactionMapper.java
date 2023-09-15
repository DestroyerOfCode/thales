package org.thales.transaction.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thales.transaction.dto.TransactionDTO;
import org.thales.transaction.model.domain.Transaction;

@Component
public class TransactionMapper {
  private final ModelMapper modelMapper;

  @Autowired
  public TransactionMapper(ModelMapper modelMapper) {
    super();
    this.modelMapper = modelMapper;
  }

  public Transaction toEntity(final TransactionDTO dto) {
      return modelMapper.map(dto, Transaction.class);
  }

  public TransactionDTO toDTO(final Transaction transaction) {
      return modelMapper.map(transaction, TransactionDTO.class);
  }
}

package org.thales.transaction.seach;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.thales.transaction.model.domain.Transaction;

public class TransactionSpecificationBuilder {

  private final List<org.thales.transaction.seach.SearchCriteria> params = new ArrayList<>();

  public void with(
          final String key, final Type type, final org.thales.transaction.seach.SearchOperation op, final Object value) {
    if (Objects.nonNull(op)) {
      params.add(new org.thales.transaction.seach.SearchCriteria(key, type, op, value));
    }
  }

  public Specification<Transaction> build() {
    if (params.isEmpty()) {
      return null;
    }

    Specification<Transaction> result = null;

    for (org.thales.transaction.seach.SearchCriteria param : params) {
      result = Specification.where(result).and(new org.thales.transaction.seach.TransactionSpecification(param));
    }

    return result;
  }
}

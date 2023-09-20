package org.thales.seach;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.thales.transaction.model.domain.Transaction;

public class TransactionSpecificationBuilder {

  private final List<SearchCriteria> params = new ArrayList<>();

  public TransactionSpecificationBuilder with(
      final String key, final Type type, final SearchOperation op, final Object value) {
    if (Objects.nonNull(op)) {
      params.add(new SearchCriteria(key, type, op, value));
    }

    return this;
  }

  public Specification<Transaction> build() {
    if (params.isEmpty()) {
      return null;
    }

    Specification<Transaction> result = null;

    for (SearchCriteria param : params) {
      result = Specification.where(result).and(new TransactionSpecification(param));
    }

    return result;
  }
}

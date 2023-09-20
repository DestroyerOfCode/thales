package org.thales.seach;

import jakarta.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.thales.transaction.model.domain.Transaction;

public class TransactionSpecification implements Specification<Transaction> {

  private final SearchCriteria criteria;

  public TransactionSpecification(final SearchCriteria criteria) {
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(
      final Root<Transaction> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {

    return switch (criteria.getOperation()) {
      case EQUALITY -> isStringOrMap(root, builder, builder::equal);
      case NEGATION -> isStringOrMap(root, builder, builder::notEqual);
      case GREATER_THAN -> getPredicateGreaterThan(root, builder);
      case LESS_THAN -> getPredicateLessThan(root, builder);
    };
  }

  private Predicate isStringOrMap(
      final Root<Transaction> root,
      final CriteriaBuilder builder,
      final PredicateFunction predFunc) {
    if (Objects.equals(Type.MAP, criteria.getType())) {
      final String[] keys = criteria.getKey().split("\\.");
      MapJoin<Root<Transaction>, String, String> join = root.joinMap(keys[0]);
      return builder.and(
          predFunc.apply(join.key(), keys[1]), predFunc.apply(join.value(), criteria.getValue()));
    }

    return predFunc.apply(root.get(criteria.getKey()), criteria.getValue());
  }

  private Predicate getPredicateGreaterThan(Root<Transaction> root, CriteriaBuilder builder) {
    if (Objects.equals(Type.LOCAL_DATE_TIME, criteria.getType())) {
      return builder.greaterThan(root.get(criteria.getKey()), (LocalDateTime) criteria.getValue());
    }
    return builder.greaterThan(root.get(criteria.getKey()), (Double) criteria.getValue());
  }

  private Predicate getPredicateLessThan(Root<Transaction> root, CriteriaBuilder builder) {
    if (Objects.equals(Type.LOCAL_DATE_TIME, criteria.getType())) {
      return builder.lessThan(
          root.get(criteria.getKey()), LocalDateTime.parse(criteria.getValue().toString()));
    }
    return builder.lessThan(root.get(criteria.getKey()), (Double) criteria.getValue());
  }
}

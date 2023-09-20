package org.thales.transaction.seach;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.MapJoin;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.thales.transaction.exception.TransactionRequestException;
import org.thales.transaction.model.domain.Transaction;

import static org.thales.transaction.seach.Type.DOUBLE;
import static org.thales.transaction.seach.Type.LOCAL_DATE_TIME;
import static org.thales.transaction.seach.Type.MAP;
import static org.thales.transaction.seach.Type.STRING;

public class TransactionSpecification implements Specification<Transaction> {

  private final org.thales.transaction.seach.SearchCriteria criteria;

  public TransactionSpecification(final org.thales.transaction.seach.SearchCriteria criteria) {
    super();
    this.criteria = criteria;
  }

  @Override
  public Predicate toPredicate(
      @NonNull final Root<Transaction> root,
      @NonNull final CriteriaQuery<?> query,
      @NonNull final CriteriaBuilder builder) {

    return switch (criteria.operation()) {
      case EQUALITY -> isStringOrMap(root, builder, builder::equal);
      case NEGATION -> isStringOrMap(root, builder, builder::notEqual);
      case GREATER_THAN -> getPredicateGreaterThan(root, builder);
      case LESS_THAN -> getPredicateLessThan(root, builder);
    };
  }

  private Predicate isStringOrMap(
      final Root<Transaction> root,
      final CriteriaBuilder builder,
      final org.thales.transaction.seach.PredicateFunction predFunc) {
    if (Objects.equals(MAP, criteria.type())) {
      final String[] keys = criteria.key().split("\\.");
      final MapJoin<Root<Transaction>, String, String> join = root.joinMap(keys[0]);
      return builder.and(
          predFunc.apply(join.key(), keys[1]), predFunc.apply(join.value(), criteria.value()));
    }

    return predFunc.apply(root.get(criteria.key()), criteria.value());
  }

  private Predicate getPredicateGreaterThan(Root<Transaction> root, CriteriaBuilder builder) {
    if (Objects.equals(LOCAL_DATE_TIME, criteria.type())) {
      return builder.greaterThan(root.get(criteria.key()), (LocalDateTime) criteria.value());
    } else if (Objects.equals(STRING, criteria.type())) {
      return builder.greaterThan(root.get(criteria.key()), (Double) criteria.value());
    }

    throw new TransactionRequestException("Invalid search criteria type selected");
  }

  private Predicate getPredicateLessThan(Root<Transaction> root, CriteriaBuilder builder) {
    if (Objects.equals(LOCAL_DATE_TIME, criteria.type())) {
      return builder.lessThan(
          root.get(criteria.key()), LocalDateTime.parse(criteria.value().toString()));
    } else if (Objects.equals(DOUBLE, criteria.type())) {
      return builder.lessThan(root.get(criteria.key()), (Double) criteria.value());
    }

    throw new TransactionRequestException("Invalid search criteria type selected");
  }
}

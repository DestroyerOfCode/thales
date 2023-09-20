package org.thales.transaction.seach;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

@FunctionalInterface
interface PredicateFunction {
    Predicate apply(Expression<?> x, Object y);
}

package org.thales.transaction.seach;

public record SearchCriteria(String key, Type type, SearchOperation operation, Object value) {}

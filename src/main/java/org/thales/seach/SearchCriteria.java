package org.thales.seach;

public class SearchCriteria {
  private final String key;

  private final Type type;

  private final SearchOperation operation;
  private final Object value;

  public SearchCriteria(
      final String key, final Type type, final SearchOperation operation, final Object value) {
    this.key = key;
    this.type = type;
    this.operation = operation;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public SearchOperation getOperation() {
    return operation;
  }

  public Object getValue() {
    return value;
  }

  public Type getType() {
    return type;
  }
}

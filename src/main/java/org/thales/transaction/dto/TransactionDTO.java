package org.thales.transaction.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class TransactionDTO {

  private Long id;

  private LocalDateTime timestamp;

  private String type;

  private String actor;

  private Map<String, String> transactionData;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(final LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getActor() {
    return actor;
  }

  public void setActor(final String actor) {
    this.actor = actor;
  }

  public Map<String, String> getTransactionData() {
    return transactionData;
  }

  public void setTransactionData(final Map<String, String> transactionData) {
    this.transactionData = transactionData;
  }
}

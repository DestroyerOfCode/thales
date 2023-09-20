package org.thales.transaction.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class TransactionDTO {

  private LocalDateTime timestamp;

  private String type;

  private String actor;

  private Map<String, String> transactionData;

  private TransactionDTO() {}

  // Builder class
  public static class Builder {
    private final TransactionDTO transactionDTO = new TransactionDTO();

    public Builder timestamp(LocalDateTime timestamp) {
      transactionDTO.timestamp = timestamp;
      return this;
    }

    public Builder type(String type) {
      transactionDTO.type = type;
      return this;
    }

    public Builder actor(String actor) {
      transactionDTO.actor = actor;
      return this;
    }

    public Builder transactionData(Map<String, String> transactionData) {
      transactionDTO.transactionData = transactionData;
      return this;
    }

    public TransactionDTO build() {
      // You can perform additional validation here if needed
      return transactionDTO;
    }
  }
  public static Builder builder() {
    return new Builder();
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

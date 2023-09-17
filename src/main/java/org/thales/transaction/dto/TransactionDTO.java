package org.thales.transaction.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "transaction")
public class TransactionDTO {

  private static final String DATE_TIME_FORMAT = "uuuu-MM-dd'T'HH:mm:ss.SSS";
  private Long id;

  @Field(type = FieldType.Date_Nanos, pattern = DATE_TIME_FORMAT, format = {})
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

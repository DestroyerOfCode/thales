package org.thales.transaction.model.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;

import java.sql.Timestamp;
import java.util.Map;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @Column(name = "type")
    private String type;

    @Column(name = "actor")
    private String actor;

    @ElementCollection
    @MapKeyColumn(name = "transaction_key")
    @Column(name = "transaction_value")
    @CollectionTable(name = "transaction_data", joinColumns = @JoinColumn(name = "transaction_id"))
    private Map<String, String> transactionData;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Timestamp timestamp) {
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

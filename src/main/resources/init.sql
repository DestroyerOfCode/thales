CREATE DATABASE IF NOT EXISTS thalestest;

CREATE TABLE IF NOT EXISTS thalestest.transaction
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP,
    type      VARCHAR(255),
    actor     VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS thalestest.transaction_data
(
    transaction_id    BIGINT,
    transaction_key   VARCHAR(255),
    transaction_value VARCHAR(255),
    PRIMARY KEY (transaction_id, transaction_key),
    CONSTRAINT FK_transaction_data_transaction_id FOREIGN KEY (transaction_id) REFERENCES transaction (id)
);

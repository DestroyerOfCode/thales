package org.thales.transaction.exception;

public class TransactionRequestException extends RuntimeException {

    public TransactionRequestException() {
    }

    public TransactionRequestException(final String message) {
        super(message);
    }

    public TransactionRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TransactionRequestException(final Throwable cause) {
        super(cause);
    }
}

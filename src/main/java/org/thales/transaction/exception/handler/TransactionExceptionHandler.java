package org.thales.transaction.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thales.transaction.controller.TransactionControllerImpl;
import org.thales.transaction.exception.TransactionRequestException;

@ControllerAdvice(basePackageClasses = TransactionControllerImpl.class)
public class TransactionExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseBody
  @ExceptionHandler(TransactionRequestException.class)
  public ResponseEntity<?> handleTransactionException(
      final WebRequest request, final TransactionRequestException ex) {
    return super.handleExceptionInternal(
        ex,
        ex.getMessage(),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request);
  }
}

package com.bakery.logistics.domain.exception;

public class CurrencyMismatchException extends BusinessException {
  public CurrencyMismatchException(String message) {
    super(message);
  }
}

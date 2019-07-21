package com.bakery.logistics.domain.exception;

public class UnsupportedOrderException extends BusinessException {
  public UnsupportedOrderException(String message) {
    super(message);
  }
}

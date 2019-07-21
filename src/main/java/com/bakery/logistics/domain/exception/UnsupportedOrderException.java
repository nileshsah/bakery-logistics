package com.bakery.logistics.domain.exception;

public class UnsupportedOrderException extends RuntimeException {
  public UnsupportedOrderException(String message) {
    super(message);
  }
}

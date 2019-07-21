package com.bakery.logistics.domain.exception;

public class ProductNotFoundException extends BusinessException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}

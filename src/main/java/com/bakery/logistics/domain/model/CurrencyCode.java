package com.bakery.logistics.domain.model;

import lombok.Getter;

@Getter
public enum CurrencyCode {
  USD("USD", "$"),
  EUR("EUR", "€");

  private final String name;
  private final String symbol;

  CurrencyCode(String name, String symbol) {
    this.name = name;
    this.symbol = symbol;
  }
}

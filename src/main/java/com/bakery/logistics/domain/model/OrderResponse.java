package com.bakery.logistics.domain.model;

import com.bakery.logistics.domain.exception.CurrencyMismatchException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class OrderResponse {
  private Product product;
  private Map<Pack, Integer> packQuantity;

  public Double getTotalCost() {
    Long currencyCount = packQuantity.entrySet()
        .stream()
        .map(keyValue -> keyValue.getKey().getCurrency())
        .distinct()
        .count();

    if (currencyCount > 1) {
      throw new CurrencyMismatchException("More than one currency present for product");
    }

    return packQuantity.entrySet()
        .stream()
        .mapToDouble(keyValue -> keyValue.getKey().getPrice() * keyValue.getValue())
        .sum();
  }

  public CurrencyCode getCurrencyCode() {
    return packQuantity.entrySet()
        .stream()
        .findFirst()
        .map(keyValue -> keyValue.getKey().getCurrency())
        .orElseThrow(() -> new IllegalStateException("No currency found"));
  }
}

package com.bakery.logistics.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class OrderResponse {
  private Product product;
  private Map<Pack, Integer> packQuantity;
}

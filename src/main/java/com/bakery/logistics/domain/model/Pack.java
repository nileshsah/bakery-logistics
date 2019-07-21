package com.bakery.logistics.domain.model;

import lombok.Data;

@Data
public class Pack {
  private final Long size;
  private final Double price;
  private final CurrencyCode currency;
}

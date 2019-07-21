package com.bakery.logistics.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pack {
  private Long size;
  private Double price;
  private CurrencyCode currency;
}

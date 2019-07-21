package com.bakery.logistics.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Inventory {
  private List<Product> products;
}

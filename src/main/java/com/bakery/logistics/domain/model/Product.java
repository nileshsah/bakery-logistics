package com.bakery.logistics.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Product {
  private final String name;
  private final List<Pack> packs;
}

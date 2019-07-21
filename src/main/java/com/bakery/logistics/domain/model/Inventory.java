package com.bakery.logistics.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
  private List<Product> products;

  @JsonIgnore
  public Optional<Product> getProductByCode(String productCode) {
    return products.stream()
        .filter(product -> productCode.equalsIgnoreCase(product.getCode()))
        .findFirst();
  }
}

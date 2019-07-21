package com.bakery.logistics.domain.service;

import com.bakery.logistics.domain.exception.ProductNotFoundException;
import com.bakery.logistics.domain.exception.UnsupportedOrderException;
import com.bakery.logistics.domain.model.Inventory;
import com.bakery.logistics.domain.model.OrderResponse;
import com.bakery.logistics.domain.model.Pack;
import com.bakery.logistics.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductOrderService {

  private final Inventory inventory;

  public ProductOrderService(Inventory inventory) {
    this.inventory = inventory;
  }

  public OrderResponse placeOrder(String productCode, Integer quantity) {
    final Product selectedProduct = inventory.getProductByCode(productCode).orElseThrow(
        () -> new ProductNotFoundException("No product found for given product code: " + productCode)
    );
    return new OrderResponse(selectedProduct, findOptimizedPacks(selectedProduct, quantity));
  }

  private Map<Pack, Integer> findOptimizedPacks(Product product, Integer quantity) {
    final Map<Integer, StateValue> quantityStateCache = new HashMap<>();
    Optional<Integer> optimizedPackCount = getMinimumPackCount(product, quantity, quantityStateCache);

    if (!optimizedPackCount.isPresent()) {
      throw new UnsupportedOrderException("The given order quantity cannot be completely broken down into available packs");
    }

    Map<Pack, Integer> requiredPackQuantity = new HashMap<>();
    getPacksToUse(quantity, quantityStateCache)
        .stream()
        .forEach(pack ->
            requiredPackQuantity.put(
                pack,
                requiredPackQuantity.getOrDefault(pack, 0) + 1
            )
        );
    return requiredPackQuantity;
  }

  private List<Pack> getPacksToUse(Integer quantity, Map<Integer, StateValue> quantityStateCache) {
    final List<Pack> packs = new ArrayList<>();
    Integer currentQuantity = quantity;

    while(currentQuantity > 0) {
      Pack chosenPack = quantityStateCache.get(currentQuantity).getChosenPack().orElseThrow(
          () -> new IllegalStateException("No pack exists for the quantity: " + quantity)
      );
      packs.add(chosenPack);
      currentQuantity -= chosenPack.getSize();
    }
    return packs;
  }

  private Optional<Integer> getMinimumPackCount(Product product, Integer quantity, Map<Integer, StateValue> cache) {
    if (quantity.equals(0)) {
      return Optional.of(0);
    }

    if (cache.containsKey(quantity)) {
      return cache.get(quantity).getMinimumPacks();
    }

    Optional<Pack> packToUse = Optional.empty();
    Optional<Integer> minimumPacksForCurrentState = Optional.empty();

    for (Pack currentPack : product.getPacks()) {
      if (currentPack.getSize() > quantity) {
        continue;
      }

      Optional<Integer> returnValue = getMinimumPackCount(product, quantity - currentPack.getSize(), cache);
      if (!returnValue.isPresent()) {
        continue;
      }

      if (minimumPacksForCurrentState.isPresent() && minimumPacksForCurrentState.get() > returnValue.get()) {
        minimumPacksForCurrentState = returnValue;
        packToUse = Optional.of(currentPack);
      } else if (!minimumPacksForCurrentState.isPresent()) {
        minimumPacksForCurrentState = returnValue;
        packToUse = Optional.of(currentPack);
      }
    }

    StateValue currentStateValue = new StateValue(packToUse, minimumPacksForCurrentState.map(value -> value + 1));
    cache.put(quantity, currentStateValue);

    return currentStateValue.getMinimumPacks();
  }

  @AllArgsConstructor
  @Getter
  private static class StateValue {
    private Optional<Pack> chosenPack;
    private Optional<Integer> minimumPacks;
  }

}

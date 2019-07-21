package com.bakery.logistics.domain.service;

import com.bakery.logistics.domain.exception.ProductNotFoundException;
import com.bakery.logistics.domain.exception.UnsupportedOrderException;
import com.bakery.logistics.domain.model.CurrencyCode;
import com.bakery.logistics.domain.model.Inventory;
import com.bakery.logistics.domain.model.OrderResponse;
import com.bakery.logistics.domain.model.Pack;
import com.bakery.logistics.domain.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ProductOrderServiceTest {

  private ProductOrderService productOrderService;

  @Before
  public void setup() {
    this.productOrderService = new ProductOrderService(buildInventory());
  }

  @Test
  public void testShouldGetOptimizedPacksForProductOrderCaseI() {
    OrderResponse response = productOrderService.placeOrder("VS5", 10);

    Assert.assertNotNull(response);
    Assert.assertEquals("Vegemite Scroll", response.getProduct().getName());

    Assert.assertTrue(response.getPackQuantity().containsKey(new Pack(5, 8.99, CurrencyCode.USD)));
    Assert.assertEquals(2, response.getPackQuantity().get(new Pack(5, 8.99, CurrencyCode.USD)).intValue());
  }

  @Test
  public void testShouldGetOptimizedPacksForProductOrderCaseII() {
    OrderResponse response = productOrderService.placeOrder("MB11", 14);

    Assert.assertNotNull(response);
    Assert.assertEquals("Blueberry Muffin", response.getProduct().getName());

    Assert.assertEquals(2, response.getPackQuantity().size());
    Assert.assertEquals(1, response.getPackQuantity().get(new Pack(8, 24.95, CurrencyCode.USD)).intValue());
    Assert.assertEquals(3, response.getPackQuantity().get(new Pack(2, 9.95, CurrencyCode.USD)).intValue());
  }

  @Test
  public void testShouldGetOptimizedPacksForProductOrderCaseIII() {
    OrderResponse response = productOrderService.placeOrder("CF", 13);

    Assert.assertNotNull(response);
    Assert.assertEquals("Croissant", response.getProduct().getName());

    Assert.assertEquals(2, response.getPackQuantity().size());
    Assert.assertEquals(1, response.getPackQuantity().get(new Pack(3, 5.95, CurrencyCode.USD)).intValue());
    Assert.assertEquals(2, response.getPackQuantity().get(new Pack(5, 9.95, CurrencyCode.USD)).intValue());
  }

  @Test(expected = ProductNotFoundException.class)
  public void testShouldThrowExceptionForUnkownProductCode() {
    productOrderService.placeOrder("ABC", 100);
  }

  @Test(expected = UnsupportedOrderException.class)
  public void testShouldThrowExceptionForUnquantifiableProductQuantity() {
    productOrderService.placeOrder("VS5", 7);
  }


  private Inventory buildInventory() {
    return new Inventory(
        Arrays.asList(
            new Product(
                "Vegemite Scroll",
                "VS5",
                Arrays.asList(
                    new Pack(3, 6.99, CurrencyCode.USD),
                    new Pack(5, 8.99, CurrencyCode.USD)
                )
            ),
            new Product(
                "Blueberry Muffin",
                "MB11",
                Arrays.asList(
                    new Pack(2, 9.95, CurrencyCode.USD),
                    new Pack(5, 16.95, CurrencyCode.USD),
                    new Pack(8, 24.95, CurrencyCode.USD)
                )
            ),
            new Product(
                "Croissant",
                "CF",
                Arrays.asList(
                    new Pack(3, 5.95, CurrencyCode.USD),
                    new Pack(5, 9.95, CurrencyCode.USD),
                    new Pack(9, 16.99, CurrencyCode.USD)
                )
            )
        )
    );
  }
}

package com.bakery.logistics.application.utils;

import com.bakery.logistics.domain.model.CurrencyCode;
import com.bakery.logistics.domain.model.Inventory;
import com.bakery.logistics.domain.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class YamlParserTest {

  @Test
  public void testShouldDeserializeInventoryYaml() throws URISyntaxException {
    Inventory inventory = YamlParser.parse(getResourceFilePath("inventory_stock_test.yaml"), Inventory.class);

    Assert.assertEquals(3, inventory.getProducts().size());

    Product firstProduct = inventory.getProducts().get(0);
    Assert.assertEquals("Vegemite Scroll", firstProduct.getName());
    Assert.assertEquals("VS5", firstProduct.getCode());
    Assert.assertEquals(2, firstProduct.getPacks().size());

    Assert.assertEquals(3, firstProduct.getPacks().get(0).getSize().longValue());
    Assert.assertEquals(CurrencyCode.USD, firstProduct.getPacks().get(0).getCurrency());
    Assert.assertEquals(6.99, firstProduct.getPacks().get(0).getPrice(), 0.001);

    Assert.assertEquals(5, firstProduct.getPacks().get(1).getSize().longValue());
    Assert.assertEquals(CurrencyCode.USD, firstProduct.getPacks().get(1).getCurrency());
    Assert.assertEquals(8.99, firstProduct.getPacks().get(1).getPrice(), 0.001);
  }

  private String getResourceFilePath(String filename) throws URISyntaxException {
    URL res = getClass().getClassLoader().getResource(filename);
    File file = Paths.get(res.toURI()).toFile();
    return file.getAbsolutePath();
  }
}

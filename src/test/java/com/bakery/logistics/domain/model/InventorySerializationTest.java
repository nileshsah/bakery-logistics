package com.bakery.logistics.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InventorySerializationTest {

  @Test
  public void testShouldSerializeInventoryInCorrectYamlFormat() throws JsonProcessingException {
    final Inventory inventory = new Inventory(
        Arrays.asList(
            new Product(
                "Vegemite Scroll",
                "VS5",
                Arrays.asList(
                    new Pack(3L, 6.99, CurrencyCode.USD),
                    new Pack(5L, 8.99, CurrencyCode.USD)
                )
            ),
            new Product(
                "Blueberry Muffin",
                "MB11",
                Arrays.asList(
                    new Pack(2L, 9.95, CurrencyCode.USD),
                    new Pack(5L, 16.95, CurrencyCode.USD),
                    new Pack(8L, 24.95, CurrencyCode.USD)
                )
            ),
            new Product(
                "Croissant",
                "CF",
                Arrays.asList(
                    new Pack(3L, 5.95, CurrencyCode.USD),
                    new Pack(5L, 9.95, CurrencyCode.USD),
                    new Pack(9L, 16.99, CurrencyCode.USD)
                )
            )
        )
    );


    ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
    String actualYamlString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inventory);
    String expectedYamlString = getResourceFileAsString("inventory_stock_test.yaml");

    Assert.assertEquals(expectedYamlString, actualYamlString);
  }

  public String getResourceFileAsString(String fileName) {
    InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    return reader.lines().collect(Collectors.joining(System.lineSeparator()));
  }
}

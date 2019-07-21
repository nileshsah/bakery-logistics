package com.bakery.logistics.application;

import com.bakery.logistics.application.utils.YamlParser;
import com.bakery.logistics.domain.exception.BusinessException;
import com.bakery.logistics.domain.model.Inventory;
import com.bakery.logistics.domain.model.OrderResponse;
import com.bakery.logistics.domain.service.ProductOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleRunner implements Runnable {

  @Override
  public void run() {
    ObjectMapper mapper = new ObjectMapper();
    Inventory inventory = YamlParser.parse(getResourceFilePath("inventory_stock.yaml"), Inventory.class);
    ProductOrderService orderService = new ProductOrderService(inventory);

    Scanner in = new Scanner(System.in);
    System.out.print("Enter number of orders: ");
    System.out.flush();

    Integer orders = in.nextInt();

    IntStream.range(1, orders + 1).forEach(orderId -> {
          System.out.print("Enter product code: ");
          System.out.flush();

          String productCode = in.next();

          System.out.print("Enter order quantity: ");
          System.out.flush();

          Integer orderQuantity = in.nextInt();

          try {
            OrderResponse response = orderService.placeOrder(productCode, orderQuantity);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            System.out.flush();
          } catch (BusinessException ex) {
            System.err.println(">>> " + ex.getMessage());
            System.err.flush();
          } catch (JsonProcessingException e) {
            // TODO: Handle the exception properly
            e.printStackTrace();
          }
        }
    );
  }

  private String getResourceFilePath(String filename) {
    File file = null;
    try {
      URL res = getClass().getClassLoader().getResource(filename);
      file = Paths.get(res.toURI()).toFile();
    } catch (URISyntaxException e) {
      throw new IllegalStateException("Unable to resolve filename path", e);
    }
    return file.getAbsolutePath();
  }
}

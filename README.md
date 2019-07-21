
```
Enter number of orders: 10
Enter product code: VS5
Enter order quantity: 10
{
  "product" : {
    "name" : "Vegemite Scroll",
    "code" : "VS5",
    "packs" : [ {
      "size" : 3,
      "price" : 6.99,
      "currency" : "USD"
    }, {
      "size" : 5,
      "price" : 8.99,
      "currency" : "USD"
    } ]
  },
  "packQuantity" : {
    "Pack(size=5, price=8.99, currency=USD)" : 2
  },
  "totalCost" : 17.98,
  "currencyCode" : "USD"
}
Enter product code: MB11
Enter order quantity: 14
{
  "product" : {
    "name" : "Blueberry Muffin",
    "code" : "MB11",
    "packs" : [ {
      "size" : 2,
      "price" : 9.95,
      "currency" : "USD"
    }, {
      "size" : 5,
      "price" : 16.95,
      "currency" : "USD"
    }, {
      "size" : 8,
      "price" : 24.95,
      "currency" : "USD"
    } ]
  },
  "packQuantity" : {
    "Pack(size=8, price=24.95, currency=USD)" : 1,
    "Pack(size=2, price=9.95, currency=USD)" : 3
  },
  "totalCost" : 54.8,
  "currencyCode" : "USD"
}
Enter product code: CF
Enter order quantity: 13
{
  "product" : {
    "name" : "Croissant",
    "code" : "CF",
    "packs" : [ {
      "size" : 3,
      "price" : 5.95,
      "currency" : "USD"
    }, {
      "size" : 5,
      "price" : 9.95,
      "currency" : "USD"
    }, {
      "size" : 9,
      "price" : 16.99,
      "currency" : "USD"
    } ]
  },
  "packQuantity" : {
    "Pack(size=5, price=9.95, currency=USD)" : 2,
    "Pack(size=3, price=5.95, currency=USD)" : 1
  },
  "totalCost" : 25.849999999999998,
  "currencyCode" : "USD"
}
Enter product code: VS5
Enter order quantity: 1
>>> The given order quantity cannot be completely broken down into available packs
Enter product code: ABC
Enter order quantity: 5
>>> No product found for given product code: ABC
```
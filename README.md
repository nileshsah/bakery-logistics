## Bakery Logistics

A bakery used to base the price of their produce on an individual item cost. So if a customer ordered
10 cross buns then they would be charged 10x the cost of a single bun. The bakery has decided to start
selling their produce prepackaged in bunches and charging the customer on a per-pack basis. So if the
the shop sold vegemite scroll in packs of 3 and 5 and a customer ordered 8 they would get a pack of 3 and
a pack of 5. The bakery currently sells the following products:

| Name               | Code   | Packs               |
|--------------------|--------|---------------------|
| Vegemite           | VS5    |  3 @ $6.99          |
| Scroll             |        |  5 @ $8.99          |
|                    |        |                     |
| Blueberry          | MB11   |  2 @ $9.95          |
| Muffin             |        |  5 @ $16.95         |
|                    |        |  8 @ $24.95         |
|                    |        |                     |
| Croissant          | CF     |  3 @ $5.95          |
|                    |        |  5 @ $9.95          |
|                    |        |  9 @ $16.99         |


**Problem:**\
Given a customer order, you are required to determine the cost and pack breakdown for each product. 
To save on shipping space each order should contain the minimal number of packs.

**Input:**\
Each order has a series of lines with each line containing the number of items followed by the product code. 
An example input:\
10 VS5\
14 MB11\
13 CF

**Output:**\
A successfully passing test(s) that demonstrates the following output:\
10 VS5   =>  $17.98   =  2 x (5 $8.99)\
14 MB11  =>  $54.80   =  1 x 8 pack ($24.95) + 3 x 2 pack ($9.95)\
13 CF    =>  $25.85   =  2 x 5 pack ($9.95) +  1 x 3  pack ($5.95)

---

### Compile and Run:

You need the maven build tool to compile and run the project.

Compile:
```bash
mvn clean install
```

Run:
```bash
mvn exec:java
```


### Sample Output:
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

# inventory-api
inventory-api

## Getting Started

### Prerequisites
- Git
- Maven 3.0+
- JDK 11+
- Docker 1.13.0+
- Docker Compose 1.23.1+
- Postgres 10.4+

#### Build

```
make build
```

#### Test

```
make test
```

#### Start Application

Run the application from the command line using:
```
make start
```
The app will start running at <http://localhost:8080>

## API Documentation

http://localhost:8080/v2/api-docs

## Explore Rest APIs

Namespace     |   URL                              | HTTP Verb        | Result
--------------|----------------------------------- | ---------------- | -------------------------
Product       | /product/all                       | GET              | List all products
Product       | /product?code=value                | GET              | Find product by code
Product       | /product/:id                       | GET              | Find product by id
Product       | /product                           | POST             | Save product
Product       | /product/:id                       | PUT              | Update product
Product       | /product/:id                       | DELETE           | Delete product
Product       | /product/disable/:id               | PUT              | Disable product
Purchase      | /purchase                          | POST             | Purchase product
Sale          | /sale                              | POST             | Sale product
Report        | /report/stock-balance/:productType | GET              | Report Stock Balance
Report        | /report/gross-profit/:productCode  | GET              | Report Gross Profit

You can test them using postman or any other rest client.

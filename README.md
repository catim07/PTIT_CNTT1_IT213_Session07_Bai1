# Session 07 - Bài 1: Xây Dựng API Gateway Định Tuyến Động Tích Hợp Eureka (Port 8222)

## 📌 Tổng Quan & Mục Tiêu
Xây dựng dự án `api-gateway` chạy trên port **8222** để tiếp nhận mọi request từ client bên ngoài và định tuyến linh hoạt (Dynamic Routing) qua Spring Cloud Eureka Server tới 3 microservices backend:
- `customer-service` (`/api/customers/**` -> `lb://customer-service`)
- `account-service` (`/api/accounts/**` -> `lb://account-service`)
- `transaction-service` (`/api/transactions/**` -> `lb://transaction-service`)

## 🛠️ Cấu Hình Cốt Lõi `application.yml`
```yaml
server:
  port: 8222

spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: customer-service-route
          uri: lb://customer-service
          predicates:
            - Path=/api/customers/**
        - id: account-service-route
          uri: lb://account-service
          predicates:
            - Path=/api/accounts/**
        - id: transaction-service-route
          uri: lb://transaction-service
          predicates:
            - Path=/api/transactions/**
```

## 🚀 Kiểm Thử
```bash
./gradlew test
```

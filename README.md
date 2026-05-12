# chib_rpm_prakt5

Минимальный учебный пример Spring Boot (Spring Data JPA + Thymeleaf).

## Что есть в проекте
- JPA-связи между `breeds`, `cats`, `users`, `carts`, `cart_items`, `orders`, `order_items`
- Связь `users` ↔ `roles` специально не реализована
- Простые страницы list/create для:
  - breeds
  - cats
  - carts
  - cart-items
  - orders
  - order-items

## Запуск
1. Настройте MySQL и БД.
2. Проверьте `src/main/resources/application.properties`.
3. Запустите:
   ```bash
   mvn spring-boot:run
   ```
4. Откройте `http://localhost:8080/breeds`.

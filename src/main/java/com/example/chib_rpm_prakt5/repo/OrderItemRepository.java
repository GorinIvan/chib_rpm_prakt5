package com.example.chib_rpm_prakt5.repo;

import com.example.chib_rpm_prakt5.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}

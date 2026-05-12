package com.example.chib_rpm_prakt5.repo;

import com.example.chib_rpm_prakt5.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

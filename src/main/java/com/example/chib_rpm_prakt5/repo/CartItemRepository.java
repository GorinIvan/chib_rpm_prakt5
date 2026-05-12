package com.example.chib_rpm_prakt5.repo;

import com.example.chib_rpm_prakt5.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}

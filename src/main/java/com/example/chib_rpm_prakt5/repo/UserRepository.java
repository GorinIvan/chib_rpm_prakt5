package com.example.chib_rpm_prakt5.repo;

import com.example.chib_rpm_prakt5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

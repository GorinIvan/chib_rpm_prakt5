package com.example.chib_rpm_prakt5.repo;

import com.example.chib_rpm_prakt5.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Integer> {
}

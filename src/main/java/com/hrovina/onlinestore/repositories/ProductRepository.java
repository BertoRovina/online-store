package com.hrovina.onlinestore.repositories;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

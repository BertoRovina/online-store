package com.hrovina.onlinestore;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Product;
import com.hrovina.onlinestore.repositories.CategoryRepository;
import com.hrovina.onlinestore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Office");

        Product p1 = new Product(null, "Mouse", 80);
        Product p2 = new Product(null, "Printer", 800);
        Product p3 = new Product(null, "Computer", 2000);

        cat1.getProductList().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProductList().add(p2);

        p1.getCategoryList().add(cat1);
        p2.getCategoryList().addAll(Arrays.asList(cat1, cat2));
        p3.getCategoryList().add(cat1);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

    }
}

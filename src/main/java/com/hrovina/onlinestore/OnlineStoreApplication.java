package com.hrovina.onlinestore;

import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.City;
import com.hrovina.onlinestore.entities.Product;
import com.hrovina.onlinestore.entities.State;
import com.hrovina.onlinestore.repositories.CategoryRepository;
import com.hrovina.onlinestore.repositories.CityRepository;
import com.hrovina.onlinestore.repositories.ProductRepository;
import com.hrovina.onlinestore.repositories.StateRepository;
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

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

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

        State est1 = new State(null, "SP");
        State est2 = new State(null, "MG");

        City c1  = new City(null, "Uberlandia", est1);
        City c2  = new City(null, "Sao Paulo", est2);
        City c3  = new City(null, "Campinas", est2);

        est1.getCityList().addAll(Arrays.asList(c2,c3));
        est2.getCityList().add(c1);

        stateRepository.saveAll(Arrays.asList(est1, est2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

    }
}

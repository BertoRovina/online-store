package com.hrovina.onlinestore;

import com.hrovina.onlinestore.entities.*;
import com.hrovina.onlinestore.repositories.*;
import enums.ClientType;
import enums.PaymentState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnlineStoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}

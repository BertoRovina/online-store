package com.hrovina.onlinestore.config;

import com.hrovina.onlinestore.services.DBService;
import com.hrovina.onlinestore.services.EmailService;
import com.hrovina.onlinestore.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}

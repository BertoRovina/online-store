package com.hrovina.onlinestore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrovina.onlinestore.entities.BankBilletPayment;
import com.hrovina.onlinestore.entities.CardPayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CardPayment.class);
                objectMapper.registerSubtypes(BankBilletPayment.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
package com.hrovina.onlinestore.services.exceptions.validation;

import com.hrovina.onlinestore.controllers.exceptions.FieldMessages;
import com.hrovina.onlinestore.dto.RegisterClientDto;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.ClientRepository;
import com.hrovina.onlinestore.services.exceptions.validation.ClientInsert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, RegisterClientDto> {

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(RegisterClientDto objDto, ConstraintValidatorContext context) {
        List<FieldMessages> list = new ArrayList<>();

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessages("email", "Email already registered"));
        }

        for (FieldMessages e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
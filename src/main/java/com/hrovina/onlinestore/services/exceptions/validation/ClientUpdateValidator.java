package com.hrovina.onlinestore.services.exceptions.validation;

import com.hrovina.onlinestore.controllers.exceptions.FieldMessages;
import com.hrovina.onlinestore.dto.ClientDto;
import com.hrovina.onlinestore.dto.RegisterClientDto;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDto> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClientRepository repo;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDto objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uirId = Integer.parseInt(map.get("id"));

        List<FieldMessages> list = new ArrayList<>();

        Client aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uirId)) {
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
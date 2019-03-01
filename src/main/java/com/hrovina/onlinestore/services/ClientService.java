package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.ClientRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client search(Integer id) {
        Optional<Client> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + Client.class.getName()));
    }
}

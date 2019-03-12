package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> find(@PathVariable Integer id){
        Client obj = clientService.search(id);
        return ResponseEntity.ok().body(obj);
    }
}



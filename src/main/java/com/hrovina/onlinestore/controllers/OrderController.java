package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import com.hrovina.onlinestore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){
        PurchaseOrder obj = orderService.search(id);
        return ResponseEntity.ok().body(obj);
    }
}



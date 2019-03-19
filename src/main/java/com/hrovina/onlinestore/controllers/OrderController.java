package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import com.hrovina.onlinestore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PurchaseOrder> find(@PathVariable Integer id){
        PurchaseOrder obj = orderService.search(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody PurchaseOrder purchaseOrder){
        purchaseOrder = orderService.insert(purchaseOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                                .buildAndExpand(purchaseOrder.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}

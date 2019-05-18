package com.hrovina.onlinestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import com.hrovina.onlinestore.services.OrderService;

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

    @GetMapping
    public ResponseEntity<Page<PurchaseOrder>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instant") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<PurchaseOrder> list = orderService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}

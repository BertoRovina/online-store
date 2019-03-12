package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import com.hrovina.onlinestore.repositories.OrderRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public PurchaseOrder search(Integer id) {
        Optional<PurchaseOrder> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + PurchaseOrder.class.getName()));
    }
}

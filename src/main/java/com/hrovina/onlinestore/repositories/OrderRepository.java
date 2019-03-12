package com.hrovina.onlinestore.repositories;

import com.hrovina.onlinestore.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}

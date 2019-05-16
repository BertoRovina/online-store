package com.hrovina.onlinestore.repositories;

import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.entities.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer> {

    @Transactional(readOnly=true)
    Page<PurchaseOrder> findByClient(Client client, Pageable pageRequest);
}

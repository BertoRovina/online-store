package com.hrovina.onlinestore.repositories;

import com.hrovina.onlinestore.entities.Address;
import com.hrovina.onlinestore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

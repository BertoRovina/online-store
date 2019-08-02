package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.City;
import com.hrovina.onlinestore.entities.State;
import com.hrovina.onlinestore.repositories.CityRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public City findById(Integer id){
        Optional<City> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + State.class.getName()));
    }

    public List<City> findByState(Integer id){
        List<City> obj = repo.findCities(id);
        return obj;

    }

}

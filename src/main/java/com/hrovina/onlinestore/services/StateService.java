package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.State;
import com.hrovina.onlinestore.repositories.StateRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository repo;

    public State findById(Integer id){
        Optional<State> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + State.class.getName()));
    }

    public List<State> findAll(){
        return repo.findAllByOrderByName();
    }

}

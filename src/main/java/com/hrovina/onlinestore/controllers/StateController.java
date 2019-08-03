package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.dto.CityDto;
import com.hrovina.onlinestore.dto.StateDto;
import com.hrovina.onlinestore.entities.City;
import com.hrovina.onlinestore.entities.State;
import com.hrovina.onlinestore.services.CityService;
import com.hrovina.onlinestore.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDto>> findAll(){
        List<State> states = stateService.findAll();
        List<StateDto> stateDtos = states.stream().map(StateDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(stateDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<State> find(@PathVariable Integer id){
        State obj = stateService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/{id}/cities")
    public ResponseEntity<List<CityDto>> findCities(@PathVariable Integer id){
        List<City> cities = cityService.findByState(id);
        List<CityDto> cityDtos = cities.stream().map(CityDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(cityDtos);
    }

}

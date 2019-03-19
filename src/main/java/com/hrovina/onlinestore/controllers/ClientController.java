package com.hrovina.onlinestore.controllers;

import com.hrovina.onlinestore.dto.CategoryDto;
import com.hrovina.onlinestore.dto.ClientDto;
import com.hrovina.onlinestore.dto.RegisterClientDto;
import com.hrovina.onlinestore.entities.Category;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> find(@PathVariable Integer id){
        Client obj = clientService.search(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDto clientDto, @PathVariable Integer id){
        Client Client = clientService.fromDTO(clientDto);
        Client.setId(id);
        Client = clientService.update(Client);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll(){
        List<Client> list = clientService.findAll();
        List<ClientDto> listDto = list.stream().map(obj -> new ClientDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDto>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "oderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        Page<Client> list = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDto> listDto = list.map(obj -> new ClientDto(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody RegisterClientDto registerClientDto){
        Client client = clientService.fromDTO(registerClientDto);
        client = clientService.insert(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}



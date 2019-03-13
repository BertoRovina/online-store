package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.dto.ClientDto;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.ClientRepository;
import com.hrovina.onlinestore.services.exceptions.DataIntegrityException;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client search(Integer id) {
        Optional<Client> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + Client.class.getName()));
    }


    public Client update(Client client){
        Client newClient = search(client.getId());
        updateData(newClient, client);
        return repo.save(newClient);
    }

    public void delete(Integer id){
        search(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cannot delete object because it's associated with other objects.");
        }
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDTO(ClientDto clientDto){
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null);
    }

    private void updateData(Client newClient, Client client){
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }
}

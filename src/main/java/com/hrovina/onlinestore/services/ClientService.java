package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.dto.ClientDto;
import com.hrovina.onlinestore.dto.RegisterClientDto;
import com.hrovina.onlinestore.entities.Address;
import com.hrovina.onlinestore.entities.City;
import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.enums.Profile;
import com.hrovina.onlinestore.repositories.AddressRepository;
import com.hrovina.onlinestore.repositories.ClientRepository;
import com.hrovina.onlinestore.security.UserSS;
import com.hrovina.onlinestore.services.exceptions.AuthorizationException;
import com.hrovina.onlinestore.services.exceptions.DataIntegrityException;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import com.hrovina.onlinestore.enums.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    public Client search(Integer id) {

        UserSS userSS = UserService.authenticated();

        if (userSS == null || !userSS.hashRole(Profile.ADMIN) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Access Denied, you don't have permission to search for clients.");
        }
        Optional<Client> obj = repo.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found, id: " + id + ", type " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client client){
        client.setId(null);
        client = repo.save(client);
        addressRepository.saveAll(client.getAddressList());
        return client;
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
            throw new DataIntegrityException("Cannot delete object because it's associated with orders.");
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
        return new Client(clientDto.getId(), clientDto.getName(), clientDto.getEmail(), null, null, null);
    }

    public Client fromDTO(RegisterClientDto clientDto){
        Client client =  new Client(
                null,
                clientDto.getName(),
                clientDto.getEmail(),
                clientDto.getDoc(),
                ClientType.toEnum(clientDto.getClientType()),
                bCryptPasswordEncoder.encode(clientDto.getPassword()));

        City city = new City(clientDto.getCityId(), null, null);

        Address address = new Address(null, clientDto.getAddress(), clientDto.getNumber(),
                clientDto.getAdditionalAddressInfo(), clientDto.getArea(), clientDto.getZipCode(), client, city);

        client.getAddressList().add(address);

        client.getPhones().add(clientDto.getPhone1());

        if (clientDto.getPhone2() != null) { client.getPhones().add(clientDto.getPhone2()); }
        if (clientDto.getPhone3() != null) { client.getPhones().add(clientDto.getPhone3()); }

        return client;
    }

    private void updateData(Client newClient, Client client){
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }

    public URI uploadProfilePicturce(MultipartFile multipartFile){
        return s3Service.uploadFile(multipartFile);
    }
}

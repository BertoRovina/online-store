package com.hrovina.onlinestore.services;

import com.hrovina.onlinestore.entities.Client;
import com.hrovina.onlinestore.repositories.ClientRepository;
import com.hrovina.onlinestore.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private Random rd = new Random();

    public void sendNewPassword(String email){
        Client client = clientRepository.findByEmail(email);
        if (client == null){
            throw new ObjectNotFoundException("Email not found.");
        }
        String newPass = newPassword();
        client.setPassword(passwordEncoder.encode(newPass));
        clientRepository.save(client);
        emailService.sendNewPasswordEmail(client, newPass);

    }

    private String newPassword() {
        char[] vector = new char[10];
        for (int i = 0; i < vector.length; i++ ){
            vector[i] = randomChar();
        }
        return new String(vector);
    }

    private char randomChar() {
        int opt = rd.nextInt(3);
        if (opt == 0){
            return (char) (rd.nextInt(10) + 48);
        }else if (opt == 1){
            return (char) (rd.nextInt(26) + 65);
        }else {
            return (char) (rd.nextInt(26) + 97);
        }
    }


}

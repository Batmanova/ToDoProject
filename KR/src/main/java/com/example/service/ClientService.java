package com.example.service;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public void create(Client client){
        clientRepository.save(client);
    }

    public void update(Client client){
        clientRepository.save(client);
    }

    public void delete(Client client){
        clientRepository.delete(client);
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> find(Long id){
        return clientRepository.findById(id);
    }

}

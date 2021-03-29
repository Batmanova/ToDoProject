package com.example.controller;

import com.example.entity.Client;
import com.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/api/users")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<Client>> findAll(){
        final List<Client> clientList = clientService.findAll();
        return clientList != null && !clientList.isEmpty()
                ? new ResponseEntity<>(clientList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<Optional<Client>> find(@PathVariable(name = "id") Long id){
        final Optional<Client> user = clientService.find(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long id, @RequestBody Client clientUpdate) {
        return clientService.find(id).map(client -> {
            client.setFirstName(clientUpdate.getFirstName());
            client.setSecondName(clientUpdate.getSecondName());
            client.setLastName(clientUpdate.getLastName());
            client.setLogin(clientUpdate.getLogin());
            client.setCreate_date(clientUpdate.getCreate_date());
            client.setBirthday(clientUpdate.getBirthday());
            client.setChange_date(clientUpdate.getChange_date());
            client.setTasks(clientUpdate.getTasks());
            clientService.update(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());

    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return clientService.find(id).map(client -> {
            clientService.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }


}

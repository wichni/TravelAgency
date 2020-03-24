package com.example.travel.controller;

import com.example.travel.model.Client;
import com.example.travel.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client/add")
    public Client addClient(@RequestBody Client newClient) {
        return clientService.addNewClient(newClient);
    }

    @GetMapping(value = "/clients")
    public List<Client> clientsListView(@RequestParam(required = false) String date) {
        if (date == null) {
            return clientService.findAll();
        }
        return clientService.findClientDontBuyTrip(LocalDate.parse(date));
    }

    @PutMapping("/client/update")
    public Client clientUpdate(@RequestBody Client newClient) {
        return clientService.updateClient(newClient);
    }

    @DeleteMapping("/client/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        return clientService.deleteById(id);
    }

    @GetMapping("/client/highestDiscount")
    public ResponseEntity<String> getClientWithTheHighestDiscount() {
        return clientService.findClientWithTheHighestDiscount();
    }
}
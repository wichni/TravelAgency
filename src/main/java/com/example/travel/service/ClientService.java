package com.example.travel.service;

import com.example.travel.exception.ClientNotFoundException;
import com.example.travel.model.Client;
import com.example.travel.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addNewClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client updateClient(Client client) {
        clientRepository.deleteById(client.getId());
        return clientRepository.save(client);
    }

    public ResponseEntity<Void> deleteById(Long id) {
        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public List<Client> findClientDontBuyTrip(LocalDate date) {
        return clientRepository.findByClientDidNotOrderByDestination(date);
    }

    public ResponseEntity<String> findClientWithTheHighestDiscount() {
        Optional<String> client = clientRepository.findByClientAndSuggestedPAndNegotiatedPrice();
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            throw new ClientNotFoundException("Client not found Exception");
        }
    }
}
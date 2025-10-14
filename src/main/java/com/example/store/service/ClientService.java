package com.example.store.service;

import com.example.store.model.Client;
import com.example.store.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(UUID id, Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(updatedClient.getNom());
            client.setPrenom(updatedClient.getPrenom());
            client.setType(updatedClient.getType());
            client.setRaisonSociale(updatedClient.getRaisonSociale());
            client.setCin(updatedClient.getCin());
            client.setNumFiscal(updatedClient.getNumFiscal());
            client.setTel(updatedClient.getTel());
            client.setEmail(updatedClient.getEmail());
            client.setAdresse(updatedClient.getAdresse());
            client.setPointsFidelite(updatedClient.getPointsFidelite());
            client.setCredit(updatedClient.getCredit());
            client.setSoldeAvance(updatedClient.getSoldeAvance());
            client.setUpdatedAt(updatedClient.getUpdatedAt());
            return clientRepository.save(client);
        }).orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}

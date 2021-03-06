package com.PsychoTeam.Psycho.services.implement;


import com.PsychoTeam.Psycho.Dtos.ClientDTO;
import com.PsychoTeam.Psycho.Models.Client;
import com.PsychoTeam.Psycho.repositories.ClientRepository;
import com.PsychoTeam.Psycho.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImplement implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ClientDTO getClientDTO(long id) {
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }

    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @Override
    public Client getClient(String credential) {
        if (credential.contains("@")){
            return clientRepository.findByEmail(credential);
        }
        else{
            return clientRepository.findByUserName(credential);
        }
    }

    @Override
    public Client getClientToken(String token) {
        return clientRepository.findByToken(token);
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public boolean existClient(long id) {
        return clientRepository.existsById(id);
    }
}